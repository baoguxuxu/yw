package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.ConsultDao;
import com.yinwang.information.domain.ConsultDO;
import com.yinwang.information.service.ConsultService;



@Service
public class ConsultServiceImpl implements ConsultService {
	@Autowired
	private ConsultDao consultDao;
	
	@Override
	public ConsultDO get(Integer id){
		return consultDao.get(id);
	}
	
	@Override
	public List<Map<Object, String>> list(Map<String,Object> map){
		return consultDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return consultDao.count(map);
	}
	
	@Override
	public int save(ConsultDO consult){
		return consultDao.save(consult);
	}
	
	@Override
	public int update(ConsultDO consult){
		return consultDao.update(consult);
	}
	
	@Override
	public int remove(Integer id){
		return consultDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return consultDao.batchRemove(ids);
	}
	
}
