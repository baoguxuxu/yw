package com.yinwang.information.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinwang.common.controller.BaseController;
import com.yinwang.information.domain.MsgDO;
import com.yinwang.information.service.MsgService;

/**
 * 消息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-12 16:00:47
 */
 
@Controller
@RequestMapping("/information/msg")
public class MsgController extends BaseController{
	@Autowired
	private MsgService msgService;
	
	@GetMapping()
	String Msg(Model model){
		Long userId = this.getUserId();
		List<Map<Object, String>> msgList = msgService.queryUserMsgList(userId);
		model.addAttribute("msgList", msgList);
		return "information/msg/xiaoxitongzhi";
	}
	

	@GetMapping("/queryMsgDetails")
	String queryMsgDetails(Long id,Long muId,Model model){
		MsgDO msg = msgService.queryMsgDetails(id,muId);
		model.addAttribute("msg", msg);
		return "information/msg/xiaoxixiangqing";
	}
	
	
}
