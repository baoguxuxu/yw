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
import com.yinwang.information.domain.QueAnswerDO;
import com.yinwang.information.service.QueAnswerService;

/**
 * 问卷答案表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 13:47:58
 */
 
@Controller
@RequestMapping("/information/queAnswer")
public class QueAnswerController {
	@Autowired
	private QueAnswerService queAnswerService;
	
	@GetMapping()
	@RequiresPermissions("information:queAnswer:queAnswer")
	String QueAnswer(){
		return "information/queAnswer/queAnswer";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:queAnswer:queAnswer")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QueAnswerDO> queAnswerList = queAnswerService.list(query);
		int total = queAnswerService.count(query);
		PageUtils pageUtils = new PageUtils(queAnswerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:queAnswer:add")
	String add(){
	    return "information/queAnswer/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:queAnswer:edit")
	String edit(@PathVariable("id") Long id,Model model){
		QueAnswerDO queAnswer = queAnswerService.get(id);
		model.addAttribute("queAnswer", queAnswer);
	    return "information/queAnswer/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:queAnswer:add")
	public R save( QueAnswerDO queAnswer){
		if(queAnswerService.save(queAnswer)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:queAnswer:edit")
	public R update( QueAnswerDO queAnswer){
		queAnswerService.update(queAnswer);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:queAnswer:remove")
	public R remove( Long id){
		if(queAnswerService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:queAnswer:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		queAnswerService.batchRemove(ids);
		return R.ok();
	}
	
}
