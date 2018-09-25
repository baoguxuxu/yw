package com.yinwang.plot.service;

import java.util.List;
import java.util.Map;

import com.yinwang.plot.domain.UserPlotDO;


/**
 * 用户小区表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 15:21:14
 */
public interface UserPlotService {
	
	UserPlotDO get(Integer id);
	
	List<UserPlotDO> list(Map<String, Object> map);
	
	List<UserPlotDO> listbyid(Long userId);
	
	int count(Map<String, Object> map);
	
	int save(UserPlotDO userPlot);
	
	int update(UserPlotDO userPlot);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
