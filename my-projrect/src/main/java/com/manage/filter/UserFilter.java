package com.manage.filter;

import com.manage.model.SearchFilter;

public class UserFilter extends SearchFilter{
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if(userName != null) {
			userName = "%" + userName + "%";
		}
		this.userName = userName;
	}
	
}
