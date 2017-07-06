package com.mploed.spring.events.creditapplication;

import com.mploed.spring.events.creditapplication.messaging.CreditApplicationChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(CreditApplicationChannels.class)
public class CreditApplicationSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditApplicationSpringApplication.class, args);
	}
}
