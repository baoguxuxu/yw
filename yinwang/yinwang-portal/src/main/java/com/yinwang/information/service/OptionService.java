package com.yinwang.information.service;

import com.yinwang.information.domain.OptionDO;

import java.util.List;
import java.util.Map;

/**
 * 问卷选项表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 12:58:18
 */
public interface OptionService {
	
	OptionDO get(Integer id);
	
	List<OptionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OptionDO option);
	
	int update(OptionDO option);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
