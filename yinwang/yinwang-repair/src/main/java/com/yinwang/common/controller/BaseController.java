package com.yinwang.common.controller;

import org.springframework.stereotype.Controller;

import com.yinwang.common.utils.ShiroUtils;
import com.yinwang.owneruser.domain.RepairUserDO;
import com.yinwang.system.domain.UserToken;

@Controller
public class BaseController {
	public RepairUserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}