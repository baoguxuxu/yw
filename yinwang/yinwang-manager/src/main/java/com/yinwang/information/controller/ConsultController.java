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

import com.yinwang.carousel.domain.BannerDO;
import com.yinwang.common.config.BootdoConfig;
import com.yinwang.common.controller.BaseController;
import com.yinwang.common.utils.FileUtil;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.information.domain.ConsultDO;
import com.yinwang.information.service.ConsultService;

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
	@RequiresPermissions("information:consult:consult")
	String Consult(){
	    return "information/consult/consult";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:consult:consult")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ConsultDO> consultList = consultService.list(query);
		int total = consultService.count(query);
		PageUtils pageUtils = new PageUtils(consultList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:consult:add")
	String add(){
	    return "information/consult/add";
	}
	/**
	 * 小区集合 
	 */
	@ResponseBody
	@PostMapping("/getUserPlotList")
	public R getUserPlotList(Integer consult_id){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("consult_id", consult_id);
		List<Map<String, Object>> plotList = consultService.getPlotList(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("plotList",plotList);
		return R.ok(map);
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:consult:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ConsultDO consult = consultService.get(id);
		model.addAttribute("consult", consult);
	    return "information/consult/edit";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable")
	public R updateEnable(int id,Integer enable) {
		System.out.println("id"+id);
		System.out.println("enable"+enable);
		ConsultDO sysFile = new ConsultDO();
		sysFile.setId(id);
		sysFile.setIsDisabled(enable);
		consultService.update(sysFile);
		return R.ok();
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:consult:add")
	public R save( ConsultDO consult){
		
		String fileName = consult.getImgFile().getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		try {
			FileUtil.uploadFile(consult.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
			consult.setUrl("/files/" + fileName);
			consult.setAddTime(new Date());
			consult.setUpdateTime(new Date());
			consult.setUserId(this.getUserId());
		} catch (Exception e) {
			return R.error();
		}
		
		
		
		if(consultService.save(consult)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:consult:edit")
	public R update( ConsultDO consult){
		if(consult.getImgFile() != null && consult.getImgFile().getSize() > 0){
			String fileName = consult.getImgFile().getOriginalFilename();
			fileName = FileUtil.renameToUUID(fileName);
			try {
				FileUtil.uploadFile(consult.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				consult.setUrl("/files/" + fileName);
				consult.setUpdateTime(new Date());
				consult.setUserId(this.getUserId());
			} catch (Exception e) {
				return R.error();
			}
		}
		consultService.update(consult);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:consult:remove")
	public R remove( Integer id){
		if(consultService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:consult:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		consultService.batchRemove(ids);
		return R.ok();
	}
	
}
