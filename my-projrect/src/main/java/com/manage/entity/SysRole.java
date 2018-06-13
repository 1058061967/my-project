package com.manage.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysRole implements Serializable {
	private static final Long serialVersionUID = 1L;
	
	private Integer roleId;

	private String roleName;

	private String remark;
	
	private List<Integer> menuIdList;
	
	private Date createTime;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Integer> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Integer> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
