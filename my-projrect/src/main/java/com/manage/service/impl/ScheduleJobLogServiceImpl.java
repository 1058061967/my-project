package com.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.entity.ScheduleJobLog;
import com.manage.mapper.ScheduleJobLogMapper;
import com.manage.service.ScheduleJobLogService;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	@Autowired
	private ScheduleJobLogMapper scheduleJobLogMapper;
	
	@Override
	public ScheduleJobLog queryObject(Integer jobId) {
		return scheduleJobLogMapper.queryObject(jobId);
	}

	@Override
	public List<ScheduleJobLog> queryList(Map<String, Object> map) {
		return scheduleJobLogMapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return scheduleJobLogMapper.queryTotal(map);
	}

	@Override
	public void save(ScheduleJobLog log) {
		scheduleJobLogMapper.save(log);
	}

}
