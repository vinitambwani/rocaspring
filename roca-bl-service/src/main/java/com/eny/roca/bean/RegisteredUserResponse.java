package com.eny.roca.bean;

import java.util.Arrays;

public class RegisteredUserResponse {

	private String id;
	private String businessPhones[];
	private String displayName;
	private String givenName;
	private String jobTitle;
	private String mail;
	private String mobilePhone;
	private String officeLocation;
	private String preferredLanguage;
	private String surname;
	private String userPrincipalName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getBusinessPhones() {
		return businessPhones;
	}
	public void setBusinessPhones(String[] businessPhones) {
		this.businessPhones = businessPhones;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	public String getPreferredLanguage() {
		return preferredLanguage;
	}
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUserPrincipalName() {
		return userPrincipalName;
	}
	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}
	@Override
	public String toString() {
		return "RegisteredUserResponse [id=" + id + ", businessPhones=" + Arrays.toString(businessPhones)
				+ ", displayName=" + displayName + ", givenName=" + givenName + ", jobTitle=" + jobTitle + ", mail="
				+ mail + ", mobilePhone=" + mobilePhone + ", officeLocation=" + officeLocation + ", preferredLanguage="
				+ preferredLanguage + ", surname=" + surname + ", userPrincipalName=" + userPrincipalName + "]";
	}
	
	
}
