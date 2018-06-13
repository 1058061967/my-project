package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.SysConfig;

public interface SysConfigService {
	
	public void save(SysConfig SysConfig);
	
	public void update(SysConfig SysConfig);

	public void updateValueByKey(String key, String value);
	
	public void deleteBatch(Integer[] ids);
	
	public List<SysConfig> queryList(Map<String, Object> map);
	
	public int queryTotal(Map<String, Object> map);
	
	public SysConfig queryObject(Integer id);
	
	public String getValue(String key, String defaultValue);
	
	public <T> T getSysConfigObject(String key, Class<T> clazz) throws Exception;
	
}
