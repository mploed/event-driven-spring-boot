package com.mploed.spring.events.creditdecision.events.customer;

public class CustomerCreatedEvent {
	private String customerUrl;

	public String getCustomerUrl() {
		return customerUrl;
	}

	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}
}
