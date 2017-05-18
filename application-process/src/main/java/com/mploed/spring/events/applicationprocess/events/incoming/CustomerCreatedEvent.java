package com.mploed.spring.events.applicationprocess.events.incoming;

public class CustomerCreatedEvent {
	private String customerUrl;

	public String getCustomerUrl() {
		return customerUrl;
	}

	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}
}
