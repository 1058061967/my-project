package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.SysUser;
import com.manage.filter.UserFilter;
import com.manage.model.SearchResult;

public interface SysUserService {
	

	List<String> queryAllPerms(Integer userId);
	
	List<Integer> queryAllMenuId(Integer userId);
	
	SysUser queryByUserName(String username);
	
	SysUser queryObject(Integer userId);
	
	List<SysUser> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysUser user);
	
	void update(SysUser user);

	void deleteBatch(Integer[] userIds);
	//修改密码
	int updatePassword(Integer userId, String password, String newPassword);
	
	SearchResult<SysUser> searchUserByFilter(UserFilter filter);
	
	Integer countUserByFilter(UserFilter filter);
}
