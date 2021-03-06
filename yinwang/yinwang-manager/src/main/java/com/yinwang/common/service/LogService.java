package com.yinwang.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yinwang.common.domain.LogDO;
import com.yinwang.common.domain.PageDO;
import com.yinwang.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
