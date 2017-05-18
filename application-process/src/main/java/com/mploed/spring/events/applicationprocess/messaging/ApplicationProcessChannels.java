package com.mploed.spring.events.applicationprocess.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ApplicationProcessChannels {
	@Output
	MessageChannel creditApplicationNumberGeneratedOut();

}
