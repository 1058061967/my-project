package com.manage.mapper;

import java.util.List;

import com.manage.entity.SysUserRoleEntity;


public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {
	
	List<Long> queryRoleIdList(Long userId);
}
