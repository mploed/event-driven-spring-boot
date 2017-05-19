package com.mploed.spring.events.creditdecision.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CreditDecisionChannels {
	String CREDIT_APPLICATION_NUMBER_GENERATED = "creditApplicationNumberGeneratedIn";
	String CREDIT_APPLICATION_ENTERED = "creditApplicationEnteredIn";
	String CUSTOMER_CREATED = "customerCreatedIn";
	String SCORING_POSITIVE = "scoringPositiveIn";
	String SCORING_NEGATIVE = "scoringNegativeIn";
	String APPLICATION_DECLINED_OUT = "applicationDeclinedOut";

	@Input
	SubscribableChannel creditApplicationNumberGeneratedIn();

	@Input
	SubscribableChannel creditApplicationEnteredIn();

	@Input
	SubscribableChannel customerCreatedIn();

	@Input
	SubscribableChannel scoringPositiveIn();

	@Input
	SubscribableChannel scoringNegativeIn();

	@Output
	MessageChannel applicationDeclinedOut();
}
