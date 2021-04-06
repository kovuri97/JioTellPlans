package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerPlanDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerPlanId;
	private int customerId;
	private int planId;
	private String planCategory;
	private double price;
	private String planValidity;
	private String planData;
	private String dataSpeed;
	private String voice;
	private String sms;
	private String otherSubscription;
	private String status;
	private int planCount;
	private String date;
	private long planAvailableDays;
	
	public long getPlanAvailableDays() {
		return planAvailableDays;
	}
	public void setPlanAvailableDays(long planAvailableDays) {
		this.planAvailableDays = planAvailableDays;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPlanCount() {
		return planCount;
	}
	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}
	public long getCustomerPlanId() {
		return customerPlanId;
	}
	public void setCustomerPlanId(long customerPlanId) {
		this.customerPlanId = customerPlanId;
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
	public String getPlanCategory() {
		return planCategory;
	}
	public void setPlanCategory(String planCategory) {
		this.planCategory = planCategory;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPlanValidity() {
		return planValidity;
	}
	public void setPlanValidity(String planValidity) {
		this.planValidity = planValidity;
	}
	public String getPlanData() {
		return planData;
	}
	public void setPlanData(String planData) {
		this.planData = planData;
	}
	public String getDataSpeed() {
		return dataSpeed;
	}
	public void setDataSpeed(String dataSpeed) {
		this.dataSpeed = dataSpeed;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public String getOtherSubscription() {
		return otherSubscription;
	}
	public void setOtherSubscription(String otherSubscription) {
		this.otherSubscription = otherSubscription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CustomerPlanDetails [customerPlanId=" + customerPlanId + ", customerId=" + customerId + ", planId="
				+ planId + ", planCategory=" + planCategory + ", price=" + price + ", planValidity=" + planValidity
				+ ", planData=" + planData + ", dataSpeed=" + dataSpeed + ", voice=" + voice + ", sms=" + sms
				+ ", otherSubscription=" + otherSubscription + ", status=" + status + ", planCount=" + planCount
				+ ", date=" + date + ", planAvailableDays=" + planAvailableDays + "]";
	}
	
}
