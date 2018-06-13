package com.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.manage.entity.ScheduleJob;
import com.manage.filter.ScheduleJobFilter;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
	
	List<ScheduleJob> selectScheduleJobByFilter(@Param("filter") ScheduleJobFilter filter);
	
	Integer countScheduleJobByFilter(@Param("filter") ScheduleJobFilter filter);

}
