package com.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.entity.ScheduleJob;
import com.manage.filter.ScheduleJobFilter;
import com.manage.mapper.ScheduleJobMapper;
import com.manage.model.PagingData;
import com.manage.model.PagingResult;
import com.manage.model.SearchResult;
import com.manage.service.ScheduleJobService;
import com.manage.utils.Constant.ScheduleStatus;
import com.manage.utils.ScheduleUtils;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;
	@Autowired
	private ScheduleJobMapper schedulerJobMapper;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJob> scheduleJobList = schedulerJobMapper.queryList(new HashMap<String, Object>());
		for(ScheduleJob scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}
	
	@Override
	public ScheduleJob queryObject(Integer jobId) {
		return schedulerJobMapper.queryObject(jobId);
	}

	@Override
	public List<ScheduleJob> queryList(Map<String, Object> map) {
		return schedulerJobMapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return schedulerJobMapper.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(ScheduleJob scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        schedulerJobMapper.save(scheduleJob);
        
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	@Transactional
	public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
                
        schedulerJobMapper.update(scheduleJob);
    }

	@Override
	@Transactional
    public void deleteBatch(Integer[] jobIds) {
    	for(Integer jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	
    	//删除数据
    	schedulerJobMapper.deleteBatch(jobIds);
	}

	@Override
    public int updateBatch(Integer[] jobIds, int status){
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", jobIds);
    	map.put("status", status);
    	return schedulerJobMapper.updateBatch(map);
    }
    
	@Override
	@Transactional
    public void run(Integer[] jobIds) {
    	for(Integer jobId : jobIds){
    		ScheduleUtils.run(scheduler, queryObject(jobId));
    	}
    }

	@Override
	@Transactional
    public void pause(Integer[] jobIds) {
        for(Integer jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
        
    	updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional
    public void resume(Integer[] jobIds) {
    	for(Integer jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }

	@Override
	public SearchResult<ScheduleJob> searchSchduleJobByFilter(ScheduleJobFilter filter) {
		SearchResult<ScheduleJob> result = new SearchResult<>();
		List<ScheduleJob> jobs = schedulerJobMapper.selectScheduleJobByFilter(filter);
		result.setResult(jobs);
		PagingData pagingData = filter.getPagingData();
		if(pagingData != null && filter.isPaged()){
			Integer recordNumber = schedulerJobMapper.countScheduleJobByFilter(filter);
			PagingResult pagingResult = new PagingResult(recordNumber, pagingData);
			result.setPaged(true);
			result.setPagingResult(pagingResult);
		}
		return result;
	}
	
    
}
