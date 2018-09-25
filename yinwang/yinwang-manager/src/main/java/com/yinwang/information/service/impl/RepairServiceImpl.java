package com.yinwang.information.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinwang.information.dao.FilesDao;
import com.yinwang.information.dao.RepairDao;
import com.yinwang.information.domain.FilesDO;
import com.yinwang.information.domain.RepairDO;
import com.yinwang.information.service.RepairService;



@Service
public class RepairServiceImpl implements RepairService {
	@Autowired
	private RepairDao repairDao;
	@Autowired
	private FilesDao filesDao;
	
	@Override
	public RepairDO get(Long id){
		return repairDao.get(id);
	}
	@Override
	public Map<String,Object> getRepair(Long id){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("type", "2");
		param.put("tableId", id);
		List<FilesDO> listImg = filesDao.list(param);
		Map<String,Object> map = repairDao.getRepair(id);
		map.put("listImg", listImg);
		return map;
	}
	
	@Override
	public List<Map<String,Object>> list(Map<String, Object> map){
		return repairDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return repairDao.count(map);
	}
	
	@Override
	public int save(RepairDO repair){
		return repairDao.save(repair);
	}
	
	@Override
	public int update(RepairDO repair){
		return repairDao.update(repair);
	}
	
	@Override
	public int remove(Long id){
		return repairDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return repairDao.batchRemove(ids);
	}
	@Override
	public List<Map<String, Object>> listMap(Map<String,Object> map){
		return repairDao.listMap(map);
	}
}
