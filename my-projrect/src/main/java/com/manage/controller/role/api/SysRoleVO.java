package com.manage.controller.role.api;

import java.util.ArrayList;
import java.util.List;

import com.manage.entity.SysRole;
import com.manage.utils.DateTimeUtility;

public class SysRoleVO {
	
	private Integer roleId;

	private String roleName;

	private String remark;
	
	private List<Integer> menuIdList;
	
	private String createTime;
	

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	public static SysRoleVO toVO(SysRole role) {
		SysRoleVO vo = new SysRoleVO();
		vo.setCreateTime(DateTimeUtility.formatYYYYMM(role.getCreateTime()));
		vo.setMenuIdList(role.getMenuIdList());
		vo.setRemark(role.getRemark());
		vo.setRoleId(role.getRoleId());
		vo.setRoleName(role.getRoleName());
		return vo;
	}
	
	public static List<SysRoleVO> toVOs(List<SysRole> roles){
		
		List<SysRoleVO> vos = new ArrayList<>();
		for(SysRole role : roles) {
			vos.add(toVO(role));
		}
		return vos;
	}
}
