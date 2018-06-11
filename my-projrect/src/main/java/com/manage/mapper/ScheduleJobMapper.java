package com.manage.mapper;

import java.util.Map;

import com.manage.entity.ScheduleJobEntity;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
