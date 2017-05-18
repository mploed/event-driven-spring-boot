package com.mploed.spring.events.scoring.events;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ScoringNegativeEvent implements Serializable {
	private UUID eventId;

	private Date creationTime;

	private String applicationNumber;

	public ScoringNegativeEvent(String applicationNumber) {
		this.eventId = UUID.randomUUID();
		this.creationTime = new Date();
		this.applicationNumber = applicationNumber;
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
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
}
