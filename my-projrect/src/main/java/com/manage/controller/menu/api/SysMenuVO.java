package com.manage.controller.menu.api;

import java.util.ArrayList;
import java.util.List;

import com.manage.entity.SysMenu;

public class SysMenuVO {
	
	private Integer menuId;

	private Integer parentId;

	private String parentName;

	private String name;

	private String url;
	
	private String perms;

	//类型     0：目录   1：菜单   2：按钮
	private Integer type;

	private String icon;

	private Integer orderNum;
	
	//ztree属性
	private Boolean open;
	
	private List<?> list;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
	public static SysMenuVO toVO(SysMenu menu) {
		SysMenuVO vo = new SysMenuVO();
		vo.setIcon(menu.getIcon());
		vo.setList(menu.getList());
		vo.setMenuId(menu.getMenuId());
		vo.setName(menu.getName());
		vo.setOpen(menu.getOpen());
		vo.setOrderNum(menu.getOrderNum());
		vo.setParentId(menu.getParentId());
		vo.setParentName(menu.getParentName());
		vo.setPerms(menu.getPerms());
		vo.setType(menu.getType());
		vo.setUrl(menu.getUrl());
		return vo;
	}
	
	public static List<SysMenuVO> toVOs(List<SysMenu> menus){
		
		List<SysMenuVO> vos = new ArrayList<>();
		for(SysMenu menu : menus) {
			vos.add(toVO(menu));
		}
		return vos;
	}
	

}
