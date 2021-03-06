package com.yinwang.information.service;

import com.yinwang.information.domain.MaintainTypeDO;

import java.util.List;
import java.util.Map;

/**
 * 维修类型类别表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-23 15:11:36
 */
public interface MaintainTypeService {
	
	MaintainTypeDO get(Long id);
	
	List<MaintainTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MaintainTypeDO maintainType);
	
	int update(MaintainTypeDO maintainType);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	public List<Map<String,Object>> listMap(Map<String,Object> map);
}
