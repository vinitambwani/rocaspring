package com.eny.roca.db.bean;

public class SubscriptionAssignment {

	private Integer  subscriptionId;
	private String  fromAssignment;
	private String  toAssignment;
	private String  comments;
	
	public Integer getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getFromAssignment() {
		return fromAssignment;
	}
	public void setFromAssignment(String fromAssignment) {
		this.fromAssignment = fromAssignment;
	}
	public String getToAssignment() {
		return toAssignment;
	}
	public void setToAssignment(String toAssignment) {
		this.toAssignment = toAssignment;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
