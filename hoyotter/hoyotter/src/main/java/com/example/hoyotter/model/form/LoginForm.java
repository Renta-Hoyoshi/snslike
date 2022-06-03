package com.example.hoyotter.model.form;

import java.io.Serializable;

public class LoginForm implements Serializable{
	
	private static final long serialVersionUID = 1010959524612166828L;
	private long id;
	private String userName;
    private String password;
    
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
