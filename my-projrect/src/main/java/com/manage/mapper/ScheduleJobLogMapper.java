package com.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.entity.ScheduleJobLog;
import com.manage.filter.ScheduleJobFilter;
import com.manage.filter.ScheduleJobLogFilter;


public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLog> {
	
	List<ScheduleJobLog>   selectScheduleJobLogByFilter(@Param("filter") ScheduleJobLogFilter filter);
	
	Integer countScheduleJobLogByFilter(@Param("filter") ScheduleJobLogFilter filter);
}
