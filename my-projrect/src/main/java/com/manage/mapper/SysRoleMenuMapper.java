package com.manage.mapper;

import java.util.List;

import com.manage.entity.SysRoleMenu;

/**
 * 角色与菜单对应关系
 * 
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	
	List<Integer> queryMenuIdList(Integer roleId);
}
