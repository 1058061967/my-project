package com.manage.controller.schedule.api;

import java.util.ArrayList;
import java.util.List;

import com.manage.entity.ScheduleJobLog;
import com.manage.utils.DateTimeUtility;

public class ScheduleJobLogVO {
	
	private Integer logId;

	private Integer jobId;

	private String beanName;

	private String methodName;

	private String params;
	
	 //任务状态    0：成功    1：失败
	private Integer status;
	
	private String error;
	
	//耗时(单位：毫秒)
	private Integer times;
	
	private String createTime;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public static ScheduleJobLogVO toVO(ScheduleJobLog log) {
		
		ScheduleJobLogVO vo = new ScheduleJobLogVO();
		vo.setBeanName(log.getBeanName());
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDD(log.getCreateTime()));
		vo.setError(log.getError());
		vo.setJobId(log.getJobId());
		vo.setLogId(log.getLogId());
		vo.setMethodName(log.getMethodName());
		vo.setParams(log.getParams());
		vo.setStatus(log.getStatus());
		vo.setTimes(log.getTimes());
		return vo;
	}
	
	
	public static List<ScheduleJobLogVO> toVOs(List<ScheduleJobLog> logs){
		List<ScheduleJobLogVO> vos = new ArrayList<>();
		for(ScheduleJobLog log :logs) {
			vos.add(toVO(log));
		}
		return vos;
	}
}
