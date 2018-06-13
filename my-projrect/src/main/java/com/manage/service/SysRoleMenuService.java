package com.manage.service;

import java.util.List;



public interface SysRoleMenuService {
	
	void saveOrUpdate(Integer roleId, List<Integer> menuIdList);
	
   //根据角色ID，获取菜单ID列表
	List<Integer> queryMenuIdList(Integer roleId);
	
}
