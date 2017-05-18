package com.mploed.spring.events.customer.event;

import java.io.Serializable;

public class CustomerCreatedEvent implements Serializable {
	private String customerUrl;

	public CustomerCreatedEvent(String customerUrl) {
		this.customerUrl = customerUrl;
	}

	public String getCustomerUrl() {
		return customerUrl;
	}

	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}
}
