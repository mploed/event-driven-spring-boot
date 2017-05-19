package com.mploed.spring.events.creditdecision.events.numbergenerated;

public class ApplicationNumberGeneratedEvent {
	private String applicationNumber;

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
}
