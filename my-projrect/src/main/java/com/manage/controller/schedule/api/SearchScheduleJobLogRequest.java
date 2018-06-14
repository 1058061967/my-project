package com.manage.controller.schedule.api;

import com.manage.model.PagingRequest;

public class SearchScheduleJobLogRequest extends PagingRequest{
	
	private  Integer jobId;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
}
