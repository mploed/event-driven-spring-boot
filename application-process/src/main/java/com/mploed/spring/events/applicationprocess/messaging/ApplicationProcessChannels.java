package com.mploed.spring.events.applicationprocess.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ApplicationProcessChannels {
	String CREDIT_APPLICATION_ENTERED = "creditApplicationEnteredIn";
	String CUSTOMER_CREATED = "customerCreatedIn";

	@Output
	MessageChannel creditApplicationNumberGeneratedOut();

	@Input
	SubscribableChannel creditApplicationEnteredIn();

	@Input
	SubscribableChannel customerCreatedIn();

}
