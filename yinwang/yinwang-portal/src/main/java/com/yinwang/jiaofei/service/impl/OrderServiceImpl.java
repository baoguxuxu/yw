package com.yinwang.jiaofei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinwang.jiaofei.dao.OrderDao;
import com.yinwang.jiaofei.service.OrderService;
import com.yinwang.jiaofei.vo.OrderDO;
import com.yinwang.owneruser.domain.UserPlotDO;

import java.util.List;
import java.util.Map;




@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderDO get(Integer id){
		return orderDao.get(id);
	}
	@Override
	public 	OrderDO getbyno(String tno){
		return orderDao.getbyno(tno);
	}
	@Override
	public String getsum(Long userId){
		return orderDao.getsum(userId);
	}
	
	@Override
	public List<OrderDO> list(OrderDO odo){
		return orderDao.list(odo);
	}
	
    @Override
    public List<Map<String, Object>> listbyid(Long userId) {
    	List<Map<String, Object>> upls = orderDao.listbyid(userId);
    	return upls;
    }
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	public int save(OrderDO order){
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
	
}
