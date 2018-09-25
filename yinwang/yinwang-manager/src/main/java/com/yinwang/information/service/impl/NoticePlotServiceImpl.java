package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.NoticePlotDao;
import com.yinwang.information.domain.NoticePlotDO;
import com.yinwang.information.service.NoticePlotService;



@Service
public class NoticePlotServiceImpl implements NoticePlotService {
	@Autowired
	private NoticePlotDao noticePlotDao;
	
	@Override
	public NoticePlotDO get(Long id){
		return noticePlotDao.get(id);
	}
	
	@Override
	public List<NoticePlotDO> list(Map<String, Object> map){
		return noticePlotDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return noticePlotDao.count(map);
	}
	
	@Override
	public int save(NoticePlotDO noticePlot){
		return noticePlotDao.save(noticePlot);
	}
	
	@Override
	public int update(NoticePlotDO noticePlot){
		return noticePlotDao.update(noticePlot);
	}
	
	@Override
	public int remove(Long id){
		return noticePlotDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return noticePlotDao.batchRemove(ids);
	}
	
}
