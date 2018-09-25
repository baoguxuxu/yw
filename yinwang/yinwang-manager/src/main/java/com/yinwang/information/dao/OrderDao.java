package com.yinwang.information.dao;

import com.yinwang.information.domain.OrderDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 支付订单
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-08-05 11:08:57
 */
@Mapper
public interface OrderDao {

	OrderDO get(Integer id);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<Map<String, Object>> listData(Map<String, Object> map);

	int countData(Map<String, Object> map);
}
