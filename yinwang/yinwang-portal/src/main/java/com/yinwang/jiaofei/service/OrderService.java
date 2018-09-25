package com.yinwang.jiaofei.service;


import java.util.List;
import java.util.Map;

import com.yinwang.jiaofei.vo.OrderDO;

/**
 * 支付订单
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-08-05 11:08:57
 */
public interface OrderService {
	
	OrderDO get(Integer id);
	
	OrderDO getbyno(String tno);
	
	String getsum(Long userId);
	
	List<OrderDO> list(OrderDO odo);
	
	List<Map<String, Object>> listbyid(Long userId);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
