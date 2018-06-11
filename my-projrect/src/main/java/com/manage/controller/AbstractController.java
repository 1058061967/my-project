package com.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manage.entity.SysUserEntity;
import com.manage.utils.ShiroUtils;


public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
