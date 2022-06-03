package com.example.hoyotter.model.form;

import java.io.Serializable;

public class UploadForm implements Serializable{
	
	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -4967097782447182785L;
		private long userIdEdit;
	private String userNameEdit;
    private String passwordEdit;
    private String profileEdit;
	public long getUserIdEdit() {
		return userIdEdit;
	}
	public void setUserIdEdit(long userIdEdit) {
		this.userIdEdit = userIdEdit;
	}
	public String getUserNameEdit() {
		return userNameEdit;
	}
	public void setUserNameEdit(String userNameEdit) {
		this.userNameEdit = userNameEdit;
	}
	public String getPasswordEdit() {
		return passwordEdit;
	}
	public void setPasswordEdit(String passwordEdit) {
		this.passwordEdit = passwordEdit;
	}
	public String getProfileEdit() {
		return profileEdit;
	}
	public void setProfileEdit(String profileEdit) {
		this.profileEdit = profileEdit;
	}
    
	
	
}
