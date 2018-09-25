package com.yinwang.information.dao;

import com.yinwang.information.domain.QueCompleteDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 已完成问卷调查表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 18:17:53
 */
@Mapper
public interface QueCompleteDao {

	QueCompleteDO get(Long id);
	
	List<QueCompleteDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(QueCompleteDO queComplete);
	
	int update(QueCompleteDO queComplete);
	
	int remove(Long id);
	int removeTop(Map<String,Object> map);
	
	int batchRemove(Long[] ids);
}
