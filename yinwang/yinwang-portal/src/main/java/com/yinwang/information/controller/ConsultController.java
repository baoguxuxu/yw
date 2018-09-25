package com.yinwang.information.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinwang.common.config.BootdoConfig;
import com.yinwang.common.controller.BaseController;
import com.yinwang.information.domain.ConsultDO;
import com.yinwang.information.service.ConsultService;
import com.yinwang.owneruser.domain.OwnerUserDO;

/**
 * 咨询表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-13 14:42:52
 */
 
@Controller
@RequestMapping("/information/consult")
public class ConsultController extends BaseController{
	@Autowired
	private ConsultService consultService;
	@Autowired
	private BootdoConfig bootdoConfig;

	
	
	@GetMapping()
	String Consult(int type,Model model){
		Map<String, Object> param = new HashMap<String, Object>();
		OwnerUserDO user = this.getUser();
		if(user != null)
			param.put("userId", this.getUserId());
		param.put("type", type);
		List<Map<Object, String>> consultList = consultService.list(param);
		model.addAttribute("consultList", consultList);
		model.addAttribute("type", type);
		return "information/consult/xinwengonggao";
	}
	

	@GetMapping("/queryMsgDetails")
	String queryMsgDetails(Integer id,Model model){
		ConsultDO consult = consultService.get(id);
		int num = consult.getBrowseNum() == null ? 0 : consult.getBrowseNum();
		consult.setBrowseNum(num + 1);
		consultService.update(consult);
		model.addAttribute("consult", consult);
		return "information/consult/xinwenxiangqing";
	}
		
}
