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
import com.yinwang.information.domain.OptionDO;
import com.yinwang.information.service.OptionService;

/**
 * 问卷选项表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 12:58:18
 */
 
@Controller
@RequestMapping("/information/option")
public class OptionController {
	@Autowired
	private OptionService optionService;
	
	@GetMapping()
	//@RequiresPermissions("information:option:option")
	String Option(Long id,Model model){
		System.out.println("++++++++++++++++++++++"+id);
		model.addAttribute("topicId", id);
	    return "information/option/option";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("information:option:option")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OptionDO> optionList = optionService.list(query);
		int total = optionService.count(query);
		PageUtils pageUtils = new PageUtils(optionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{topicId}")
	@RequiresPermissions("information:option:add")
	String add(@PathVariable("topicId") Integer topicId,Model model){
		model.addAttribute("topicId", topicId);
		return "information/option/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:option:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OptionDO option = optionService.get(id);
		model.addAttribute("option", option);
		return "information/option/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:option:add")
	public R save( OptionDO option){
		option.setAddTime(new Date());
		option.setUpdateTime(new Date());
		if(optionService.save(option)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:option:edit")
	public R update( OptionDO option){
		option.setUpdateTime(new Date());
		optionService.update(option);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:option:remove")
	public R remove( Integer id){
		if(optionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:option:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		optionService.batchRemove(ids);
		return R.ok();
	}
	
}
