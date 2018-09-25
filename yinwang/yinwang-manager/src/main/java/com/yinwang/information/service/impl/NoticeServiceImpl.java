package com.yinwang.information.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.NoticeDao;
import com.yinwang.information.dao.NoticePlotDao;
import com.yinwang.information.domain.MsgUserDO;
import com.yinwang.information.domain.NoticeDO;
import com.yinwang.information.domain.NoticePlotDO;
import com.yinwang.information.service.NoticeService;



@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private NoticePlotDao noticePlotDao;
	
	
	@Override
	public NoticeDO get(Integer id){
		return noticeDao.get(id);
	}
	
	@Override
	public List<NoticeDO> list(Map<String, Object> map){
		return noticeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return noticeDao.count(map);
	}
	
	@Override
	public int save(NoticeDO notice){
		int id = noticeDao.noticeId();
		notice.setId(id);
		if(StringUtils.isNotBlank(notice.getForIds())){
			String[] ids = notice.getForIds().split(",");
			List<String> result = new ArrayList<>();  
			boolean flag;  
			for(int i=0;i<ids.length;i++){  
			flag = false;  
			for(int j=0;j<result.size();j++){  
			  if(ids[i].equals(result.get(j))){  
			      flag = true;  
			      break;  
			  }  
			}  
			if(!flag){  
			  result.add(ids[i]);  
			}  
			}  
			String[] arrayResult = (String[]) result.toArray(new String[result.size()]);  
			System.out.println(Arrays.toString(arrayResult)); 
			String[] userIds = arrayResult;
			for (int i = 0; i < userIds.length; i++) {
				NoticePlotDO msgUser = new NoticePlotDO((long)id,Long.parseLong(userIds[i]));
				noticePlotDao.save(msgUser);
			}
			notice.setForNum(userIds.length);
		}
		return noticeDao.save(notice);
	}
	
	@Override
	public int update(NoticeDO notice){
		return noticeDao.update(notice);
	}
	
	@Override
	public int remove(Integer id){
		return noticeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return noticeDao.batchRemove(ids);
	}
	
}
