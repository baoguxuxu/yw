package com.yinwang.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import com.yinwang.information.dao.OrderDao;
import com.yinwang.information.domain.OrderDO;
import com.yinwang.information.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderDO get(Integer id){
		return orderDao.get(id);
	}
	
	@Override
	public List<OrderDO> list(Map<String, Object> map){
		return orderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	public int save(OrderDO order){
		Date currentTime =new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderNo ="yw"+ formatter.format(currentTime);
		order.setOrderNo(orderNo);
		order.setCreateTime(new Date());
		return orderDao.save(order);
	}
	
	@Override
	public int update(OrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(Integer id){
		return orderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> listData(Map<String, Object> map){
		return orderDao.listData(map);
	}

	@Override
	public int countData(Map<String, Object> map){
		return orderDao.countData(map);
	}


}
