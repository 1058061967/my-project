package com.manage.mapper;

import java.util.List;
import java.util.Map;

import com.manage.entity.SysUserEntity;

public interface SysUserMapper extends BaseMapper<SysUserEntity> {
	
	List<String> queryAllPerms(Long userId);
	
	List<Long> queryAllMenuId(Long userId);
	
	SysUserEntity queryByUserName(String username);

	int updatePassword(Map<String, Object> map);
}
