package com.yinwang.information.dao;

import com.yinwang.information.domain.NoticePlotDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 公告小区中间表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-20 09:18:37
 */
@Mapper
public interface NoticePlotDao {

	NoticePlotDO get(Long id);
	
	List<NoticePlotDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(NoticePlotDO noticePlot);
	
	int update(NoticePlotDO noticePlot);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
