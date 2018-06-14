package com.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.entity.SysUser;
import com.manage.filter.UserFilter;
import com.manage.mapper.SysUserMapper;
import com.manage.model.PagingData;
import com.manage.model.PagingResult;
import com.manage.model.SearchResult;
import com.manage.service.SysUserRoleService;
import com.manage.service.SysUserService;


@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Override
	public List<String> queryAllPerms(Integer userId) {
		return sysUserMapper.queryAllPerms(userId);
	}

	@Override
	public List<Integer> queryAllMenuId(Integer userId) {
		return sysUserMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUser searchUserByName(String username) {
		return sysUserMapper.selectUserByName(username);
	}
	
	@Override
	public SysUser searchUserById(Integer userId) {
		return sysUserMapper.queryObject(userId);
	}

	@Override
	@Transactional
	public void createUser(SysUser user) {
		user.setCreateTime(new Date());
		//sha256加密
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		sysUserMapper.createUser(user);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void modifyUser(SysUser user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		}
		sysUserMapper.update(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void batchDelete(Integer[] userId) {
		sysUserMapper.deleteBatch(userId);
	}

	@Override
	public int updatePassword(Integer userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserMapper.updatePassword(map);
	}

	
	@Override
	public SearchResult<SysUser> searchUserByFilter(UserFilter filter) {
		
		SearchResult<SysUser>  result = new SearchResult<>();
		List<SysUser>  users = sysUserMapper.selectUserByFilter(filter);
		result.setResult(users);
		PagingData pagingData = filter.getPagingData();
		
		if(pagingData != null & filter.isPaged()) {
		Integer recordNumber = sysUserMapper.countUserByFilter(filter);
		PagingResult pagingResult = new PagingResult(recordNumber, pagingData);
		result.setPaged(true);
		result.setPagingResult(pagingResult);
		}
		return result;
	}

	@Override
	public Integer countUserByFilter(UserFilter filter) {
		return sysUserMapper.countUserByFilter(filter);
	}

	
}
