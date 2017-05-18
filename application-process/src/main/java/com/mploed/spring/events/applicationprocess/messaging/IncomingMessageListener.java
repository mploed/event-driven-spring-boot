package com.mploed.spring.events.applicationprocess.messaging;

import com.mploed.spring.events.applicationprocess.domain.CreditApplicationStatus;
import com.mploed.spring.events.applicationprocess.events.incoming.CreditApplicationEnteredEvent;
import com.mploed.spring.events.applicationprocess.events.incoming.Customer;
import com.mploed.spring.events.applicationprocess.events.incoming.CustomerCreatedEvent;
import com.mploed.spring.events.applicationprocess.events.incoming.ScoringDoneEvent;
import com.mploed.spring.events.applicationprocess.repository.CreditApplicationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class IncomingMessageListener {
	private CreditApplicationStatusRepository creditApplicationStatusRepository;

	private RestTemplate restTemplate;

	public IncomingMessageListener(CreditApplicationStatusRepository creditApplicationStatusRepository, RestTemplate restTemplate) {
		this.creditApplicationStatusRepository = creditApplicationStatusRepository;
		this.restTemplate = restTemplate;
	}

	@StreamListener(ApplicationProcessChannels.CREDIT_APPLICATION_ENTERED)
	public void receiveCreditApplicationEnteredEvent(@Payload CreditApplicationEnteredEvent creditApplicationEnteredEvent) {
		CreditApplicationStatus status = creditApplicationStatusRepository.findByApplicationNumber(creditApplicationEnteredEvent.getApplicationNumber());
		status.setCreditApplicationEntered(creditApplicationEnteredEvent.getCreationTime());
		creditApplicationStatusRepository.save(status);
	}


	@StreamListener(ApplicationProcessChannels.CUSTOMER_CREATED)
	public void receiveCustomerCreatedEvent(@Payload CustomerCreatedEvent customerCreatedEvent) {
		Customer customer = restTemplate.getForObject(customerCreatedEvent.getCustomerUrl(), Customer.class);
		CreditApplicationStatus status = creditApplicationStatusRepository.findByApplicationNumber(customer.getApplicationNumber());
		status.setCustomerEntered(customer.getUpdated());
		creditApplicationStatusRepository.save(status);
	}

	@StreamListener(ApplicationProcessChannels.SCORING_NEGATIVE)
	public void receiveScoringNegativeEvent(@Payload ScoringDoneEvent scoringDoneEvent) {
		CreditApplicationStatus status = creditApplicationStatusRepository.findByApplicationNumber(scoringDoneEvent.getApplicationNumber());
		status.setScoringResult("DECLINED");
		status.setScoringDoneDate(scoringDoneEvent.getCreationTime());
		creditApplicationStatusRepository.save(status);
	}

	@StreamListener(ApplicationProcessChannels.SCORING_POSITIVE)
	public void receiveScoringPositiveEvent(@Payload ScoringDoneEvent scoringDoneEvent) {
		CreditApplicationStatus status = creditApplicationStatusRepository.findByApplicationNumber(scoringDoneEvent.getApplicationNumber());
		status.setScoringResult("ACCEPTABLE");
		status.setScoringDoneDate(scoringDoneEvent.getCreationTime());
		creditApplicationStatusRepository.save(status);
	}
}
