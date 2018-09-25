package com.yinwang.information.service;

import com.yinwang.information.domain.ComplaintDO;

import java.util.List;
import java.util.Map;

/**
 * 投诉表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-13 14:32:12
 */
public interface ComplaintService {
	
	ComplaintDO get(Long id);
	
	List<Map> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ComplaintDO complaint);
	
	int update(ComplaintDO complaint);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	/**
	 * 根据小区ID查询小区联系人电话
	 * @param id
	 * @return
	 */
	String getPlotPhone(Long id);
}
