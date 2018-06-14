package com.manage.controller.schedule.api;

import java.util.ArrayList;
import java.util.List;

import com.manage.entity.ScheduleJob;
import com.manage.utils.DateTimeUtility;

import freemarker.template.LocalizedString;

public class ScheduleJobVO {
	
	private Integer jobId;

	private String beanName;

	private String methodName;

	private String params;
	

	private String cronExpression;

	private Integer status;

	private String remark;


	private String createTime;


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


	public String getCronExpression() {
		return cronExpression;
	}


	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	public static ScheduleJobVO toVO(ScheduleJob job) {
		ScheduleJobVO vo = new ScheduleJobVO();
		vo.setBeanName(job.getBeanName());
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDD(job.getCreateTime()));
		vo.setCronExpression(job.getCronExpression());
		vo.setJobId(job.getJobId());
		vo.setMethodName(job.getMethodName());
		vo.setParams(job.getParams());
		vo.setRemark(job.getRemark());
		vo.setStatus(job.getStatus());
		return vo;
	}
	
	public static List<ScheduleJobVO> toVOs(List<ScheduleJob> jobs){
		
		List<ScheduleJobVO>  vos = new ArrayList<>();
		for(ScheduleJob  job : jobs) {
			vos.add(toVO(job));
		}
		return vos;
	}

}
