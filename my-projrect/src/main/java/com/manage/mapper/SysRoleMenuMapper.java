package com.manage.mapper;

import java.util.List;

import com.manage.entity.SysRoleMenuEntity;

/**
 * 角色与菜单对应关系
 * 
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {
	
	List<Long> queryMenuIdList(Long roleId);
}
