package com.yinwang.information.dao;

import com.yinwang.information.domain.FilesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 附件表，用于存储附件的关联表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-19 14:23:59
 */
@Mapper
public interface FilesDao {

	FilesDO get(Integer id);
	
	List<FilesDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FilesDO files);
	
	int update(FilesDO files);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
