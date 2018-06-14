package com.manage.controller.config.api;

import com.manage.model.PagingRequest;

public class SearchConfigRequest extends PagingRequest{
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
