package com.yinwang.information.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinwang.information.dao.QuestionnaireDao;
import com.yinwang.information.dao.QuestionnairePlotDao;
import com.yinwang.information.domain.ConsultDO;
import com.yinwang.information.domain.ConsultPlotDO;
import com.yinwang.information.domain.QuestionnaireDO;
import com.yinwang.information.domain.QuestionnairePlotDO;
import com.yinwang.information.service.QuestionnaireService;



@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
	@Autowired
	private QuestionnaireDao questionnaireDao;
	@Autowired
	private QuestionnairePlotDao questionnairePlotDao;
	
	
	
	
	@Override
	public QuestionnaireDO get(Integer id){
		return questionnaireDao.get(id);
	}
	
	@Override
	public List<QuestionnaireDO> list(Map<String, Object> map){
		return questionnaireDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return questionnaireDao.count(map);
	}
	
	@Override
	public int countOption(Integer id){
		return questionnaireDao.countOption(id);
	}
	@Override
	public int counttopic(Integer id){
		return questionnaireDao.counttopic(id);
	}
	
	@Override
	public int save(QuestionnaireDO questionnaire){
		int id = questionnaireDao.questionnaireId();
		questionnaire.setId(id);
		if(StringUtils.isNotBlank(questionnaire.getForIds())){
			String[] ids = questionnaire.getForIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				QuestionnairePlotDO msgUser = new QuestionnairePlotDO((long)id,Long.parseLong(ids[i]));
				questionnairePlotDao.save(msgUser);
			}
		}
		return questionnaireDao.save(questionnaire);
	}
	
	@Override
	public int update(QuestionnaireDO questionnaire){
		QuestionnaireDO con = questionnaireDao.get(questionnaire.getId());
		if(StringUtils.isNotBlank(questionnaire.getForIds())){
			questionnairePlotDao.removePlot(Long.parseLong(questionnaire.getId().toString()));
			String[] ids = questionnaire.getForIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				QuestionnairePlotDO msgUser = new QuestionnairePlotDO((long)questionnaire.getId(),Long.parseLong(ids[i]));
				questionnairePlotDao.save(msgUser);
			}
		}
		return questionnaireDao.update(questionnaire);
	}
	
	@Override
	public int remove(Integer id){
		return questionnaireDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return questionnaireDao.batchRemove(ids);
	}
	
	@Override
	public List<Map<String,Object>> getPlotList(Map<String,Object> map){
		return questionnaireDao.getPlotList(map);
	}
}
