package com.mploed.spring.events.creditdecision.events.out;

public class ApplicationDeclinedEvent {
	private String applicationNumber;
	private String reason;

	public ApplicationDeclinedEvent() {
	}

	public ApplicationDeclinedEvent(String applicationNumber, String reason) {
		this.applicationNumber = applicationNumber;
		this.reason = reason;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
