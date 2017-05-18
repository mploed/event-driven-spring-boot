package com.mploed.spring.events.customer.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomerChannels {
	@Output
	MessageChannel customerCreatedOut();
}
