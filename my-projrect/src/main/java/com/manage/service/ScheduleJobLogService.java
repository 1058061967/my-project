package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.ScheduleJobLog;
import com.manage.filter.ScheduleJobLogFilter;
import com.manage.model.SearchResult;

public interface ScheduleJobLogService {


	ScheduleJobLog queryObject(Integer jobId);
	
	List<ScheduleJobLog> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScheduleJobLog log);
	
	SearchResult<ScheduleJobLog>  searchShceduleJobLogByFilter(ScheduleJobLogFilter filter);
	
}
