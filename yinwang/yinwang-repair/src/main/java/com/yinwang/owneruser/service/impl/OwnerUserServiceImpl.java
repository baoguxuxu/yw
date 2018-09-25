package com.yinwang.owneruser.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yinwang.common.config.BootdoConfig;
import com.yinwang.common.service.FileService;
import com.yinwang.owneruser.dao.OwnerUserDao;
import com.yinwang.owneruser.domain.RepairUserDO;
import com.yinwang.owneruser.service.OwnerUserService;



@Transactional
@Service
public class OwnerUserServiceImpl implements OwnerUserService {
	@Autowired
	OwnerUserDao ownerUserMapper;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
/*	private static final Logger logger = LoggerFactory.getLogger(UserService.class);*/

	@Override
	public RepairUserDO get(Long id) {
		RepairUserDO user = ownerUserMapper.get(id);
		return user;
	}
	
	@Override
	public RepairUserDO getbyname(String username){
		
		RepairUserDO user = ownerUserMapper.getbyname(username);
		return user;
	}

	@Override
	public List<RepairUserDO> list(Map<String, Object> map) {
		return ownerUserMapper.list(map);
	}

	@Override
	public int save(RepairUserDO user){
		return ownerUserMapper.save(user);
	}
	
	@Override
	public int count(Map<String, Object> map) {
		return ownerUserMapper.count(map);
	}


	@Override
	public int update(RepairUserDO user) {
		int r = ownerUserMapper.update(user);
		
		return r;
	}

	@Override
	public int remove(Long userId) {
		/*userRoleMapper.removeByUserId(userId);*/
		return ownerUserMapper.remove(userId);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = ownerUserMapper.list(params).size() > 0;
		return exit;
	}



}
