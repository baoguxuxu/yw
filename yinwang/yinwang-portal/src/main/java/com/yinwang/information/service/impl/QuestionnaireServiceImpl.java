package com.yinwang.information.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinwang.common.utils.ShiroUtils;
import com.yinwang.information.dao.QueAnswerDao;
import com.yinwang.information.dao.QueCompleteDao;
import com.yinwang.information.dao.QuestionnaireDao;
import com.yinwang.information.dao.TopicDao;
import com.yinwang.information.domain.QueAnswerDO;
import com.yinwang.information.domain.QueCompleteDO;
import com.yinwang.information.domain.QuestionnaireDO;
import com.yinwang.information.domain.TopicDO;
import com.yinwang.information.service.QuestionnaireService;
import com.yinwang.owneruser.domain.OwnerUserDO;



@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
	@Autowired
	private QuestionnaireDao questionnaireDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private QueAnswerDao queAnswerDao;
	@Autowired
	private QueCompleteDao queCompleteDao;
	
	@Override
	public QuestionnaireDO get(Integer id){
		return questionnaireDao.get(id);
	}
	
	@Override
	public List<QuestionnaireDO> list(Map<String, Object> map){
		return questionnaireDao.list(map);
	}

	@Override
	public boolean saveWen(OwnerUserDO user,Map<Integer, String> map) {
		boolean falg = false;
		
		try {
			Set<Integer> keySet = map.keySet();
			Iterator<Integer> iter = keySet.iterator();
			Long questionnaireId = null;
			while (iter.hasNext()) {
				Integer key = iter.next();
				String val = map.get(key);
				System.out.println("zhiconval:"+val);
				TopicDO top = topicDao.get(key);
				questionnaireId = Long.parseLong(top.getQuestionnaireId().toString());
				//0：单选；1：多选;2:填写
				if(top.getIsRadio() == 0){
					QueAnswerDO queAnswer = new QueAnswerDO(user.getId(), Long.parseLong(key.toString()),  Long.parseLong(val), val);
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("user_id", user.getId());
					param.put("topic_id", key);
					queAnswerDao.removeTop(param);
					queAnswerDao.save(queAnswer);
				}
				if(top.getIsRadio() == 1){
					String[] ids = val.split(",");
					if(ids.length > 0){
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("user_id", user.getId());
						param.put("topic_id", key);
						queAnswerDao.removeTop(param);
						for (int i = 0; i < ids.length; i++) {
							if(StringUtils.isNotBlank(ids[i])){
								QueAnswerDO queAnswer = new QueAnswerDO(user.getId(), Long.parseLong(key.toString()),  Long.parseLong(ids[i]), ids[i]);
								queAnswerDao.save(queAnswer);
							}
						}
					}
				}
				if(top.getIsRadio() == 2){
					QueAnswerDO queAnswer = new QueAnswerDO(user.getId(), Long.parseLong(key.toString()), null, val);
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("user_id", user.getId());
					param.put("topic_id", key);
					queAnswerDao.removeTop(param);
					queAnswerDao.save(queAnswer);
				}
			}
			falg = true;
		} catch (InvalidSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return falg;
	}
	@Override
	public boolean saveWen1(OwnerUserDO user,Map<Integer, String> map) {
		boolean falg = false;
		
		try {
			Set<Integer> keySet = map.keySet();
			Iterator<Integer> iter = keySet.iterator();
			Long questionnaireId = null;
			while (iter.hasNext()) {
				Integer key = iter.next();
				String val = map.get(key);
				System.out.println("zhiconval:"+val);
				TopicDO top = topicDao.get(key);
				questionnaireId = Long.parseLong(top.getQuestionnaireId().toString());
				//0：单选；1：多选;2:填写
				if(top.getIsRadio() == 0){
					QueAnswerDO queAnswer = new QueAnswerDO(user.getId(), Long.parseLong(key.toString()),  Long.parseLong(val), val);
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("user_id", user.getId());
					param.put("topic_id", key);
					queAnswerDao.removeTop(param);
					queAnswerDao.save(queAnswer);
				}
				if(top.getIsRadio() == 1){
					String[] ids = val.split(",");
					if(ids.length > 0){
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("user_id", user.getId());
						param.put("topic_id", key);
						queAnswerDao.removeTop(param);
						for (int i = 0; i < ids.length; i++) {
							if(StringUtils.isNotBlank(ids[i])){
								QueAnswerDO queAnswer = new QueAnswerDO(user.getId(), Long.parseLong(key.toString()),  Long.parseLong(ids[i]), ids[i]);
								queAnswerDao.save(queAnswer);
							}
						}
					}
				}
				if(top.getIsRadio() == 2){
					QueAnswerDO queAnswer = new QueAnswerDO(user.getId(), Long.parseLong(key.toString()), null, val);
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("user_id", user.getId());
					param.put("topic_id", key);
					queAnswerDao.removeTop(param);
					queAnswerDao.save(queAnswer);
				}
			}
			QueCompleteDO queComplete = new QueCompleteDO(user.getId(), questionnaireId);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("user_id", user.getId());
			param.put("questionnaire_id", questionnaireId);
			queCompleteDao.removeTop(param);
			System.out.println("questionnaireId:===="+questionnaireId);
			queCompleteDao.save(queComplete);
			falg = true;
		} catch (InvalidSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public List<Map<String, Object>> myQuestionnaire(Map<String, Object> map) {
		return questionnaireDao.myQuestionnaire(map);
	}
	
}
