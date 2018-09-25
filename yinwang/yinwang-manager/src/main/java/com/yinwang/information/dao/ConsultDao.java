package com.yinwang.information.dao;

import com.yinwang.information.domain.ConsultDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 咨询表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-13 14:42:52
 */
@Mapper
public interface ConsultDao {

	ConsultDO get(Integer id);
	
	List<ConsultDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ConsultDO consult);
	
	int update(ConsultDO consult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int consultId();
	
	List<Map<String,Object>> getPlotList(Map<String,Object> map);
}
