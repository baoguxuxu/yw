package com.yinwang.information.controller;

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

import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.information.domain.ComplaintDO;
import com.yinwang.information.service.ComplaintService;

/**
 * 投诉表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-13 14:32:12
 */
 
@Controller
@RequestMapping("/information/complaint")
public class ComplaintController {
	@Autowired
	private ComplaintService complaintService;
	
	@GetMapping()
	@RequiresPermissions("information:complaint:complaint")
	String Complaint(){
	    return "information/complaint/complaint";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:complaint:complaint")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Map> complaintList = complaintService.list(query);
		int total = complaintService.count(query);
		PageUtils pageUtils = new PageUtils(complaintList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:complaint:add")
	String add(){
	    return "information/complaint/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:complaint:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ComplaintDO complaint = complaintService.get(id);
		model.addAttribute("complaint", complaint);
	    return "information/complaint/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:complaint:add")
	public R save( ComplaintDO complaint){
		if(complaintService.save(complaint)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("information:complaint:edit")
	public R update( ComplaintDO complaint){
		complaintService.update(complaint);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:complaint:remove")
	public R remove( Long id){
		if(complaintService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:complaint:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		complaintService.batchRemove(ids);
		return R.ok();
	}
	
}
