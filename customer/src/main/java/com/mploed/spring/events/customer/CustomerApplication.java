package com.mploed.spring.events.customer;

import com.mploed.spring.events.customer.messaging.CustomerChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(CustomerChannels.class)
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
