package com.example.hoyotter.model.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class LoginSession implements Serializable{
    
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4026083382495081111L;
	private long id;
	private String userName;
    private String password;
    private String profile;
    
    public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
