package com.manage.utils;

import java.util.HashMap;
import java.util.Map;

public class ServiceResponse extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public ServiceResponse() {
		put("code", 0);
	}
	
	public static ServiceResponse error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ServiceResponse error(String msg) {
		return error(500, msg);
	}
	
	public static ServiceResponse error(int code, String msg) {
		ServiceResponse r = new ServiceResponse();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ServiceResponse ok(String msg) {
		ServiceResponse r = new ServiceResponse();
		r.put("msg", msg);
		return r;
	}
	
	public static ServiceResponse ok(Map<String, Object> map) {
		ServiceResponse r = new ServiceResponse();
		r.putAll(map);
		return r;
	}
	
	public static ServiceResponse ok() {
		return new ServiceResponse();
	}

	public ServiceResponse put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
