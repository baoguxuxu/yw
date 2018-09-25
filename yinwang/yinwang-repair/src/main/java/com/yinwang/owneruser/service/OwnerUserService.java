package com.yinwang.owneruser.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.yinwang.common.domain.Tree;
import com.yinwang.owneruser.domain.RepairUserDO;

import org.springframework.web.multipart.MultipartFile;

@Service
public interface OwnerUserService {
	RepairUserDO get(Long id);
	
	RepairUserDO getbyname(String username);

	List<RepairUserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(RepairUserDO user);

	int update(RepairUserDO user);

	int remove(Long userId);

	boolean exit(Map<String, Object> params);

}
