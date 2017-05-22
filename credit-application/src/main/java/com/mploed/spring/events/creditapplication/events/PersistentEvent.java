package com.mploed.spring.events.creditapplication.events;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersistentEvent implements Serializable {
	@Id
	@GeneratedValue
	private Long id;

	private UUID eventId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@Column(name = "appNumber")
	private String applicationNumber;

	protected PersistentEvent() {

	}

	public PersistentEvent(String applicationNumber) {
		this.eventId = UUID.randomUUID();
		this.creationTime = new Date();
		this.applicationNumber = applicationNumber;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
				return "id=" + id +
				", eventId=" + eventId +
				", creationTime=" + creationTime +
				", applicationNumber='" + applicationNumber + '\'';
	}
}
