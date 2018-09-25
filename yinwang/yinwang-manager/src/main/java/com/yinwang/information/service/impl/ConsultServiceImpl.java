package com.yinwang.information.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinwang.information.dao.ConsultDao;
import com.yinwang.information.dao.ConsultPlotDao;
import com.yinwang.information.domain.ConsultDO;
import com.yinwang.information.domain.ConsultPlotDO;
import com.yinwang.information.service.ConsultService;



@Service
public class ConsultServiceImpl implements ConsultService {
	@Autowired
	private ConsultDao consultDao;
	@Autowired
	private ConsultPlotDao consultPlotDao;
	
	@Override
	public ConsultDO get(Integer id){
		return consultDao.get(id);
	}
	
	@Override
	public List<ConsultDO> list(Map<String, Object> map){
		return consultDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return consultDao.count(map);
	}
	
	@Override
	public int save(ConsultDO consult){
		int id = consultDao.consultId();
		consult.setId(id);
		if(StringUtils.isNotBlank(consult.getForIds())){
			String[] ids = consult.getForIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				ConsultPlotDO msgUser = new ConsultPlotDO((long)id,Long.parseLong(ids[i]));
				consultPlotDao.save(msgUser);
			}
		}
		return consultDao.save(consult);
	}
	
	@Override
	public int update(ConsultDO consult){
		ConsultDO con = consultDao.get(consult.getId());
		if(StringUtils.isNotBlank(consult.getForIds())){
			consultPlotDao.removePlot(Long.parseLong(consult.getId().toString()));
			String[] ids = consult.getForIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				ConsultPlotDO msgUser = new ConsultPlotDO((long)consult.getId(),Long.parseLong(ids[i]));
				consultPlotDao.save(msgUser);
			}
		}
		return consultDao.update(consult);
	}
	public Long StrToInt(String str){
		return Long.parseLong(str);
	}
	 
	@Override
	public int remove(Integer id){
		return consultDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return consultDao.batchRemove(ids);
	}
	
	@Override
	public List<Map<String,Object>> getPlotList(Map<String,Object> map){
		return consultDao.getPlotList(map);
	}
}
