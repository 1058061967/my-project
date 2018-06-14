package com.manage.controller.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manage.entity.SysUser;
import com.manage.utils.ShiroUtils;


public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUser getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Integer getUserId() {
		return getUser().getUserId();
	}
}
