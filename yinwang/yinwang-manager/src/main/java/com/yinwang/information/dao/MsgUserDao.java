package com.yinwang.information.dao;

import com.yinwang.information.domain.MsgUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消息用户中间表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-16 15:52:25
 */
@Mapper
public interface MsgUserDao {

	MsgUserDO get(Long id);
	
	List<MsgUserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MsgUserDO msgUser);
	
	int update(MsgUserDO msgUser);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
}
