package com.manage.controller.user.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.manage.entity.SysUser;
import com.manage.utils.DateTimeUtility;

public class SysUserVO {
	
	private Integer userId;
	
	private String userName;

	private transient String password;
	
	private String email;

	private String mobile;

	private Integer status;
	
	private List<Integer> roleIdList;

	private String createTime;

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public static SysUserVO toVO(SysUser user) {
		SysUserVO vo = new SysUserVO();
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDD(user.getCreateTime()));
		vo.setEmail(user.getEmail());
		vo.setMobile(user.getMobile());
		vo.setMobile(user.getMobile());
		vo.setPassword(user.getPassword());
		vo.setStatus(user.getStatus());
		vo.setUserId(user.getUserId());
		vo.setUserName(user.getUserName());
		vo.setRoleIdList(user.getRoleIdList());
		return vo;
	}
	
	public static List<SysUserVO> toVOs(List<SysUser> users) {
		List<SysUserVO> vos = new ArrayList<>();
		for(SysUser user : users) {
			vos.add(toVO(user));
		}
		return vos;
	}
	
	
}
