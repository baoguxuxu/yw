package com.yinwang.information.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinwang.common.utils.MD5Utils;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.information.domain.RepairUserDO;
import com.yinwang.information.service.RepairUserService;

/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-06-05 16:39:40
 */
 
@Controller
@RequestMapping("/information/repairUser")
public class RepairUserController {
	@Autowired
	private RepairUserService repairUserService;
	
	@GetMapping()
	@RequiresPermissions("information:repairUser:repairUser")
	String RepairUser(){
	    return "information/repairUser/repairUser";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:repairUser:repairUser")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RepairUserDO> repairUserList = repairUserService.list(query);
		int total = repairUserService.count(query);
		PageUtils pageUtils = new PageUtils(repairUserList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:repairUser:add")
	String add(){
	    return "information/repairUser/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:repairUser:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		RepairUserDO repairUser = repairUserService.get(id);
		model.addAttribute("repairUser", repairUser);
	    return "information/repairUser/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:repairUser:add")
	public R save( RepairUserDO repairUser){
		repairUser.setPassword(MD5Utils.encrypt(repairUser.getPhone(), repairUser.getPassword()));
		repairUser.setNickname(repairUser.getName());
		repairUser.setAddTime(new Date());
		repairUser.setUsername(repairUser.getPhone());
		repairUser.setDeleteFlag(0);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("phone", repairUser.getPhone());
		List<RepairUserDO> list = this.repairUserService.list(map);
		if(list.size() > 0){
			return R.error("该手机号已存在");
		}
		if(repairUserService.save(repairUser)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:repairUser:edit")
	public R update( RepairUserDO repairUser){
		repairUser.setPassword(MD5Utils.encrypt(repairUser.getPhone(), repairUser.getPassword()));
		repairUser.setNickname(repairUser.getName());
		repairUser.setUsername(repairUser.getPhone());
		repairUser.setDeleteFlag(0);
		repairUserService.update(repairUser);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:repairUser:remove")
	public R remove( Integer id){
		if(repairUserService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:repairUser:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		repairUserService.batchRemove(ids);
		return R.ok();
	}
	
}
