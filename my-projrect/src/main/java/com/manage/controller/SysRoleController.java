package com.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.entity.SysRoleEntity;
import com.manage.service.SysRoleMenuService;
import com.manage.service.SysRoleService;
import com.manage.utils.PageUtils;
import com.manage.utils.R;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(String roleName, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("roleName", roleName);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		List<SysRoleEntity> list = sysRoleService.queryList(map);
		int total = sysRoleService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(list, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select(){
		//查询列表数据
		List<SysRoleEntity> list = sysRoleService.queryList(new HashMap<String, Object>());
		
		return R.ok().put("list", list);
	}
	
	
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.queryObject(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return R.ok().put("role", role);
	}
	
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role){
		if(StringUtils.isBlank(role.getRoleName())){
			return R.error("角色名称不能为空");
		}
		
		sysRoleService.save(role);
		
		return R.ok();
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role){
		if(StringUtils.isBlank(role.getRoleName())){
			return R.error("角色名称不能为空");
		}
		
		sysRoleService.update(role);
		
		return R.ok();
	}
	
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return R.ok();
	}
}
