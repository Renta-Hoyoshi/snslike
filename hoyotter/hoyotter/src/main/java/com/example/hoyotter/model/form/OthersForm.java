package com.example.hoyotter.model.form;

import java.io.Serializable;

public class OthersForm implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 8803176950330791498L;
	private long othersUserId;
    private String othersUserName;
    
	public long getOthersUserId() {
		return othersUserId;
	}
	
	public void setOthersUserId(long othersUserId) {
		this.othersUserId = othersUserId;
	}
	
	public String getOthersUserName() {
		return othersUserName;
	}
	
	public void setOthersUserName(String othersUserName) {
		this.othersUserName = othersUserName;
	}
}
