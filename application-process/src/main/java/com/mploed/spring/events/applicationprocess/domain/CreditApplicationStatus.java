package com.mploed.spring.events.applicationprocess.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CreditApplicationStatus {
	@Id
	@GeneratedValue
	private Long id;

	private String applicationNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationStart;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creditApplicationEntered;

	@Temporal(TemporalType.TIMESTAMP)
	private Date customerEntered;

	private String scoringResult;

	@Temporal(TemporalType.TIMESTAMP)
	private Date scoringDoneDate;

	private Boolean approved;

	private CreditApplicationStatus() {
	}

	public CreditApplicationStatus(String applicationNumber, Date applicationStart) {
		this.applicationNumber = applicationNumber;
		this.applicationStart = applicationStart;
	}


	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getScoringResult() {
		return scoringResult;
	}

	public void setScoringResult(String scoringResult) {
		this.scoringResult = scoringResult;
	}

	public Date getScoringDoneDate() {
		return scoringDoneDate;
	}

	public void setScoringDoneDate(Date scoringDoneDate) {
		this.scoringDoneDate = scoringDoneDate;
	}

	public Date getCustomerEntered() {
		return customerEntered;
	}

	public void setCustomerEntered(Date customerEntered) {
		this.customerEntered = customerEntered;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Date getApplicationStart() {
		return applicationStart;
	}

	public void setApplicationStart(Date applicationStart) {
		this.applicationStart = applicationStart;
	}

	public Date getCreditApplicationEntered() {
		return creditApplicationEntered;
	}

	public void setCreditApplicationEntered(Date creditApplicationEntered) {
		this.creditApplicationEntered = creditApplicationEntered;
	}

	@Override
	public String toString() {
		return "CreditApplicationStatus{" +
				"id=" + id +
				", applicationNumber='" + applicationNumber + '\'' +
				", applicationStart=" + applicationStart +
				", creditApplicationEntered=" + creditApplicationEntered +
				'}';
	}
}
