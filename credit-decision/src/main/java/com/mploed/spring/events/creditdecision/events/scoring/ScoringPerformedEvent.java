package com.mploed.spring.events.creditdecision.events.scoring;

public class ScoringPerformedEvent {
	private String applicationNumber;

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
}
