package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.OptionDao;
import com.yinwang.information.domain.OptionDO;
import com.yinwang.information.service.OptionService;



@Service
public class OptionServiceImpl implements OptionService {
	@Autowired
	private OptionDao optionDao;
	
	@Override
	public OptionDO get(Integer id){
		return optionDao.get(id);
	}
	
	@Override
	public List<OptionDO> list(Map<String, Object> map){
		return optionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return optionDao.count(map);
	}
	
	@Override
	public int save(OptionDO option){
		return optionDao.save(option);
	}
	
	@Override
	public int update(OptionDO option){
		return optionDao.update(option);
	}
	
	@Override
	public int remove(Integer id){
		return optionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return optionDao.batchRemove(ids);
	}
	
}
