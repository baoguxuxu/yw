package com.yinwang.information.dao;

import com.yinwang.information.domain.RepairDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 维修表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-05-04 14:12:24
 */
@Mapper
public interface RepairDao {

	RepairDO get(Long id);
	Map<String,Object> getRepair(Long id);
	
	List<Map<String,Object>> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RepairDO repair);
	
	int update(RepairDO repair);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	List<Map<String, Object>> listMap(Map<String,Object> map);
}
