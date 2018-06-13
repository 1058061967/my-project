package com.manage.filter;

import com.manage.model.SearchFilter;

public class ScheduleJobFilter extends SearchFilter{
	
	private String beanName;

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		if(beanName != null) {
			beanName = "%" + beanName + "%";
		}
		this.beanName = beanName;
	}
}
