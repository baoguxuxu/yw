package com.yinwang.information.dao;

import com.yinwang.information.domain.QuestionnaireDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 问卷表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 10:11:20
 */
@Mapper
public interface QuestionnaireDao {

	QuestionnaireDO get(Integer id);
	
	List<QuestionnaireDO> list(Map<String,Object> map);
	
	/**
	 * 查询用户参与的调查问卷  
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> myQuestionnaire(Map<String,Object> map);
}
