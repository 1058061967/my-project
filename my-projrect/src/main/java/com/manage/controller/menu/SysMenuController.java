package com.manage.controller.menu;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.controller.common.AbstractController;
import com.manage.controller.menu.api.SysMenuVO;
import com.manage.controller.menu.api.searchMenuRequest;
import com.manage.entity.SysMenu;
import com.manage.filter.MenuFilter;
import com.manage.model.PagingData;
import com.manage.model.SearchResult;
import com.manage.service.SysMenuService;
import com.manage.utils.Constant.MenuType;
import com.manage.utils.PageResponse;
import com.manage.utils.RRException;
import com.manage.utils.ServiceResponse;


@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public ServiceResponse searchMenu(searchMenuRequest request){
		MenuFilter filter = new MenuFilter();
		filter.setPaged(request.isPaged());
		filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
		SearchResult<SysMenu>  result = sysMenuService.searchMenuByFilter(filter);
		PageResponse  response = new PageResponse(
				SysMenuVO.toVOs(result.getResult()),
				result.getPagingResult().getTotalPage(),
				result.getPagingResult().getPageSize(),
				result.getPagingResult().getTotalPage());
		return ServiceResponse.ok().put("page",response);
}
	
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public ServiceResponse select(){
		//查询列表数据
		List<SysMenu> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenu root = new SysMenu();
		root.setMenuId(0);
		root.setName("一级菜单");
		root.setParentId(-1);
		root.setOpen(true);
		menuList.add(root);
		
		return ServiceResponse.ok().put("menuList", menuList);
	}
	
	/**
	 * 角色授权菜单
	 */
	@RequestMapping("/perms")
	@RequiresPermissions("sys:menu:perms")
	public ServiceResponse perms(){
		//查询列表数据
		List<SysMenu> menuList = sysMenuService.queryList(new HashMap<String, Object>());
		
		return ServiceResponse.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public ServiceResponse info(@PathVariable("menuId") Integer menuId){
		SysMenu menu = sysMenuService.queryObject(menuId);
		return ServiceResponse.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public ServiceResponse save(@RequestBody SysMenu menu){
		//数据校验
		verifyForm(menu);
		
		sysMenuService.save(menu);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public ServiceResponse update(@RequestBody SysMenu menu){
		//数据校验
		verifyForm(menu);
				
		sysMenuService.update(menu);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public ServiceResponse delete(@RequestBody Integer[] menuIds){
		for(Integer menuId : menuIds){
			if(menuId <= 28){
				return ServiceResponse.error("系统菜单，不能删除");
			}
		}
		sysMenuService.deleteBatch(menuIds);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 用户菜单列表
	 */
	@RequestMapping("/user")
	public ServiceResponse user(){
		List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
		
		return ServiceResponse.ok().put("menuList", menuList);
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenu menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenu parentMenu = sysMenuService.queryObject(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == MenuType.CATALOG.getValue() ||
				menu.getType() == MenuType.MENU.getValue()){
			if(parentType != MenuType.CATALOG.getValue()){
				throw new RRException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == MenuType.BUTTON.getValue()){
			if(parentType != MenuType.MENU.getValue()){
				throw new RRException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
