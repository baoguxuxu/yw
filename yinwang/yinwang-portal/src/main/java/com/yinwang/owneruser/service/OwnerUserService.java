package com.yinwang.owneruser.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.yinwang.common.domain.Tree;
import com.yinwang.owneruser.domain.OwnerUserDO;

import org.springframework.web.multipart.MultipartFile;

@Service
public interface OwnerUserService {
	OwnerUserDO get(Long id);
	
	OwnerUserDO getbyname(String username);

	List<OwnerUserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(OwnerUserDO user);

	int update(OwnerUserDO user);

	int remove(Long userId);

	boolean exit(Map<String, Object> params);

}
