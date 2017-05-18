package com.mploed.spring.events.creditapplication.events.incoming;


public class CreditApplicationNumberGeneratedEvent {
	private String applicationNumber;

	public CreditApplicationNumberGeneratedEvent() {
	}


	public CreditApplicationNumberGeneratedEvent(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}
}
