package com.mploed.spring.events.creditapplication.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CreditApplicationChannels {
	String CREDIT_APPLICATION_NUMBER_GENERATED = "creditApplicationNumberGeneratedIn";

	@Output
	MessageChannel creditApplicationEnteredOut();

	@Input
	SubscribableChannel creditApplicationNumberGeneratedIn();
}
