package com.mploed.spring.events.scoring.messaging;

import com.mploed.spring.events.scoring.domain.ScoringResult;
import com.mploed.spring.events.scoring.events.ScoringNegativeEvent;
import com.mploed.spring.events.scoring.events.ScoringPositiveEvent;
import com.mploed.spring.events.scoring.events.creditApplicationEntered.CreditApplicationEnteredEvent;
import com.mploed.spring.events.scoring.events.customerCreated.Customer;
import com.mploed.spring.events.scoring.events.customerCreated.CustomerCreatedEvent;
import com.mploed.spring.events.scoring.repository.ScoringResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class IncomingMessageListener {

	private final static Logger LOGGER = LoggerFactory.getLogger(IncomingMessageListener.class);

	private RestTemplate restTemplate;

	private ScoringResultRepository scoringResultRepository;

	private ScoringChannels scoringChannels;

	@Autowired
	public IncomingMessageListener(RestTemplate restTemplate, ScoringResultRepository scoringResultRepository, ScoringChannels scoringChannels) {
		this.restTemplate = restTemplate;
		this.scoringResultRepository = scoringResultRepository;
		this.scoringChannels = scoringChannels;
	}

	@StreamListener(ScoringChannels.CREDIT_APPLICATION_ENTERED)
	public void receiveCreditApplicationEnteredEvent(CreditApplicationEnteredEvent event) {
		LOGGER.info("Received Credit Application Entered Event: " + event.toString());
		ScoringResult scoringResult = loadOrInitializeScoringResult(event.getApplicationNumber());

		scoringResult.setPositiveBalance(event.isBalancePositive());

		scoringResult.setSupportworthyCause(event.isCauseSupportWorthy());

		scoringResult.setLastUpdate(new Date());
		ScoringResult savedScoringResult = scoringResultRepository.save(scoringResult);
		notifyInCaseOfFinalizedScoring(savedScoringResult);

	}


	@StreamListener(ScoringChannels.CUSTOMER_CREATED)
	public void receiveCustomerCreatedEvent(@Payload CustomerCreatedEvent customerCreatedEvent) {
		LOGGER.info("Received Customer Created Event: " + customerCreatedEvent.toString());
		Customer customer = restTemplate.getForObject(customerCreatedEvent.getCustomerUrl(), Customer.class);

		LOGGER.info("Received Customer from Event: " + customer.toString());
		ScoringResult scoringResult = loadOrInitializeScoringResult(customer.getApplicationNumber());

		scoringResult.setLegitCity(customer.isCityLegit());
		scoringResult.setLastUpdate(new Date());
		ScoringResult savedScoringResult = scoringResultRepository.save(scoringResult);

		notifyInCaseOfFinalizedScoring(savedScoringResult);
	}

	private void notifyInCaseOfFinalizedScoring(ScoringResult scoringResult) {
		LOGGER.info("Scoring complete: " + scoringResult.isComplete());
		if (scoringResult.isComplete()) {
			LOGGER.info("Scoring positive: " + scoringResult.isScoringPositive());
			if (scoringResult.isScoringPositive()) {
				ScoringPositiveEvent scoringPositiveEvent = new ScoringPositiveEvent(scoringResult.getApplicationNumber());
				scoringChannels.scoringPositiveOut().send(MessageBuilder.withPayload(scoringPositiveEvent).build());
			} else {
				ScoringNegativeEvent scoringNegativeEvent = new ScoringNegativeEvent(scoringResult.getApplicationNumber());
				scoringChannels.scoringNegativeOut().send(MessageBuilder.withPayload(scoringNegativeEvent).build());
			}
		}
	}

	private ScoringResult loadOrInitializeScoringResult(String applicationNumber) {
		ScoringResult scoringResult = scoringResultRepository.findByApplicationNumber(applicationNumber);

		if (scoringResult == null) {
			scoringResult = new ScoringResult();
			scoringResult.setApplicationNumber(applicationNumber);
		}
		return scoringResult;
	}

}
