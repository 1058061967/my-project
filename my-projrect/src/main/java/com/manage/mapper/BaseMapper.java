package com.manage.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	
	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	
	Integer update(T t);
	
	Integer update(Map<String, Object> map);
	
	Integer delete(Object id);
	
	Integer delete(Map<String, Object> map);
	
	Integer deleteBatch(Object[] id);

	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	Integer queryTotal(Map<String, Object> map);

	Integer queryTotal();
}
