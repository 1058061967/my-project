package com.manage.controller.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.entity.ScheduleJobLog;
import com.manage.service.ScheduleJobLogService;
import com.manage.utils.PageResponse;
import com.manage.utils.ServiceResponse;


@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public ServiceResponse list(Integer page, Integer limit, Integer jobId){
		Map<String, Object> map = new HashMap<>();
		map.put("jobId", jobId);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ScheduleJobLog> jobList = scheduleJobLogService.queryList(map);
		int total = scheduleJobLogService.queryTotal(map);
		
		PageResponse pageResult = new PageResponse(jobList, total, limit, page);
		
		return ServiceResponse.ok().put("page", pageResult);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public ServiceResponse info(@PathVariable("logId") Integer logId){
		ScheduleJobLog log = scheduleJobLogService.queryObject(logId);
		
		return ServiceResponse.ok().put("log", log);
	}
}
