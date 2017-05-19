package com.mploed.spring.events.creditdecision;

import com.mploed.spring.events.creditdecision.messaging.CreditDecisionChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding(CreditDecisionChannels.class)
public class CreditDecisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditDecisionApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
