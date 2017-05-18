package com.mploed.spring.events.creditapplication.messaging;

import com.mploed.spring.events.creditapplication.domain.VerifiedCreditApplicationNumber;
import com.mploed.spring.events.creditapplication.events.incoming.CreditApplicationNumberGeneratedEvent;
import com.mploed.spring.events.creditapplication.repository.VerifiedCreditApplicationNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

@Component
public class IncomingMessageListener {

	private VerifiedCreditApplicationNumberRepository verifiedCreditApplicationNumberRepository;

	@Autowired
	public IncomingMessageListener(VerifiedCreditApplicationNumberRepository verifiedCreditApplicationNumberRepository) {
		this.verifiedCreditApplicationNumberRepository = verifiedCreditApplicationNumberRepository;
	}

	@StreamListener(CreditApplicationChannels.CREDIT_APPLICATION_NUMBER_GENERATED)
	public void receiveCreditApplicationNumberGeneratedEvent(CreditApplicationNumberGeneratedEvent event) {
		verifiedCreditApplicationNumberRepository.save(new VerifiedCreditApplicationNumber(event.getApplicationNumber()));
	}
}
