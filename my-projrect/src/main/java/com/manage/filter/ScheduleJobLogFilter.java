package com.manage.filter;

import com.manage.model.SearchFilter;

public class ScheduleJobLogFilter extends SearchFilter{
	
	private Integer jobId;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
}
