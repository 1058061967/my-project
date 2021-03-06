package com.manage.entity;

import java.io.Serializable;
import java.util.Date;


public class ScheduleJob implements Serializable {
	private static final long serialVersionUID = 1L;
	

    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
	
	private Integer jobId;

	private String beanName;

	private String methodName;

	private String params;
	

	private String cronExpression;

	private Integer status;

	private String remark;


	private Date createTime;


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


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
