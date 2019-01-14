package com.eny.roca.bean;

public class PasswordProfile {
	private boolean forceChangePasswordNextSignIn=false;
	private String password="Kuma2222";
	
	
	public boolean isForceChangePasswordNextSignIn() {
		return forceChangePasswordNextSignIn;
	}
	public void setForceChangePasswordNextSignIn(boolean forceChangePasswordNextSignIn) {
		this.forceChangePasswordNextSignIn = forceChangePasswordNextSignIn;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
