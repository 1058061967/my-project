package com.manage.service;

import java.awt.Menu;
import java.util.List;
import java.util.Map;

import com.manage.entity.SysMenu;
import com.manage.filter.MenuFilter;
import com.manage.model.SearchResult;



public interface SysMenuService {
	
	// 根据父菜单，查询子菜单
	List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList);
	
	List<SysMenu> queryNotButtonList();
	
	List<SysMenu> getUserMenuList(Integer userId);
	
	SysMenu queryObject(Integer menuId);
	

	List<SysMenu> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysMenu menu);

	void update(SysMenu menu);
	
	void deleteBatch(Integer[] menuIds);
	
	SearchResult<SysMenu> searchMenuByFilter(MenuFilter filter);
	
}
