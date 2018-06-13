package com.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.entity.SysMenu;
import com.manage.filter.MenuFilter;


public interface SysMenuMapper extends BaseMapper<SysMenu> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(Integer parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();
	
	List<SysMenu> selectMenuByFilter(@Param("filter") MenuFilter filter);
	Integer       countMenuByFilter(@Param("filter") MenuFilter filter);
	
	
}
