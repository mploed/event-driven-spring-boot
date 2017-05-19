package com.mploed.spring.events.creditdecision.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
public class DecisionMemo {
	@Id
	@GeneratedValue
	private Long id;

	private String applicationNumber;

	private boolean applicationNumberVerified;

	private Long monthlyIncome;

	private Long monthlyOutgoins;

	private Long creditAmount;

	private Long creditTerm;

	private String creditReason;

	private String customerName;

	private String customerCity;

	private Boolean scoredPositive;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	private Boolean approved;

	public Boolean isApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
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

	public Long getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Long monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Long getMonthlyOutgoins() {
		return monthlyOutgoins;
	}

	public void setMonthlyOutgoins(Long monthlyOutgoins) {
		this.monthlyOutgoins = monthlyOutgoins;
	}

	public Long getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Long creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Long getCreditTerm() {
		return creditTerm;
	}

	public void setCreditTerm(Long creditTerm) {
		this.creditTerm = creditTerm;
	}

	public String getCreditReason() {
		return creditReason;
	}

	public void setCreditReason(String creditReason) {
		this.creditReason = creditReason;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public Boolean getScoredPositive() {
		return scoredPositive;
	}

	public void setScoredPositive(Boolean scoredPositive) {
		this.scoredPositive = scoredPositive;
	}

	public boolean isApplicationNumberVerified() {
		return applicationNumberVerified;
	}

	public void setApplicationNumberVerified(boolean applicationNumberVerified) {
		this.applicationNumberVerified = applicationNumberVerified;
	}

	@Override
	public String toString() {
		return "DecisionMemo{" +
				"id='" + id + '\'' +
				", applicationNumber='" + applicationNumber + '\'' +
				", applicationNumberVerified=" + applicationNumberVerified +
				", monthlyIncome=" + monthlyIncome +
				", monthlyOutgoins=" + monthlyOutgoins +
				", creditAmount=" + creditAmount +
				", creditTerm=" + creditTerm +
				", creditReason='" + creditReason + '\'' +
				", customerName='" + customerName + '\'' +
				", customerCity='" + customerCity + '\'' +
				", scoredPositive=" + scoredPositive +
				", lastUpdate=" + lastUpdate +
				", approved=" + approved +
				'}';
	}
}
