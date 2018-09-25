package com.yinwang.information.dao;

import com.yinwang.information.domain.RepairDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 维修表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-25 16:17:51
 */
@Mapper
public interface RepairDao {

	RepairDO get(Long id);
	
	List<RepairDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RepairDO repair);
	
	int update(RepairDO repair);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	int repairId();
	
	List<Map<String, Object>> listMap(Map<String,Object> map);
}
