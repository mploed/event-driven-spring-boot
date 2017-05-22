package com.mploed.spring.events.creditapplication.web;

import com.mploed.spring.events.creditapplication.domain.FinancialSituation;
import com.mploed.spring.events.creditapplication.events.external.CreditApplicationEnteredEvent;
import com.mploed.spring.events.creditapplication.events.internal.CreditDetailsEnteredEvent;
import com.mploed.spring.events.creditapplication.events.internal.FinancialSituationEnteredEvent;
import com.mploed.spring.events.creditapplication.messaging.CreditApplicationChannels;
import com.mploed.spring.events.creditapplication.repository.CreditApplicationEnteredEventRepository;
import com.mploed.spring.events.creditapplication.repository.CreditDetailsEnteredEventRepository;
import com.mploed.spring.events.creditapplication.domain.CreditDetails;
import com.mploed.spring.events.creditapplication.repository.FinancialSituationEnteredEventRepository;
import com.mploed.spring.events.creditapplication.repository.VerifiedCreditApplicationNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = "credit-application")
public class CreditApplicationController {
	private final static Logger LOGGER = LoggerFactory.getLogger(CreditApplicationController.class);

	private CreditDetailsEnteredEventRepository creditDetailsEnteredEventRepository;
	private FinancialSituationEnteredEventRepository financialSituationEnteredEventRepository;
	private VerifiedCreditApplicationNumberRepository verifiedCreditApplicationNumberRepository;
	private CreditApplicationEnteredEventRepository creditApplicationEnteredEventRepository;
	private CreditApplicationChannels creditApplicationChannels;


	@Value("${nextProcessStepUrl}")
	private String nextProcessStepUrl;

	@Autowired
	public CreditApplicationController(CreditDetailsEnteredEventRepository creditDetailsEnteredEventRepository,
	                                   FinancialSituationEnteredEventRepository financialSituationEnteredEventRepository,
	                                   VerifiedCreditApplicationNumberRepository verifiedCreditApplicationNumberRepository,
	                                   CreditApplicationEnteredEventRepository creditApplicationEnteredEventRepository,
	                                   CreditApplicationChannels creditApplicationChannels) {
		this.creditDetailsEnteredEventRepository = creditDetailsEnteredEventRepository;
		this.financialSituationEnteredEventRepository = financialSituationEnteredEventRepository;
		this.verifiedCreditApplicationNumberRepository = verifiedCreditApplicationNumberRepository;
		this.creditApplicationEnteredEventRepository = creditApplicationEnteredEventRepository;
		this.creditApplicationChannels = creditApplicationChannels;
	}

	@GetMapping("/{applicationNumber}")
	public String index(Model model, @PathVariable String applicationNumber) {
		LOGGER.info("Received a request for a new application: " + applicationNumber);
		CreditDetails creditDetails = new CreditDetails();
		model.addAttribute("creditDetails", creditDetails);
		model.addAttribute("applicationNumber", applicationNumber);
		return "creditDetails";
	}


	@PostMapping("/{applicationNumber}/creditDetails")
	public String saveCreditDetails(@PathVariable String applicationNumber,
	                                @ModelAttribute CreditDetails creditDetails,
	                                Model model) {
		LOGGER.info("Received credit details: " + creditDetails);
		CreditDetailsEnteredEvent creditDetailsEnteredEvent = new CreditDetailsEnteredEvent(applicationNumber, creditDetails);
		creditDetailsEnteredEventRepository.save(creditDetailsEnteredEvent);
		model.addAttribute("financialSituation", new FinancialSituation());
		model.addAttribute("applicationNumber", applicationNumber);
		return "financialSituation";
	}

	@PostMapping("/{applicationNumber}/financialSituation")
	public String saveFinancialSituation(@PathVariable String applicationNumber,
	                                     @ModelAttribute FinancialSituation financialSituation,
	                                     Model model) {
		LOGGER.info("Received financial situation for: " + applicationNumber);
		LOGGER.info("Received financial situation: " + financialSituation);
		FinancialSituationEnteredEvent financialSituationEnteredEvent = new FinancialSituationEnteredEvent(applicationNumber, financialSituation);
		financialSituationEnteredEventRepository.save(financialSituationEnteredEvent);
		CreditDetailsEnteredEvent creditDetailsEnteredEvent = creditDetailsEnteredEventRepository.findByApplicationNumber(applicationNumber);
		model.addAttribute("creditDetails", creditDetailsEnteredEvent.getCreditDetails());
		model.addAttribute("financialSituation", financialSituation);
		model.addAttribute("applicationNumber", applicationNumber);
		return "summary";
	}

	@PostMapping("/{applicationNumber}/confirm")
	public RedirectView confirmCreditApplication(@PathVariable String applicationNumber) {
		LOGGER.info("app number: " + applicationNumber);
		FinancialSituationEnteredEvent financialSituationEnteredEvent = financialSituationEnteredEventRepository.findByApplicationNumber(applicationNumber);
		CreditDetailsEnteredEvent creditDetailsEnteredEvent = creditDetailsEnteredEventRepository.findByApplicationNumber(applicationNumber);

		CreditApplicationEnteredEvent creditApplicationEnteredEvent = new CreditApplicationEnteredEvent(applicationNumber,
				creditDetailsEnteredEvent.getCreditDetails(),
				financialSituationEnteredEvent.getFinancialSituation());
		CreditApplicationEnteredEvent savedEvent = creditApplicationEnteredEventRepository.save(creditApplicationEnteredEvent);
		creditApplicationChannels.creditApplicationEnteredOut().send(MessageBuilder.withPayload(savedEvent).build());
		LOGGER.info(savedEvent.toString());
		return new RedirectView(nextProcessStepUrl + applicationNumber);
	}

}
