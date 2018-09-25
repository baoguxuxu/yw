package com.yinwang.plot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinwang.owneruser.domain.OwnerUserDO;
import com.yinwang.plot.domain.PlotDO;
import com.yinwang.plot.service.PlotService;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;

/**
 * 小区表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-13 15:26:20
 */
 
@Controller
@RequestMapping("/plot")
public class PlotController {
	@Autowired
	private PlotService plotService;
/*	@Autowired
	UserService userService;*/
	
	@GetMapping()
	String Plot(){
	    return "plot/plot";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PlotDO> plotList = plotService.list(query);
		int total = plotService.count(query);
		PageUtils pageUtils = new PageUtils(plotList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(Model model){
		/*List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);*/
	    return "plot/add";
	}

	@GetMapping("/show/{id}")
	@RequiresPermissions("sys:plot:chakan")
	String edit(@PathVariable("id") Long id,Model model){
		PlotDO plot = plotService.get(id);
		model.addAttribute("plot", plot);
		/*UserDO udo = userService.getbyphone(plot.getPhone());
		List<RoleDO> roles = roleService.listbyid(udo.getUserId());
		model.addAttribute("roles", roles);
		model.addAttribute("loginname", udo.getUsername());*/
	    return "plot/show";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( PlotDO plot){
		if(plotService.save(plot)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:plot:edit")
	public R update( PlotDO plot){
		plotService.update(plot);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:plot:remove")
	public R remove( Integer id){
		if(plotService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable")
	public R updateEnable(Long id,Integer enable) {
		PlotDO pdo = plotService.get(id);
		pdo.setId(id);
		pdo.setDaleteFlag(enable);
		plotService.update(pdo);

		return R.ok();
	}
	
	/**
	 * 获取位置
	 */
	@ResponseBody
	@RequestMapping(value="/getArea")
	public void getArea(HttpServletRequest request,HttpServletResponse response) {
		String jsonStr = "";
		try {
			System.out.println("11111111111111111");
			jsonStr = plotService.selectAreaDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeText(response, jsonStr);
	}
	
	public static void writeText(HttpServletResponse response, String json) {
		try {
			response.setHeader("Content-type", "application/json;charset=UTF-8");// 浏览器解析编码
			response.setCharacterEncoding("UTF-8");// tomcat编码
			System.out.println("******************************writeText==="+json);
			PrintWriter out = response.getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
