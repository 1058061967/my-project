package com.manage.controller.schedule;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.controller.schedule.api.ScheduleJobLogVO;
import com.manage.controller.schedule.api.SearchScheduleJobLogRequest;
import com.manage.entity.ScheduleJobLog;
import com.manage.filter.ScheduleJobLogFilter;
import com.manage.model.PagingData;
import com.manage.model.SearchResult;
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
	public ServiceResponse searchSheduleJobLog(SearchScheduleJobLogRequest request){

		ScheduleJobLogFilter filter = new ScheduleJobLogFilter();
		filter.setJobId(request.getJobId());
		filter.setPaged(true);
		filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
		SearchResult<ScheduleJobLog> result = scheduleJobLogService.searchShceduleJobLogByFilter(filter);
		PageResponse response = new PageResponse(
				ScheduleJobLogVO.toVOs(result.getResult()),
				result.getPagingResult().getRecordNumber(),
				result.getPagingResult().getPageSize(),
				result.getPagingResult().getTotalPage());
		return ServiceResponse.ok().put("page",response);
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
