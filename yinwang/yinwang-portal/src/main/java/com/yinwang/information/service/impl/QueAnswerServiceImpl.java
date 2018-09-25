package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.QueAnswerDao;
import com.yinwang.information.domain.QueAnswerDO;
import com.yinwang.information.service.QueAnswerService;



@Service
public class QueAnswerServiceImpl implements QueAnswerService {
	@Autowired
	private QueAnswerDao queAnswerDao;
	
	@Override
	public QueAnswerDO get(Long id){
		return queAnswerDao.get(id);
	}
	
	@Override
	public List<QueAnswerDO> list(Map<String, Object> map){
		return queAnswerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return queAnswerDao.count(map);
	}
	
	@Override
	public int save(QueAnswerDO queAnswer){
		return queAnswerDao.save(queAnswer);
	}
	
	@Override
	public int update(QueAnswerDO queAnswer){
		return queAnswerDao.update(queAnswer);
	}
	
	@Override
	public int remove(Long id){
		return queAnswerDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return queAnswerDao.batchRemove(ids);
	}
	

	@Override
	public QueAnswerDO getQueAnswer(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return queAnswerDao.getQueAnswer(map);
	}

	@Override
	public List<QueAnswerDO> getQueAnswerList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return queAnswerDao.getQueAnswerList(map);
	}
	
}
