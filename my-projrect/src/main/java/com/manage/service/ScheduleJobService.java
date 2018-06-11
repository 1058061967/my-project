package com.manage.service;

import java.util.List;
import java.util.Map;

import com.manage.entity.ScheduleJobEntity;


public interface ScheduleJobService {

	ScheduleJobEntity queryObject(Long jobId);

	List<ScheduleJobEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(ScheduleJobEntity scheduleJob);

	void update(ScheduleJobEntity scheduleJob);

	void deleteBatch(Long[] jobIds);

	int updateBatch(Long[] jobIds, int status);

	void run(Long[] jobIds);

	void pause(Long[] jobIds);

	void resume(Long[] jobIds);
}
