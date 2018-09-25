package com.yinwang.information.dao;

import com.yinwang.information.domain.ConsultPlotDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 资讯小区中间表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-17 16:22:30
 */
@Mapper
public interface ConsultPlotDao {

	ConsultPlotDO get(Long id);
	
	List<ConsultPlotDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ConsultPlotDO consultPlot);
	
	int update(ConsultPlotDO consultPlot);
	
	int remove(Long id);

	int removePlot(Long id);
	
	int batchRemove(Long[] ids);
}
