package com.yinwang.information.dao;

import com.yinwang.information.domain.QueAnswerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 问卷答案表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 13:47:58
 */
@Mapper
public interface QueAnswerDao {

	QueAnswerDO get(Long id);
	
	List<QueAnswerDO> list(Map<String,Object> map);
	
	QueAnswerDO getQueAnswer(Map<String,Object> map);
	
	List<QueAnswerDO> getQueAnswerList(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(QueAnswerDO queAnswer);
	
	int update(QueAnswerDO queAnswer);
	
	int remove(Long id);
	int removeTop(Map<String,Object> map);
	
	int batchRemove(Long[] ids);
}
