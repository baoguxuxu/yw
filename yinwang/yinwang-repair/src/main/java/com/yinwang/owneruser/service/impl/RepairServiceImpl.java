package com.yinwang.owneruser.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yinwang.common.config.BootdoConfig;
import com.yinwang.common.utils.FileUtil;
import com.yinwang.owneruser.dao.FilesDao;
import com.yinwang.owneruser.dao.RepairDao;
import com.yinwang.owneruser.domain.FilesDO;
import com.yinwang.owneruser.domain.RepairDO;
import com.yinwang.owneruser.service.RepairService;



@Service
public class RepairServiceImpl implements RepairService {
	@Autowired
	private RepairDao repairDao;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FilesDao filesDao;
	
	
	
	@Override
	public RepairDO get(Long id){
		return repairDao.get(id);
	}
	
	@Override
	public List<RepairDO> list(Map<String, Object> map){
		return repairDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return repairDao.count(map);
	}
	
	@Override
	public int save(RepairDO repair){
		
		try {
			int id = repairDao.repairId();
			System.out.println("=============长度"+repair.getListImg().length);
			if(repair.getListImg() != null && repair.getListImg().length > 0){
				for (int i = 0; i < repair.getListImg().length-1; i++) {
					MultipartFile sysFile = repair.getListImg()[i];
					String fileName = sysFile.getOriginalFilename();
					fileName = FileUtil.renameToUUID(fileName);
					FileUtil.uploadFile(sysFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
					FilesDO file = new FilesDO(2, id, "/files/" + fileName);
					filesDao.save(file);
				}
			}
			repair.setId(Long.parseLong(id+""));
			repair.setStatue(0);
			repair.setAddTime(new Date());
			return repairDao.save(repair);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
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
	
	@Override
	public Map<String, Object> queryRepair(Map<String,Object> map){
		return repairDao.queryRepair(map);
	}
	@Override
	public Map<String, Object> queryUserRepair(Map<String,Object> map){
		return repairDao.queryUserRepair(map);
	}
	
}
