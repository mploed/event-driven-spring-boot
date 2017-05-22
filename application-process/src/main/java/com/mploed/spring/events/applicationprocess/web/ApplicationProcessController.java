package com.mploed.spring.events.applicationprocess.web;

import com.mploed.spring.events.applicationprocess.domain.CreditApplicationStatus;
import com.mploed.spring.events.applicationprocess.events.CreditApplicationNumberGeneratedEvent;
import com.mploed.spring.events.applicationprocess.messaging.ApplicationProcessChannels;
import com.mploed.spring.events.applicationprocess.repository.CreditApplicationStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.UUID;

@Controller
public class ApplicationProcessController {
	private final Logger LOGGER = LoggerFactory.getLogger(ApplicationProcessController.class);

	private CreditApplicationStatusRepository repository;

	private ApplicationProcessChannels applicationProcessChannels;

	@Value("${nextProcessStepUrl}")
	private String nextProcessStepUrl;


	@Autowired
	public ApplicationProcessController(CreditApplicationStatusRepository repository, ApplicationProcessChannels applicationProcessChannels) {
		this.repository = repository;
		this.applicationProcessChannels = applicationProcessChannels;
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/")
	public RedirectView startCreditApplicationProcess() {
		//Create Credit Application Number
		UUID creditApplicationNumber = UUID.randomUUID();
		Date applicationTime = new Date();
		LOGGER.info("Created a new Credit Application Number: " + creditApplicationNumber.toString());

		// We are saving the initial status
		CreditApplicationStatus status = new CreditApplicationStatus(creditApplicationNumber.toString(), applicationTime);
		repository.save(status);
		LOGGER.info("Saved " + status.toString());

		// We are sending a CreditApplicationNumberGeneratedEvent
		CreditApplicationNumberGeneratedEvent event = new CreditApplicationNumberGeneratedEvent();
		event.setApplicationNumber(creditApplicationNumber.toString());
		event.setCreationTime(applicationTime);
		applicationProcessChannels.creditApplicationNumberGeneratedOut()
				.send(MessageBuilder.withPayload(event).build());
		LOGGER.info("Sent " + event.toString());

		return new RedirectView(nextProcessStepUrl + creditApplicationNumber.toString());
	}


}
