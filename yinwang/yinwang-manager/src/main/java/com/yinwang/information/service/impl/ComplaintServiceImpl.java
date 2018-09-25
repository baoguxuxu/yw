package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.ComplaintDao;
import com.yinwang.information.domain.ComplaintDO;
import com.yinwang.information.service.ComplaintService;



@Service
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	private ComplaintDao complaintDao;
	
	@Override
	public ComplaintDO get(Long id){
		return complaintDao.get(id);
	}
	
	@Override
	public List<Map> list(Map<String, Object> map){
		return complaintDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return complaintDao.count(map);
	}
	
	@Override
	public int save(ComplaintDO complaint){
		return complaintDao.save(complaint);
	}
	
	@Override
	public int update(ComplaintDO complaint){
		return complaintDao.update(complaint);
	}
	
	@Override
	public int remove(Long id){
		return complaintDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return complaintDao.batchRemove(ids);
	}
	
}
