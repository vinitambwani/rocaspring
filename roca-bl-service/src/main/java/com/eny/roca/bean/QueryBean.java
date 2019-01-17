package com.eny.roca.bean;

public class QueryBean extends QuestionBean{
	private String queryCaption;
	private String queryFact;
	private String Category;
	private String financialYear;
	private String status;
	private Integer isAssigned;
	private Integer inScope;
	private String comment;
	private Integer userId;
	
	private Boolean isSubmit;
	
	public String getQueryCaption() {
		return queryCaption;
	}
	public void setQueryCaption(String queryCaption) {
		this.queryCaption = queryCaption;
	}
	public String getQueryFact() {
		return queryFact;
	}
	public void setQueryFact(String queryFact) {
		this.queryFact = queryFact;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(Integer isAssigned) {
		this.isAssigned = isAssigned;
	}
	public Integer getInScope() {
		return inScope;
	}
	public void setInScope(Integer inScope) {
		this.inScope = inScope;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Boolean getIsSubmit() {
		return isSubmit;
	}
	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}
}
