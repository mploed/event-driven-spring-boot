package com.mploed.spring.events.applicationprocess;

import com.mploed.spring.events.applicationprocess.messaging.ApplicationProcessChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding(ApplicationProcessChannels.class)
public class ApplicationProcessApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationProcessApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
