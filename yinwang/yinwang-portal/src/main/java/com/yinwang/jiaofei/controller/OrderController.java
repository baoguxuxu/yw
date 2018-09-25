package com.yinwang.jiaofei.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinwang.common.controller.BaseController;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.jiaofei.service.OrderService;
import com.yinwang.jiaofei.vo.OrderDO;



/**
 * 支付订单
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-08-05 11:08:57
 */
 
@Controller
@RequestMapping("/information/order")
public class OrderController extends BaseController  {
	@Autowired
	private OrderService orderService;
	
	@GetMapping()
	public String Order(Model model){
		//查询列表数据
		List<Map<String, Object>> orderList = orderService.listbyid(getUserId());
		String sum = orderService.getsum(getUserId());
		model.addAttribute("sum", sum);
		model.addAttribute("orderList", orderList);
		 return "order/order";
	}
	
	@GetMapping("/add")
	String add(){
	    return "order/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		OrderDO order = orderService.get(id);
		model.addAttribute("order", order);
	    return "order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(orderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}
	
}
