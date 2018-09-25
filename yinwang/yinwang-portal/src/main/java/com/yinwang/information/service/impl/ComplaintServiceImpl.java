package com.yinwang.information.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yinwang.common.config.BootdoConfig;
import com.yinwang.common.utils.FileUtil;
import com.yinwang.common.utils.R;
import com.yinwang.information.dao.ComplaintDao;
import com.yinwang.information.dao.FilesDao;
import com.yinwang.information.domain.ComplaintDO;
import com.yinwang.information.domain.FilesDO;
import com.yinwang.information.service.ComplaintService;



@Service
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	private ComplaintDao complaintDao;
	@Autowired
	private FilesDao filesDao;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	
	
	
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
		try {
			int id = complaintDao.complaintId();
			if(complaint.getListImg() != null && complaint.getListImg().length > 0){
				System.out.println("=============长度"+complaint.getListImg().length);
				for (int i = 0; i < complaint.getListImg().length-1; i++) {
					MultipartFile sysFile = complaint.getListImg()[i];
					String fileName = sysFile.getOriginalFilename();
					fileName = FileUtil.renameToUUID(fileName);
					FileUtil.uploadFile(sysFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
					FilesDO file = new FilesDO(1, id, "/files/" + fileName);
					filesDao.save(file);
				}
			}
			complaint.setId(Long.parseLong(id+""));
			complaint.setStatue(0);
			complaint.setAddTime(new Date());
			return complaintDao.save(complaint);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
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

	@Override
	public String getPlotPhone(Long id) {
		return complaintDao.getPlotPhone(id);
	}
	
}
