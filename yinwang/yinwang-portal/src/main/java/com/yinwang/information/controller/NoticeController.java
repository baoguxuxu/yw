package com.yinwang.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinwang.information.domain.NoticeDO;
import com.yinwang.information.service.NoticeService;

/**
 * 公告表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-13 13:10:58
 */
 
@Controller
@RequestMapping("/information/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@GetMapping("/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		NoticeDO notice = noticeService.get(id);
		int num = notice.getNum() == null ? 0 : notice.getNum();
		notice.setNum(num+1);
		noticeService.update(notice);
		model.addAttribute("notice", notice);
		return "information/notice/noticexiangqing";
	}
	
}
