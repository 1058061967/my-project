package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.ScheduleJob;
import com.manage.filter.ScheduleJobFilter;
import com.manage.filter.ScheduleJobFilter;
import com.manage.model.SearchResult;


public interface ScheduleJobService {

	ScheduleJob queryObject(Integer jobId);

	List<ScheduleJob> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(ScheduleJob scheduleJob);

	void update(ScheduleJob scheduleJob);

	void deleteBatch(Integer[] jobIds);

	int updateBatch(Integer[] jobIds, int status);

	void run(Integer[] jobIds);

	void pause(Integer[] jobIds);

	void resume(Integer[] jobIds);
	
	SearchResult<ScheduleJob> searchSchduleJobByFilter(ScheduleJobFilter filter);
}
