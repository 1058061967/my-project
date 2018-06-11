package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.SysConfigEntity;

public interface SysConfigService {
	
	public void save(SysConfigEntity config);
	
	public void update(SysConfigEntity config);

	public void updateValueByKey(String key, String value);
	
	public void deleteBatch(Long[] ids);
	
	public List<SysConfigEntity> queryList(Map<String, Object> map);
	
	public int queryTotal(Map<String, Object> map);
	
	public SysConfigEntity queryObject(Long id);
	
	public String getValue(String key, String defaultValue);
	
	public <T> T getConfigObject(String key, Class<T> clazz) throws Exception;
	
}
