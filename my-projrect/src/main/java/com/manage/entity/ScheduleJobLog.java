package com.manage.entity;

import java.io.Serializable;
import java.util.Date;

import org.omg.PortableInterceptor.INACTIVE;

public class ScheduleJobLog implements Serializable {
	private static final long serialVersionUID = 1L;
	

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
	
	private Date createTime;



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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
