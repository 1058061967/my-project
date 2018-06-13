package com.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.manage.entity.SysUser;
import com.manage.filter.UserFilter;

public interface SysUserMapper extends BaseMapper<SysUser> {
	
	List<String> queryAllPerms(Integer userId);
	
	List<Integer> queryAllMenuId(Integer userId);
	
	SysUser queryByUserName(String username);

	int updatePassword(Map<String, Object> map);
	
	List<SysUser> selectUserByFilter(@Param("filter") UserFilter filter);
	Integer countUserByFilter(@Param("filter") UserFilter filter);
}
