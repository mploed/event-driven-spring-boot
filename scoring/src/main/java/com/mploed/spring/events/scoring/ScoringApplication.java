package com.mploed.spring.events.scoring;

import com.mploed.spring.events.scoring.messaging.ScoringChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding(ScoringChannels.class)
public class ScoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoringApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
