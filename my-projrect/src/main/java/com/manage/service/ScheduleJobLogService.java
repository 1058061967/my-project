package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.ScheduleJobLogEntity;

public interface ScheduleJobLogService {


	ScheduleJobLogEntity queryObject(Long jobId);
	
	List<ScheduleJobLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScheduleJobLogEntity log);
	
}
