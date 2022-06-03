package com.example.hoyotter.model.form;

import java.io.Serializable;


public class CommentForm implements Serializable{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -840413640871499269L;
	private long userId;
    private String comment;
    private String userName;
   
	
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
    
}
