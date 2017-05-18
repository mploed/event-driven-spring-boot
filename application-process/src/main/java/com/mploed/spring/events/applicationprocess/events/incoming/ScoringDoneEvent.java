package com.mploed.spring.events.applicationprocess.events.incoming;

import java.util.Date;

public class ScoringDoneEvent {
	private String applicationNumber;

	private Date creationTime;

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
}
