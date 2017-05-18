package com.mploed.spring.events.applicationprocess.events;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class CreditApplicationNumberGeneratedEvent implements Serializable {
	private UUID id;
	private Date creationTime;
	private String applicationNumber;

	public CreditApplicationNumberGeneratedEvent(Date creationTime, String applicationNumber) {
		super();
		this.creationTime = creationTime;
		this.applicationNumber = applicationNumber;
	}

	public CreditApplicationNumberGeneratedEvent() {
		this.id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@Override
	public String toString() {
		return "CreditApplicationNumberGeneratedEvent{" +
				"id=" + id +
				", creationTime=" + creationTime +
				", applicationNumber='" + applicationNumber + '\'' +
				'}';
	}
}
