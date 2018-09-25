package com.yinwang.information.service;

import com.yinwang.information.domain.QuestionnaireDO;

import java.util.List;
import java.util.Map;

/**
 * 问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 10:11:20
 */
public interface QuestionnaireService {
	
	QuestionnaireDO get(Integer id);
	
	List<QuestionnaireDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	int countOption(Integer id);
	int counttopic(Integer id);
	
	
	int save(QuestionnaireDO questionnaire);
	
	int update(QuestionnaireDO questionnaire);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> getPlotList(Map<String,Object> map);
}
