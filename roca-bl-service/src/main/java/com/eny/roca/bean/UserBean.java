package com.eny.roca.bean;

import java.util.List;

public class UserBean {
	private String emailId;
	private String status;
	private List<String> multipleStatus;
	private Integer subscriptionId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public List<String> getMultipleStatus() {
		return multipleStatus;
	}

	public void setMultipleStatus(List<String> multipleStatus) {
		this.multipleStatus = multipleStatus;
	}

}
