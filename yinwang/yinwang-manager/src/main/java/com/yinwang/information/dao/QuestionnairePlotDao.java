package com.yinwang.information.dao;

import com.yinwang.information.domain.QuestionnairePlotDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 问卷小区中间表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-05-30 13:40:29
 */
@Mapper
public interface QuestionnairePlotDao {

	QuestionnairePlotDO get(Long id);
	
	List<QuestionnairePlotDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(QuestionnairePlotDO questionnairePlot);
	
	int update(QuestionnairePlotDO questionnairePlot);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int removePlot(Long id);
}
