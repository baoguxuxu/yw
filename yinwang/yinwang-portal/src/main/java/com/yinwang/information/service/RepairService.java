package com.yinwang.information.service;

import com.yinwang.information.domain.RepairDO;

import java.util.List;
import java.util.Map;

/**
 * 维修表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-25 16:17:51
 */
public interface RepairService {
	
	RepairDO get(Long id);
	
	List<RepairDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RepairDO repair);
	
	int update(RepairDO repair);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	/**
	 * 我的服务
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> listMap(Map<String,Object> map);
}
