package com.yinwang.system.service;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.yinwang.owneruser.domain.RepairUserDO;
import com.yinwang.system.domain.UserOnline;

@Service
public interface SessionService {
	List<UserOnline> list();

	List<RepairUserDO> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
