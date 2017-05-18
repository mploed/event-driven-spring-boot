package com.mploed.spring.events.applicationprocess.events.incoming;

import java.util.Date;

public class Customer {
	private String applicationNumber;
	private Date updated;

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
