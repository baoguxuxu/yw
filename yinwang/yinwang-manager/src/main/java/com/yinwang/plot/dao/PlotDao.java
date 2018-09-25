package com.yinwang.plot.dao;

import com.yinwang.plot.domain.PlotDO;
import com.yinwang.plot.domain.TArea;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 小区表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-13 15:26:20
 */
@Mapper
public interface PlotDao {

	PlotDO get(Long id);
	
	List<PlotDO> list(Map<String,Object> map);
	
	List<TArea> listArea(TArea tare);
	
	int count(Map<String,Object> map);
	
	int save(PlotDO plot);
	
	int update(PlotDO plot);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
