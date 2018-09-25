package com.yinwang.owneruser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinwang.owneruser.dao.UserPlotDao;
import com.yinwang.owneruser.domain.UserPlotDO;
import com.yinwang.owneruser.service.UserPlotService;

import java.util.List;
import java.util.Map;


@Service
public class UserPlotServiceImpl implements UserPlotService {
	@Autowired
	private UserPlotDao userPlotDao;
	
	@Override
	public UserPlotDO get(Integer id){
		return userPlotDao.get(id);
	}
	
	@Override
	public List<UserPlotDO> list(Map<String, Object> map){
		return userPlotDao.list(map);
	}
	
    @Override
    public List<UserPlotDO> listbyid(Long userId) {
    	List<UserPlotDO> upls = userPlotDao.listbyid(userId);
    	return upls;
    }
    
	@Override
	public int count(Map<String, Object> map){
		return userPlotDao.count(map);
	}
	
	@Override
	public int save(UserPlotDO userPlot){
		return userPlotDao.save(userPlot);
	}
	
	@Override
	public int update(UserPlotDO userPlot){
		return userPlotDao.update(userPlot);
	}
	
	@Override
	public int remove(Integer id){
		return userPlotDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userPlotDao.batchRemove(ids);
	}
	
}
