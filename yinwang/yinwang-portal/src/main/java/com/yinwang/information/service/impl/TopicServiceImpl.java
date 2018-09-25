package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yinwang.information.dao.TopicDao;
import com.yinwang.information.domain.TopicDO;
import com.yinwang.information.service.TopicService;



@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TopicDao topicDao;
	
	@Override
	public TopicDO get(Integer id){
		return topicDao.get(id);
	}
	
	@Override
	public List<TopicDO> list(Map<String, Object> map){
		return topicDao.list(map);
	}
	
}
