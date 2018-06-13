package com.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.manage.entity.SysConfig;
import com.manage.mapper.SysConfigMapper;
import com.manage.service.SysConfigService;

@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	@Override
	public void save(SysConfig config) {
		sysConfigMapper.save(config);
	}

	@Override
	public void update(SysConfig config) {
		sysConfigMapper.update(config);
	}

	@Override
	public void updateValueByKey(String key, String value) {
		sysConfigMapper.updateValueByKey(key, value);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		sysConfigMapper.deleteBatch(ids);
	}

	@Override
	public List<SysConfig> queryList(Map<String, Object> map) {
		return sysConfigMapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysConfigMapper.queryTotal(map);
	}

	@Override
	public SysConfig queryObject(Integer id) {
		return sysConfigMapper.queryObject(id);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String value = sysConfigMapper.queryByKey(key);
		if(StringUtils.isBlank(value)){
			return defaultValue;
		}
		return value;
	}
	
	@Override
	public <T> T getSysConfigObject(String key, Class<T> clazz) throws Exception {
		String value = getValue(key, null);
		if(StringUtils.isNotBlank(value)){
			return JSON.parseObject(value, clazz);
		}
		
		return clazz.newInstance();
	}
}
