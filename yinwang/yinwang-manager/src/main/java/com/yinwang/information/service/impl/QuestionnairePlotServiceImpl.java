package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.QuestionnairePlotDao;
import com.yinwang.information.domain.QuestionnairePlotDO;
import com.yinwang.information.service.QuestionnairePlotService;



@Service
public class QuestionnairePlotServiceImpl implements QuestionnairePlotService {
	@Autowired
	private QuestionnairePlotDao questionnairePlotDao;
	
	@Override
	public QuestionnairePlotDO get(Long id){
		return questionnairePlotDao.get(id);
	}
	
	@Override
	public List<QuestionnairePlotDO> list(Map<String, Object> map){
		return questionnairePlotDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return questionnairePlotDao.count(map);
	}
	
	@Override
	public int save(QuestionnairePlotDO questionnairePlot){
		return questionnairePlotDao.save(questionnairePlot);
	}
	
	@Override
	public int update(QuestionnairePlotDO questionnairePlot){
		return questionnairePlotDao.update(questionnairePlot);
	}
	
	@Override
	public int remove(Long id){
		return questionnairePlotDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return questionnairePlotDao.batchRemove(ids);
	}
	
}
