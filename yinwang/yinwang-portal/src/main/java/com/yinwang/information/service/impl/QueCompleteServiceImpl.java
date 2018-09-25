package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.QueCompleteDao;
import com.yinwang.information.domain.QueCompleteDO;
import com.yinwang.information.service.QueCompleteService;



@Service
public class QueCompleteServiceImpl implements QueCompleteService {
	@Autowired
	private QueCompleteDao queCompleteDao;
	
	@Override
	public QueCompleteDO get(Long id){
		return queCompleteDao.get(id);
	}
	
	@Override
	public List<QueCompleteDO> list(Map<String, Object> map){
		return queCompleteDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return queCompleteDao.count(map);
	}
	
	@Override
	public int save(QueCompleteDO queComplete){
		return queCompleteDao.save(queComplete);
	}
	
	@Override
	public int update(QueCompleteDO queComplete){
		return queCompleteDao.update(queComplete);
	}
	
	@Override
	public int remove(Long id){
		return queCompleteDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return queCompleteDao.batchRemove(ids);
	}
	
}
