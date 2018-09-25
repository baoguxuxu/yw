package com.yinwang.information.controller;

import java.util.List;
import java.util.Map;

import com.yinwang.common.annotation.Log;
import com.yinwang.owneruser.domain.OwnerUserDO;
import com.yinwang.owneruser.service.OwnerUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinwang.information.domain.OrderDO;
import com.yinwang.information.service.OrderService;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;

/**
 * 支付订单
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-08-05 11:08:57
 */
 
@Controller
@RequestMapping("/information/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	OwnerUserService userService;

	@GetMapping("/user")
	String user(Model model) {
		return "/information/owneruser/user";
	}

	@GetMapping()
	@RequiresPermissions("information:order:order")
	String Order(){
	    return "information/order/order";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderDO> orderList = orderService.list(query);
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:order:add")
	String add(){
	    return "information/order/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:order:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OrderDO order = orderService.get(id);
		model.addAttribute("order", order);
	    return "information/order/edit";
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
	@RequiresPermissions("information:order:edit")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:order:remove")
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
	@RequiresPermissions("information:order:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/edit/{id}/{address}")
	String edit1(Model model, @PathVariable("id") Long id, @PathVariable("address") String address) {
		OwnerUserDO user = new OwnerUserDO();
		user.setId(id);
		user.setAddress(address);
		OwnerUserDO userDO = userService.getup(user);
		model.addAttribute("user", userDO);
		return "/information/owneruser/edit";
	}

	@GetMapping("/OrderData")
	String OrderData(int type,Model model){
		model.addAttribute("type",type);
		return "information/order/orderData";
	}

	@ResponseBody
	@GetMapping("/listData")
	@RequiresPermissions("information:order:order")
	public PageUtils listData(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> orderList = orderService.listData(query);
		int total = orderService.countData(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
}
