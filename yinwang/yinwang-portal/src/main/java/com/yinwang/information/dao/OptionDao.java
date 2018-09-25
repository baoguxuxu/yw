package com.yinwang.information.dao;

import com.yinwang.information.domain.OptionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 问卷选项表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 12:58:18
 */
@Mapper
public interface OptionDao {

	OptionDO get(Integer id);
	
	List<OptionDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OptionDO option);
	
	int update(OptionDO option);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
