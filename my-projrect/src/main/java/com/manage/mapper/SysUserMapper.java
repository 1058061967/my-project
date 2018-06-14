package com.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.manage.entity.SysUser;
import com.manage.filter.UserFilter;

public interface SysUserMapper{
	
	public List<String> queryAllPerms(Integer userId);
	
	public List<Integer> queryAllMenuId(@Param("userId") Integer userId);
	
	public SysUser selectUserByName(@Param("userName") String userName);

	public Integer updatePassword(Map<String, Object> map);
	
	
	public List<SysUser> selectUserByFilter(@Param("filter") UserFilter filter);
	
	public Integer countUserByFilter(@Param("filter") UserFilter filter);
	
	public SysUser selectUserById(@Param("userId") Integer userId);
	
	public void updateUser(SysUser user);
	
	public void createUser(SysUser user);
	
	public void batchDelete(Integer[] userId);
}
