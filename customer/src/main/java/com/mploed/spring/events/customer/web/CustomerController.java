package com.mploed.spring.events.customer.web;

import com.mploed.spring.events.customer.domain.Customer;
import com.mploed.spring.events.customer.event.CustomerCreatedEvent;
import com.mploed.spring.events.customer.messaging.CustomerChannels;
import com.mploed.spring.events.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	private Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Value("${nextProcessStepUrl}")
	private String nextProcessStepUrl;

	@Value("${routeToSelf}")
	private String routeToSelf;

	private CustomerRepository customerRepository;

	private CustomerChannels customerChannels;

	public CustomerController(CustomerRepository customerRepository, CustomerChannels customerChannels) {
		this.customerRepository = customerRepository;
		this.customerChannels = customerChannels;
	}

	@GetMapping("/{applicationNumber}")
	public String index(Model model, @PathVariable String applicationNumber) {
		LOGGER.info("Received a request for a new customer: " + applicationNumber);
		Customer customer = new Customer();
		customer.setApplicationNumber(applicationNumber);
		model.addAttribute("customer", customer);
		return "customer";
	}

	@PostMapping("/saveCustomer")
	public RedirectView saveCustomer(@ModelAttribute Customer customer, Model model) {
		customer.setUpdated(new Date());
		Customer savedCustomer = customerRepository.save(customer);

		CustomerCreatedEvent customerCreatedEvent =
				new CustomerCreatedEvent(routeToSelf + "customer/rest/" + savedCustomer.getId());
		customerChannels.customerCreatedOut().send(MessageBuilder.withPayload(customerCreatedEvent).build());
		return new RedirectView(nextProcessStepUrl + customer.getApplicationNumber());
	}

	@RequestMapping(value = "/feed", produces = "application/atom+xml")
	public ModelAndView orderFeed(WebRequest webRequest) {
		return new ModelAndView(new CustomerAtomFeedView(customerRepository), "customers", customerRepository.findAll());
	}
}
