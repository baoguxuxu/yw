package com.yinwang.information.service;

import com.yinwang.information.domain.QueAnswerDO;

import java.util.List;
import java.util.Map;

/**
 * 问卷答案表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 13:47:58
 */
public interface QueAnswerService {
	
	QueAnswerDO get(Long id);
	
	List<QueAnswerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QueAnswerDO queAnswer);
	
	int update(QueAnswerDO queAnswer);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	QueAnswerDO getQueAnswer(Map<String,Object> map);
	
	List<QueAnswerDO> getQueAnswerList(Map<String,Object> map);
	
}
