package com.eny.roca.bean;

public class UserCreationRequestBean {
	private Boolean accountEnabled= true;
	private String displayName;
	private String mailNickname;
	private String userPrincipalName;
	private PasswordProfile passwordProfile;
	public Boolean getAccountEnabled() {
		return accountEnabled;
	}
	public void setAccountEnabled(Boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getMailNickname() {
		return mailNickname;
	}
	public void setMailNickname(String mailNickname) {
		this.mailNickname = mailNickname;
	}
	public String getUserPrincipalName() {
		return userPrincipalName;
	}
	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}
	public PasswordProfile getPasswordProfile() {
		return passwordProfile;
	}
	public void setPasswordProfile(PasswordProfile passwordProfile) {
		this.passwordProfile = passwordProfile;
	}
	
	
}
