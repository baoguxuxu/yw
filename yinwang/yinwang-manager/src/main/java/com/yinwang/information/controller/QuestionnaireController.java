package com.yinwang.information.controller;

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

import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.information.domain.ConsultDO;
import com.yinwang.information.domain.QuestionnaireDO;
import com.yinwang.information.service.QuestionnaireService;
import com.yinwang.information.service.TopicService;

/**
 * 问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 10:11:20
 */
 
@Controller
@RequestMapping("/information/questionnaire")
public class QuestionnaireController {
	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private TopicService topicService;
	
	
	
	@GetMapping()
	@RequiresPermissions("information:questionnaire:questionnaire")
	String Questionnaire(){
	    return "information/questionnaire/questionnaire";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:questionnaire:questionnaire")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QuestionnaireDO> questionnaireList = questionnaireService.list(query);
		int total = questionnaireService.count(query);
		PageUtils pageUtils = new PageUtils(questionnaireList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:questionnaire:add")
	String add(){
	    return "information/questionnaire/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:questionnaire:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QuestionnaireDO questionnaire = questionnaireService.get(id);
		model.addAttribute("questionnaire", questionnaire);
	    return "information/questionnaire/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:questionnaire:add")
	public R save( QuestionnaireDO questionnaire){
		if(questionnaireService.save(questionnaire)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:questionnaire:edit")
	public R update( QuestionnaireDO questionnaire){
		questionnaireService.update(questionnaire);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable")
	public R updateEnable(int id,Integer enable) {
		System.out.println("id"+id);
		System.out.println("enable"+enable);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("questionnaireId", id);
		int total = topicService.count(params);
		System.out.println("有题目total:"+total);
		int countOption = questionnaireService.countOption(id);
		int counttopic = questionnaireService.counttopic(id);
		System.out.println("有答案countOption:"+countOption);
		if(total > 0 && countOption > 0 || counttopic >0){
			QuestionnaireDO sysFile = new QuestionnaireDO();
			sysFile.setId(id);
			sysFile.setIsShow(enable);
			questionnaireService.update(sysFile);
			return R.ok();
		}else{
			return R.error("请先添加题目和答案!");
		}
		
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:questionnaire:remove")
	public R remove( Integer id){
		if(questionnaireService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:questionnaire:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		questionnaireService.batchRemove(ids);
		return R.ok();
	}
	
	
	/**
	 * 小区集合 
	 */
	@ResponseBody
	@PostMapping("/getUserPlotList")
	public R getUserPlotList(Integer consult_id){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("questionnaire_id", consult_id);
		List<Map<String, Object>> plotList = questionnaireService.getPlotList(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("plotList",plotList);
		return R.ok(map);
	}
	
}
