package com.mploed.spring.events.creditapplication.events.external;

import com.mploed.spring.events.creditapplication.domain.CreditDetails;
import com.mploed.spring.events.creditapplication.domain.FinancialSituation;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class CreditApplicationEnteredEvent {
	@Id
	@GeneratedValue
	private Long id;

	private UUID eventId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@Column(name = "appNumber")
	private String applicationNumber;

	@Embedded
	@AttributeOverride(
			name = "applicationNumber", column = @Column(name = "creditDetailsAppNumber")
	)
	private CreditDetails creditDetails;

	@Embedded
	@AttributeOverride(
			name = "applicationNumber", column = @Column(name = "financialSituationAppNumber")
	)
	private FinancialSituation financialSituation;


	public CreditApplicationEnteredEvent(String applicationNumber, CreditDetails creditDetails, FinancialSituation financialSituation) {
		this.applicationNumber = applicationNumber;
		this.creditDetails = creditDetails;
		this.financialSituation = financialSituation;
	}

	public Long getId() {
		return id;
	}

	public UUID getEventId() {
		return eventId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public CreditDetails getCreditDetails() {
		return creditDetails;
	}

	public FinancialSituation getFinancialSituation() {
		return financialSituation;
	}
}
