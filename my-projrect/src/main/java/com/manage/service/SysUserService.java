package com.manage.service;

import java.util.List;

import com.manage.entity.SysUser;
import com.manage.filter.UserFilter;
import com.manage.model.SearchResult;

public interface SysUserService {
	
	public List<String> queryAllPerms(Integer userId);
	
	public List<Integer> queryAllMenuId(Integer userId);
	
	public SysUser searchUserByName(String userName);
	
	public SysUser searchUserById(Integer userId);
	
	public void createUser(SysUser user);

	public void batchDelete(Integer[] userIds);
	
	public int updatePassword(Integer userId, String password, String newPassword);
	
	public SearchResult<SysUser> searchUserByFilter(UserFilter filter);
	
	public Integer countUserByFilter(UserFilter filter);
	
	public void  modifyUser(SysUser user);
	
}
