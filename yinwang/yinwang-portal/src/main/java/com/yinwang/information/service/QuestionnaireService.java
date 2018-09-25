package com.yinwang.information.service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.domain.QuestionnaireDO;
import com.yinwang.owneruser.domain.OwnerUserDO;

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
	
	public boolean saveWen(OwnerUserDO user,Map<Integer, String> param);
	
	public boolean saveWen1(OwnerUserDO user,Map<Integer, String> param);
	
	List<Map<String, Object>> myQuestionnaire(Map<String,Object> map);
	
}
