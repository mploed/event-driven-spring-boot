package com.mploed.spring.events.scoring.events.customerCreated;

public class CustomerCreatedEvent {
	private String customerUrl;

	public String getCustomerUrl() {
		return customerUrl;
	}

	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}

	@Override
	public String toString() {
		return "CustomerCreatedEvent{" +
				"customerUrl='" + customerUrl + '\'' +
				'}';
	}
}
