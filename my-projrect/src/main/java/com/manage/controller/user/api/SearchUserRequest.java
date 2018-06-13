package com.manage.controller.user.api;

import com.manage.model.PagingRequest;

public class SearchUserRequest extends PagingRequest{
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
