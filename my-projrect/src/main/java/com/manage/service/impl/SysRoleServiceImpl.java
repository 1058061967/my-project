package com.manage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.entity.SysRole;
import com.manage.filter.RoleFilter;
import com.manage.mapper.SysRoleMapper;
import com.manage.model.PagingData;
import com.manage.model.PagingResult;
import com.manage.model.SearchResult;
import com.manage.service.SysRoleMenuService;
import com.manage.service.SysRoleService;
import com.manage.service.SysUserRoleService;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Override
	public SysRole queryObject(Integer roleId) {
		return sysRoleMapper.queryObject(roleId);
	}

	@Override
	public List<SysRole> queryList(Map<String, Object> map) {
		return sysRoleMapper.queryList(map);
	}

	@Override
	public Integer queryTotal(Map<String, Object> map) {
		return sysRoleMapper.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysRole role) {
		role.setCreateTime(new Date());
		sysRoleMapper.save(role);
		
		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void update(SysRole role) {
		sysRoleMapper.update(role);
		
		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Integer[] roleIds) {
		sysRoleMapper.deleteBatch(roleIds);
	}

	@Override
	public SearchResult<SysRole> searchRoleByFilter(RoleFilter filter) {
			SearchResult<SysRole>  result = new SearchResult<>();
			List<SysRole> roles = sysRoleMapper.selectRoleByFilter(filter);
			result.setResult(roles);
			PagingData pagingData = filter.getPagingData();
			if(pagingData != null && filter.isPaged()) {
				Integer recordNumber = sysRoleMapper.countRoleByFilter(filter);
				PagingResult pagingResult = new PagingResult(recordNumber, pagingData);
				result.setPagingResult(pagingResult);
				result.setPaged(true);
			}
			return result;
	}

}
