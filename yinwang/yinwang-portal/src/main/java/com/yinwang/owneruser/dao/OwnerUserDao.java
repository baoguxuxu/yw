package com.yinwang.owneruser.dao;

import com.yinwang.owneruser.domain.OwnerUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author tmn
 */
@Mapper
public interface OwnerUserDao {

	OwnerUserDO get(Long userId);
	
	OwnerUserDO getbyname(String username);
	
	List<OwnerUserDO> list(Map<String,Object> map);
	
	int save(OwnerUserDO user);
	
	int count(Map<String,Object> map);
	
	int update(OwnerUserDO user);
	
	int remove(Long userId);
	

}
