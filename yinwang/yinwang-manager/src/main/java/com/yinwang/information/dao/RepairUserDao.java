package com.yinwang.information.dao;

import com.yinwang.information.domain.RepairUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-06-05 16:39:40
 */
@Mapper
public interface RepairUserDao {

	RepairUserDO get(Integer id);
	
	List<RepairUserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RepairUserDO repairUser);
	
	int update(RepairUserDO repairUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
