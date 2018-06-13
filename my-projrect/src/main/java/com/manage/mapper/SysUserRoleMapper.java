package com.manage.mapper;

import java.util.List;

import com.manage.entity.SysUserRole;


public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
	
	List<Integer> queryRoleIdList(Integer userId);
}
