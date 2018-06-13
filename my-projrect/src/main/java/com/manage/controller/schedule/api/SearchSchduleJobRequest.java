package com.manage.controller.schedule.api;

import com.manage.model.PagingRequest;

public class SearchSchduleJobRequest extends PagingRequest{
	
	private String beanName;

	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
}
