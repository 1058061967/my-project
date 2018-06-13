package com.manage.controller.role.api;

import com.manage.model.PagingRequest;

public class SearchRoleRequest extends PagingRequest{
	
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
