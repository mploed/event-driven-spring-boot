package com.mploed.spring.events.creditapplication.events.internal;

import com.mploed.spring.events.creditapplication.domain.CreditDetails;
import com.mploed.spring.events.creditapplication.domain.FinancialSituation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
public class FinancialSituationEnteredEvent implements Serializable {
	@Id
	@GeneratedValue
	private Long id;

	private UUID eventId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@Embedded
	private FinancialSituation financialSituation;

	private FinancialSituationEnteredEvent() {
	}

	public FinancialSituationEnteredEvent(FinancialSituation financialSituation) {
		this.financialSituation = financialSituation;
		this.eventId = UUID.randomUUID();
		this.creationTime = new Date();
	}

	public UUID getEventId() {
		return eventId;
	}

	public Date getCreationTime() {
		return creationTime;
	}


	public FinancialSituation getFinancialSituation() {
		return financialSituation;
	}
}
