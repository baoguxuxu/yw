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

import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.information.domain.NoticeDO;
import com.yinwang.information.service.NoticeService;
import com.yinwang.system.domain.UserDO;
import com.yinwang.system.service.UserService;

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
	@Autowired
	UserService userService;
	
	
	
	
	@GetMapping()
	@RequiresPermissions("information:notice:notice")
	String Notice(){
	    return "information/notice/notice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:notice:notice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NoticeDO> noticeList = noticeService.list(query);
		int total = noticeService.count(query);
		PageUtils pageUtils = new PageUtils(noticeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:notice:add")
	String add(){
	    return "information/notice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:notice:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		NoticeDO notice = noticeService.get(id);
		model.addAttribute("notice", notice);
	    return "information/notice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:notice:add")
	public R save( NoticeDO notice){
		int total = userService.count(null);
		
		notice.setForNum(total);
		notice.setAddTime(new Date());
		notice.setUpdateTime(new Date());
		notice.setDaleteFlag(1);
		if(noticeService.save(notice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:notice:edit")
	public R update( NoticeDO notice){
		noticeService.update(notice);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:notice:remove")
	public R remove( Integer id){
		if(noticeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:notice:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		noticeService.batchRemove(ids);
		return R.ok();
	}
	
}
