package com.eny.roca.bean;

public class QuestionBean {
	private String questionDescription;
	private Integer queryId;
	private String queStatus;
	private String modifiedQuestionDescription;
	private Integer isQuestionModified;
	private String queComment;
	private String answer;
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public String getQueStatus() {
		return queStatus;
	}
	public void setQueStatus(String queStatus) {
		this.queStatus = queStatus;
	}
	public Integer getQueryId() {
		return queryId;
	}
	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}

	public String getModifiedQuestionDescription() {
		return modifiedQuestionDescription;
	}
	public void setModifiedQuestionDescription(String modifiedQuestionDescription) {
		this.modifiedQuestionDescription = modifiedQuestionDescription;
	}
	public Integer getIsQuestionModified() {
		return isQuestionModified;
	}
	public void setIsQuestionModified(Integer isQuestionModified) {
		this.isQuestionModified = isQuestionModified;
	}
	public String getQueComment() {
		return queComment;
	}
	public void setQueComment(String queComment) {
		this.queComment = queComment;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
