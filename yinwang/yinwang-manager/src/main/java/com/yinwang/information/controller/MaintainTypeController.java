package com.yinwang.information.controller;

import java.util.Date;
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
import com.yinwang.information.domain.MaintainTypeDO;
import com.yinwang.information.service.MaintainTypeService;

/**
 * 维修类型类别表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-23 15:11:36
 */
 
@Controller
@RequestMapping("/information/maintainType")
public class MaintainTypeController {
	@Autowired
	private MaintainTypeService maintainTypeService;
	
	@GetMapping()
	@RequiresPermissions("information:maintainType:maintainType")
	String MaintainType(){
	    return "information/maintainType/maintainType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:maintainType:maintainType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> maintainTypeList = maintainTypeService.listMap(query);
		int total = maintainTypeService.count(query);
		PageUtils pageUtils = new PageUtils(maintainTypeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:maintainType:add")
	String add(){
	    return "information/maintainType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:maintainType:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MaintainTypeDO maintainType = maintainTypeService.get(id);
		model.addAttribute("maintainType", maintainType);
	    return "information/maintainType/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:maintainType:add")
	public R save( MaintainTypeDO maintainType){
		maintainType.setAddTime(new Date());
		maintainType.setUpdateTime(new Date());
		maintainType.setDeleteFlag(0);
		if(maintainTypeService.save(maintainType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:maintainType:edit")
	public R update( MaintainTypeDO maintainType){
		maintainType.setUpdateTime(new Date());
		maintainTypeService.update(maintainType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:maintainType:remove")
	public R remove( Long id){
		if(maintainTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:maintainType:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		maintainTypeService.batchRemove(ids);
		return R.ok();
	}
	
}
