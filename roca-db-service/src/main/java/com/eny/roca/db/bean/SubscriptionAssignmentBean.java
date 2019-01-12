package com.eny.roca.db.bean;

public class SubscriptionAssignmentBean {
	private int subscriptionId;
	private String fromAssignment;
	private String toAssignment;
	private String comments;
	
	public int getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(int subscriptionId) {
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
