package com.yinwang.information.dao;

import com.yinwang.information.domain.MsgDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消息表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-12 16:00:47
 */
@Mapper
public interface MsgDao {

	MsgDO get(Integer id);
	
	List<MsgDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int msgId();
	
	int save(MsgDO msg);
	
	int update(MsgDO msg);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String, Object>> getUserPlotList();
}
