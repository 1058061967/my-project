package com.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.mapper.SysUserRoleMapper;
import com.manage.service.SysUserRoleService;

@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public void saveOrUpdate(Integer userId, List<Integer> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		
		//先删除用户与角色关系
		sysUserRoleMapper.delete(userId);
		
		//保存用户与角色关系
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", roleIdList);
		sysUserRoleMapper.save(map);
	}

	@Override
	public List<Integer> queryRoleIdList(Integer userId) {
		return sysUserRoleMapper.queryRoleIdList(userId);
	}

	@Override
	public void delete(Integer userId) {
		sysUserRoleMapper.delete(userId);
	}
}
