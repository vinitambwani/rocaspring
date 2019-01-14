package com.eny.roca.bean;

public class RegistrationTokenBean {
	private String client_id;
	private String grant_type;
	private String client_Secret;
	private String scope;
	private String code;
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getClient_Secret() {
		return client_Secret;
	}
	public void setClient_Secret(String client_Secret) {
		this.client_Secret = client_Secret;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
