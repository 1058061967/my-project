package com.manage.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.entity.SysMenu;
import com.manage.filter.MenuFilter;
import com.manage.mapper.SysMenuMapper;
import com.manage.model.PagingData;
import com.manage.model.PagingResult;
import com.manage.model.SearchResult;
import com.manage.service.SysMenuService;
import com.manage.service.SysRoleMenuService;
import com.manage.service.SysUserService;
import com.manage.utils.Constant.MenuType;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Override
	public List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
		List<SysMenu> menuList = sysMenuMapper.queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenu> userMenuList = new ArrayList<>();
		for(SysMenu menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenu> queryNotButtonList() {
		return sysMenuMapper.queryNotButtonList();
	}

	@Override
	public List<SysMenu> getUserMenuList(Integer userId) {
		//系统管理员，拥有最高权限
		if(userId == 1){
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<Integer> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	@Override
	public SysMenu queryObject(Integer menuId) {
		return sysMenuMapper.queryObject(menuId);
	}

	@Override
	public List<SysMenu> queryList(Map<String, Object> map) {
		return sysMenuMapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysMenuMapper.queryTotal(map);
	}

	@Override
	public void save(SysMenu menu) {
		sysMenuMapper.save(menu);
	}

	@Override
	public void update(SysMenu menu) {
		sysMenuMapper.update(menu);
	}

	@Override
	@Transactional
	public void deleteBatch(Integer[] menuIds) {
		sysMenuMapper.deleteBatch(menuIds);
	}
	
	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenu> getAllMenuList(List<Integer> menuIdList){
		//查询根菜单列表
		List<SysMenu> menuList = queryListParentId(0, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Integer> menuIdList){
		List<SysMenu> subMenuList = new ArrayList<SysMenu>();
		
		for(SysMenu entity : menuList){
			if(entity.getType() == MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}

	@Override
	public SearchResult<SysMenu> searchMenuByFilter(MenuFilter filter) {
		SearchResult<SysMenu> result = new SearchResult<>();
		List<SysMenu>  menus = sysMenuMapper.selectMenuByFilter(filter);
		result.setResult(menus);
		PagingData  pagingData= filter.getPagingData();
		if(pagingData != null && filter.isPaged()) {
			Integer recordNumber = sysMenuMapper.countMenuByFilter(filter);
			PagingResult pagingResult = new PagingResult(recordNumber, pagingData);
			result.setPagingResult(pagingResult);
			result.setPaged(true);
		}
		return result;
	}	
	
}
