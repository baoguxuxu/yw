package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.ConsultPlotDao;
import com.yinwang.information.domain.ConsultPlotDO;
import com.yinwang.information.service.ConsultPlotService;



@Service
public class ConsultPlotServiceImpl implements ConsultPlotService {
	@Autowired
	private ConsultPlotDao consultPlotDao;
	
	@Override
	public ConsultPlotDO get(Long id){
		return consultPlotDao.get(id);
	}
	
	@Override
	public List<ConsultPlotDO> list(Map<String, Object> map){
		return consultPlotDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return consultPlotDao.count(map);
	}
	
	@Override
	public int save(ConsultPlotDO consultPlot){
		return consultPlotDao.save(consultPlot);
	}
	
	@Override
	public int update(ConsultPlotDO consultPlot){
		return consultPlotDao.update(consultPlot);
	}
	
	@Override
	public int remove(Long id){
		return consultPlotDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return consultPlotDao.batchRemove(ids);
	}
	
}
