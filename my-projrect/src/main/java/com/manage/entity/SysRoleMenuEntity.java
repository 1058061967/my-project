package com.manage.entity;


import java.io.Serializable;

//角色与菜单对应关系

public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Long roleId;

	private Long menuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
}
