package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.SysMenuEntity;



public interface SysMenuService {
	
	// 根据父菜单，查询子菜单
	List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);
	
	List<SysMenuEntity> queryNotButtonList();
	
	List<SysMenuEntity> getUserMenuList(Long userId);
	
	SysMenuEntity queryObject(Long menuId);
	

	List<SysMenuEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysMenuEntity menu);

	void update(SysMenuEntity menu);
	
	void deleteBatch(Long[] menuIds);
}
