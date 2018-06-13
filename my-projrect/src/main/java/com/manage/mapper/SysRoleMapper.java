package com.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.entity.SysRole;
import com.manage.filter.RoleFilter;

public interface SysRoleMapper extends BaseMapper<SysRole> {
	
     List<SysRole> selectRoleByFilter(@Param("filter") RoleFilter filter);
     
     Integer countRoleByFilter(@Param("filter") RoleFilter filter);
}
