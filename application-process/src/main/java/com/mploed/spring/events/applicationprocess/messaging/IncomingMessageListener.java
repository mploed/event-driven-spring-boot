package com.mploed.spring.events.applicationprocess.messaging;

import com.mploed.spring.events.applicationprocess.domain.CreditApplicationStatus;
import com.mploed.spring.events.applicationprocess.events.incoming.CreditApplicationEnteredEvent;
import com.mploed.spring.events.applicationprocess.repository.CreditApplicationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class IncomingMessageListener {
	private CreditApplicationStatusRepository creditApplicationStatusRepository;

	@Autowired
	public IncomingMessageListener(CreditApplicationStatusRepository creditApplicationStatusRepository) {
		this.creditApplicationStatusRepository = creditApplicationStatusRepository;
	}

	@StreamListener(ApplicationProcessChannels.CREDIT_APPLICATION_ENTERED)
	public void receiveCreditApplicationEnteredEvent(@Payload CreditApplicationEnteredEvent creditApplicationEnteredEvent) {
		CreditApplicationStatus status = creditApplicationStatusRepository.findByApplicationNumber(creditApplicationEnteredEvent.getApplicationNumber());
		status.setCreditApplicationEntered(creditApplicationEnteredEvent.getCreationTime());
		creditApplicationStatusRepository.save(status);
	}
}
