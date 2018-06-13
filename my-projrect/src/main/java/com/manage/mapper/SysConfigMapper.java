package com.manage.mapper;

import org.apache.ibatis.annotations.Param;

import com.manage.entity.SysConfig;


public interface SysConfigMapper extends BaseMapper<SysConfig> {
	
	/**
	 * 根据key，查询value
	 */
	String queryByKey(String paramKey);
	
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
