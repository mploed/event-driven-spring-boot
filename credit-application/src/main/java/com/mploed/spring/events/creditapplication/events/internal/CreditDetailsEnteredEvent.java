package com.mploed.spring.events.creditapplication.events.internal;

import com.mploed.spring.events.creditapplication.domain.CreditDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
public class CreditDetailsEnteredEvent implements Serializable {
	@Id
	@GeneratedValue
	private Long id;

	private UUID eventId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@Embedded
	private CreditDetails creditDetails;

	private CreditDetailsEnteredEvent() {
	}

	public CreditDetailsEnteredEvent(CreditDetails creditDetails) {
		this.creditDetails = creditDetails;
		this.eventId = UUID.randomUUID();
		this.creationTime = new Date();
	}

	public UUID getEventId() {
		return eventId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public CreditDetails getCreditDetails() {
		return creditDetails;
	}


}
