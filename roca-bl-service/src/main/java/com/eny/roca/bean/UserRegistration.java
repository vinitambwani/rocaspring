package com.eny.roca.bean;

public class UserRegistration {
private String legalEntityName;
	
	private String contactPerson;
	
	private int roleId;
	
    private String emailId;
	
	private Long mobileNo;
	
	private String industryId;
	
	private String password;
	
	private Boolean  isEmailVrified=false;
	
	private Boolean  isMobileVrified=false;

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsEmailVrified() {
		return isEmailVrified;
	}

	public void setIsEmailVrified(Boolean isEmailVrified) {
		this.isEmailVrified = isEmailVrified;
	}

	public Boolean getIsMobileVrified() {
		return isMobileVrified;
	}

	public void setIsMobileVrified(Boolean isMobileVrified) {
		this.isMobileVrified = isMobileVrified;
	}

	@Override
	public String toString() {
		return "UserRegistration [legalEntityName=" + legalEntityName + ", contactPerson=" + contactPerson + ", roleId="
				+ roleId + ", emailId=" + emailId + ", mobileNo=" + mobileNo + ", industryId=" + industryId
				+ ", password=" + password + ", isEmailVrified=" + isEmailVrified + ", isMobileVrified="
				+ isMobileVrified + "]";
	}

	
}
