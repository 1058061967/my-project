package com.manage.controller.schedule;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.controller.schedule.api.ScheduleJobVO;
import com.manage.controller.schedule.api.SearchSchduleJobRequest;
import com.manage.entity.ScheduleJob;
import com.manage.filter.ScheduleJobFilter;
import com.manage.model.PagingData;
import com.manage.model.SearchResult;
import com.manage.service.ScheduleJobService;
import com.manage.utils.PageResponse;
import com.manage.utils.RRException;
import com.manage.utils.ServiceResponse;


@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:list")
	public ServiceResponse searchSchduleJob(SearchSchduleJobRequest request){
		
		ScheduleJobFilter filter = new ScheduleJobFilter();
		filter.setBeanName(request.getBeanName());
		filter.setPaged(request.isPaged());
		filter.setPagingData(new PagingData(request.getPage(), request.getLimit()));
		SearchResult<ScheduleJob> result = scheduleJobService.searchSchduleJobByFilter(filter);
		PageResponse response = new PageResponse(
				ScheduleJobVO.toVOs(result.getResult()),
				result.getPagingResult().getRecordNumber(),
				result.getPagingResult().getPageSize(),
				result.getPagingResult().getTotalPage());
		return ServiceResponse.ok().put("page", response);
	}	

	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	@RequiresPermissions("sys:schedule:info")
	public ServiceResponse info(@PathVariable("jobId") Integer jobId){
		ScheduleJob schedule = scheduleJobService.queryObject(jobId);
		
		return ServiceResponse.ok().put("schedule", schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:schedule:save")
	public ServiceResponse save(@RequestBody ScheduleJob scheduleJob){
		//数据校验
		verifyForm(scheduleJob);
		
		scheduleJobService.save(scheduleJob);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 修改定时任务
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:schedule:update")
	public ServiceResponse update(@RequestBody ScheduleJob scheduleJob){
		//数据校验
		verifyForm(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:schedule:delete")
	public ServiceResponse delete(@RequestBody Integer[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@RequestMapping("/run")
	@RequiresPermissions("sys:schedule:run")
	public ServiceResponse run(@RequestBody Integer[] jobIds){
		scheduleJobService.run(jobIds);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@RequestMapping("/pause")
	@RequiresPermissions("sys:schedule:pause")
	public ServiceResponse pause(@RequestBody Integer[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@RequestMapping("/resume")
	@RequiresPermissions("sys:schedule:resume")
	public ServiceResponse resume(@RequestBody Integer[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(ScheduleJob scheduleJob){
		if(StringUtils.isBlank(scheduleJob.getBeanName())){
			throw new RRException("bean名称不能为空");
		}
		
		if(StringUtils.isBlank(scheduleJob.getMethodName())){
			throw new RRException("方法名称不能为空");
		}
		
		if(StringUtils.isBlank(scheduleJob.getCronExpression())){
			throw new RRException("cron表达式不能为空");
		}
	}
}
