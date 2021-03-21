package com.app.model;

public class CutomerPlanFraming {
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
}
