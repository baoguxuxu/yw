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
import com.yinwang.information.domain.TopicDO;
import com.yinwang.information.service.TopicService;

/**
 * 问卷题目表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 11:32:26
 */
 
@Controller
@RequestMapping("/information/topic")
public class TopicController {
	@Autowired
	private TopicService topicService;
	
	@GetMapping()
	//@RequiresPermissions("information:topic:topic")
	String Topic(Long id,Model model){
		model.addAttribute("questionnaireId", id);
		return "information/topic/topic";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("information:topic:topic")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TopicDO> topicList = topicService.list(query);
		int total = topicService.count(query);
		PageUtils pageUtils = new PageUtils(topicList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{questionnaireId}")
	@RequiresPermissions("information:topic:add")
	String add(@PathVariable("questionnaireId") Integer questionnaireId,Model model){
		model.addAttribute("questionnaireId", questionnaireId);
		return "information/topic/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:topic:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TopicDO topic = topicService.get(id);
		model.addAttribute("topic", topic);
	    return "information/topic/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:topic:add")
	public R save( TopicDO topic){
		topic.setAddTime(new Date());
		topic.setUpdateTime(new Date());
		if(topicService.save(topic)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:topic:edit")
	public R update( TopicDO topic){
		topic.setUpdateTime(new Date());
		topicService.update(topic);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:topic:remove")
	public R remove( Integer id){
		if(topicService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:topic:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		topicService.batchRemove(ids);
		return R.ok();
	}
	
}
