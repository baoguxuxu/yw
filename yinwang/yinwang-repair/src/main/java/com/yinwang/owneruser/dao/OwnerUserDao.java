package com.yinwang.owneruser.dao;

import com.yinwang.owneruser.domain.RepairUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author tmn
 */
@Mapper
public interface OwnerUserDao {

	RepairUserDO get(Long userId);
	
	RepairUserDO getbyname(String username);
	
	List<RepairUserDO> list(Map<String,Object> map);
	
	int save(RepairUserDO user);
	
	int count(Map<String,Object> map);
	
	int update(RepairUserDO user);
	
	int remove(Long userId);
	

}
