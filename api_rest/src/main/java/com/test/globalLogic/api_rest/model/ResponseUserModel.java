package com.test.globalLogic.api_rest.model;

import java.io.Serializable;
import java.util.Date;

public class ResponseUserModel implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String id;
	private String created;
	private String modified;
	private String last_login;
	private String token;
	private Boolean isactive;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	
	

}
