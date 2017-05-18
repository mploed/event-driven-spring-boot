package com.mploed.spring.events.scoring.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ScoringChannels {
	String CREDIT_APPLICATION_ENTERED = "creditApplicationEnteredIn";
	String CUSTOMER_CREATED = "customerCreatedIn";

	@Output
	MessageChannel scoringPositiveOut();

	@Output
	MessageChannel scoringNegativeOut();

	@Input
	SubscribableChannel creditApplicationEnteredIn();

	@Input
	SubscribableChannel customerCreatedIn();

}
