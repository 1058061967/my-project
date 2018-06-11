package com.manage.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long roleId;

	private String roleName;

	private String remark;
	
	private List<Long> menuIdList;
	
	private Date createTime;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
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

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
