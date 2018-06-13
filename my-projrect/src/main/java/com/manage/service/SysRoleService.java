package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.SysRole;
import com.manage.filter.RoleFilter;
import com.manage.model.SearchResult;

public interface SysRoleService {
	
	SysRole queryObject(Integer roleId);
	
	List<SysRole> queryList(Map<String, Object> map);
	
	Integer queryTotal(Map<String, Object> map);
	
	void save(SysRole role);
	
	void update(SysRole role);
	
	void deleteBatch(Integer[] roleIds);
	
	SearchResult<SysRole> searchRoleByFilter(RoleFilter filter);
}
