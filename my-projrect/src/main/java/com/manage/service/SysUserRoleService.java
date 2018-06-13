package com.manage.service;
import java.util.List;

// 用户与角色对应关系
public interface SysUserRoleService {
	
	void saveOrUpdate(Integer userId, List<Integer> roleIdList);
	//根据用户ID，获取角色ID列表
	List<Integer> queryRoleIdList(Integer userId);
	
	void delete(Integer userId);
}
