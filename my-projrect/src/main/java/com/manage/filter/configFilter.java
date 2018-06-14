package com.manage.filter;

import com.manage.model.SearchFilter;

public class configFilter extends SearchFilter{
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		if(key != null) {
			key = "%" + key + "%";
		}
		this.key = key;
	}
}
