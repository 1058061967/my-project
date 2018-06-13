package com.manage.entity;


import java.io.Serializable;

//角色与菜单对应关系

public class SysRoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private Integer roleId;

	private Integer menuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}


}
