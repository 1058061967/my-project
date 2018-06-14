package com.manage.controller.role;

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
import com.manage.controller.role.api.SearchRoleRequest;
import com.manage.controller.role.api.SysRoleVO;
import com.manage.entity.SysRole;
import com.manage.filter.RoleFilter;
import com.manage.model.PagingData;
import com.manage.model.SearchResult;
import com.manage.service.SysRoleMenuService;
import com.manage.service.SysRoleService;
import com.manage.utils.PageResponse;
import com.manage.utils.ServiceResponse;;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public ServiceResponse searchRoles(SearchRoleRequest request){
		RoleFilter filter = new RoleFilter();
		filter.setRoleName(request.getRoleName());
		filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
		filter.setPaged(request.isPaged());
		SearchResult<SysRole> result = sysRoleService.searchRoleByFilter(filter);	
		PageResponse  response = new PageResponse(
			SysRoleVO.toVOs(result.getResult()), 
			result.getPagingResult().getRecordNumber(),
			result.getPagingResult().getPageSize(),
			result.getPagingResult().getTotalPage());
		return ServiceResponse.ok().put("page", response);
}	
	
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public ServiceResponse select(){
		//查询列表数据
		List<SysRole> list = sysRoleService.queryList(new HashMap<String, Object>());
		
		return ServiceResponse.ok().put("list", list);
	}
	
	
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public ServiceResponse info(@PathVariable("roleId") Integer roleId){
		SysRole role = sysRoleService.queryObject(roleId);
		
		//查询角色对应的菜单
		List<Integer> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return ServiceResponse.ok().put("role", role);
	}
	
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public ServiceResponse save(@RequestBody SysRole role){
		if(StringUtils.isBlank(role.getRoleName())){
			return ServiceResponse.error("角色名称不能为空");
		}
		
		sysRoleService.save(role);
		
		return ServiceResponse.ok();
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public ServiceResponse update(@RequestBody SysRole role){
		if(StringUtils.isBlank(role.getRoleName())){
			return ServiceResponse.error("角色名称不能为空");
		}
		
		sysRoleService.update(role);
		
		return ServiceResponse.ok();
	}
	
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public ServiceResponse delete(@RequestBody Integer[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return ServiceResponse.ok();
	}
}
