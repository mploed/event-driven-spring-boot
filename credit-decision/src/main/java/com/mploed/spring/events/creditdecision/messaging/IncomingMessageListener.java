package com.mploed.spring.events.creditdecision.messaging;

import com.mploed.spring.events.creditdecision.domain.DecisionMemo;
import com.mploed.spring.events.creditdecision.events.applicationentered.ApplicationEnteredEvent;
import com.mploed.spring.events.creditdecision.events.customer.Customer;
import com.mploed.spring.events.creditdecision.events.customer.CustomerCreatedEvent;
import com.mploed.spring.events.creditdecision.events.numbergenerated.ApplicationNumberGeneratedEvent;
import com.mploed.spring.events.creditdecision.events.out.ApplicationDeclinedEvent;
import com.mploed.spring.events.creditdecision.events.scoring.ScoringPerformedEvent;
import com.mploed.spring.events.creditdecision.repository.DecisionMemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class IncomingMessageListener {
	private final static Logger LOGGER = LoggerFactory.getLogger(IncomingMessageListener.class);

	private DecisionMemoRepository decisionMemoRepository;

	private RestTemplate restTemplate;

	@Autowired
	public IncomingMessageListener(DecisionMemoRepository decisionMemoRepository, RestTemplate restTemplate) {
		this.decisionMemoRepository = decisionMemoRepository;
		this.restTemplate = restTemplate;
	}

	@StreamListener(CreditDecisionChannels.CREDIT_APPLICATION_NUMBER_GENERATED)
	public void applicationNumberGenerated(ApplicationNumberGeneratedEvent event) {
		DecisionMemo decisionMemo = loadOrCreateDecisionMemo(event.getApplicationNumber());
		decisionMemo.setApplicationNumberVerified(true);
		decisionMemo.setLastUpdate(new Date());
		decisionMemoRepository.save(decisionMemo);
	}


	@StreamListener(CreditDecisionChannels.CREDIT_APPLICATION_ENTERED)
	public void applicationEntered(ApplicationEnteredEvent event) {
		DecisionMemo decisionMemo = loadOrCreateDecisionMemo(event.getApplicationNumber());
		decisionMemo.setCreditAmount(event.creditAmount());
		decisionMemo.setCreditTerm(event.creditTerm());
		decisionMemo.setCreditReason(event.creditReason());
		decisionMemo.setMonthlyIncome(event.monthlyIncome());
		decisionMemo.setMonthlyOutgoins(event.monthlyOutgoings());
		decisionMemo.setLastUpdate(new Date());
		decisionMemoRepository.save(decisionMemo);

	}

	@StreamListener(CreditDecisionChannels.CUSTOMER_CREATED)
	public void customerCreated(CustomerCreatedEvent event) {
		Customer customer = restTemplate.getForObject(event.getCustomerUrl(), Customer.class);
		DecisionMemo decisionMemo = loadOrCreateDecisionMemo(customer.getApplicationNumber());
		decisionMemo.setCustomerCity(customer.getCity());
		decisionMemo.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
		decisionMemo.setLastUpdate(new Date());
		decisionMemoRepository.save(decisionMemo);
	}

	@StreamListener(CreditDecisionChannels.SCORING_POSITIVE)
	public void scoringPositive(ScoringPerformedEvent event) {
		DecisionMemo decisionMemo = loadOrCreateDecisionMemo(event.getApplicationNumber());
		decisionMemo.setScoredPositive(true);
		decisionMemo.setApproved(true);
		decisionMemo.setLastUpdate(new Date());
		decisionMemoRepository.save(decisionMemo);
	}

	@StreamListener(CreditDecisionChannels.SCORING_NEGATIVE)
	@SendTo(CreditDecisionChannels.APPLICATION_DECLINED_OUT)
	public ApplicationDeclinedEvent scoringNegative(ScoringPerformedEvent event) {
		DecisionMemo decisionMemo = loadOrCreateDecisionMemo(event.getApplicationNumber());
		decisionMemo.setScoredPositive(false);
		decisionMemo.setApproved(false);
		decisionMemo.setLastUpdate(new Date());
		decisionMemoRepository.save(decisionMemo);
		return new ApplicationDeclinedEvent(event.getApplicationNumber(), "Scoring negative, auto declined");
	}

	private DecisionMemo loadOrCreateDecisionMemo(String applicationNumber) {
		DecisionMemo decisionMemo = decisionMemoRepository.findByApplicationNumber(applicationNumber);
		if (decisionMemo == null) {
			decisionMemo = new DecisionMemo();
			decisionMemo.setApplicationNumber(applicationNumber);
		}
		return decisionMemo;
	}
}
