package com.yinwang.plot.service;

import com.yinwang.plot.domain.PlotDO;
import com.yinwang.plot.domain.TArea;

import java.util.List;
import java.util.Map;

/**
 * 小区表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-13 15:26:20
 */
public interface PlotService {
	
	PlotDO get(Long id);
	
	List<PlotDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PlotDO plot);
	
	int update(PlotDO plot);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	//地区下拉框事件数据
	public String selectAreaDate();
	
	List<TArea> findArea(Integer level, Integer parentCode);
}
