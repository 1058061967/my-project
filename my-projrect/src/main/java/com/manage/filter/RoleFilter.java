package com.manage.filter;

import com.manage.model.SearchFilter;

public class RoleFilter extends SearchFilter{
	
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		if(roleName != null) {
			roleName = "%" + roleName +'%';
		}
		this.roleName = roleName;
	}
	
}
