package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmailsInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emailInfoId;
	private int customerId;
	private int planId;
	private String fullname;
	private String emailId;
	private String date;
	private String planStatus;
	public int getEmailInfoId() {
		return emailInfoId;
	}
	public void setEmailInfoId(int emailInfoId) {
		this.emailInfoId = emailInfoId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	@Override
	public String toString() {
		return "EmailsInfo [emailInfoId=" + emailInfoId + ", customerId=" + customerId + ", planId=" + planId
				+ ", fullname=" + fullname + ", emailId=" + emailId + ", date=" + date + ", planStatus=" + planStatus
				+ "]";
	}

}
