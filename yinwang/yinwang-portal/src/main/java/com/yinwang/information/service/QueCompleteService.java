package com.yinwang.information.service;

import com.yinwang.information.domain.QueCompleteDO;

import java.util.List;
import java.util.Map;

/**
 * 已完成问卷调查表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 18:17:53
 */
public interface QueCompleteService {
	
	QueCompleteDO get(Long id);
	
	List<QueCompleteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QueCompleteDO queComplete);
	
	int update(QueCompleteDO queComplete);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
