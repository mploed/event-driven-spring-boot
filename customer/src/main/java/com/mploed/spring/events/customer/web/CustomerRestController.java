package com.mploed.spring.events.customer.web;

import com.mploed.spring.events.customer.domain.Customer;
import com.mploed.spring.events.customer.repository.CustomerRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/rest")
public class CustomerRestController {
	private CustomerRepository customerRepository;

	public CustomerRestController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/{customerId}")
	public Customer index(Model model, @PathVariable Long customerId) {
		return customerRepository.findOne(customerId);
	}
}
