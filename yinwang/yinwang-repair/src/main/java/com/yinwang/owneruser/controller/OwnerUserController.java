package com.yinwang.owneruser.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yinwang.common.annotation.Log;
import com.yinwang.common.config.BootdoConfig;
import com.yinwang.common.config.Constant;
import com.yinwang.common.controller.BaseController;
import com.yinwang.common.utils.FileUtil;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.common.utils.StringUtils;
import com.yinwang.owneruser.domain.RepairUserDO;
import com.yinwang.owneruser.domain.UserPlotDO;
import com.yinwang.owneruser.service.OwnerUserService;
import com.yinwang.owneruser.service.UserPlotService;

@RequestMapping("/owner")
@Controller
public class OwnerUserController extends BaseController {
	private String prefix="user"  ;
	@Autowired
	OwnerUserService userService;
	@Autowired
	UserPlotService plotService;
	@Autowired
	private BootdoConfig bootdoConfig;
	/**
	 * 个人中心
	 * @return
	 */
	@GetMapping("/user")
	String user(Model model) {
		RepairUserDO udo= userService.get(getUserId());
		model.addAttribute("user", udo);
		return prefix + "/gerenzhongxin";
	}
	/**
	 * 获取用户信息
	 * @return
	 */
	@GetMapping("/userInfo")
	String userInfo(Model model) {
		RepairUserDO udo=userService.get(getUserId());
		model.addAttribute("user", udo);
		return prefix + "/gerenxinxi";
	}
	/**
	 * 编辑用户信息
	 * @return
	 */
	@Log("更新用户")
	@PostMapping("/editInfo")
	@ResponseBody
	R editInfo(RepairUserDO user) {
		RepairUserDO userd = userService.getbyname(user.getUsername());
		if(user.getHeardUrl()!=null){
			userd.setHeardUrl(user.getHeardUrl());
		}
		if(user.getNickname()!=null){
			userd.setNickname(user.getNickname());
		}
		if(user.getName()!=null){
			userd.setName(user.getName());
		}
		if(user.getIdentityCard()!=null){
			userd.setIdentityCard(user.getIdentityCard());
		}
		if(user.getFileImg() != null && user.getFileImg().getSize() > 0){
				MultipartFile sysFile = user.getFileImg();
				String fileName = sysFile.getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				try {
					FileUtil.uploadFile(sysFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				userd.setHeardUrl("/files/" + fileName);
		}
		if (userService.update(userd) > 0) {
			
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 获取用户小区
	 * @return
	 */
	@GetMapping("/userPlot")
	String userPlot(Model model) {
		RepairUserDO udo= getUser();
		if(StringUtils.isNoneBlank(udo.getIdentityCard()))
			udo.setIdentityCard(udo.getIdentityCard().replaceAll("(\\d{3})\\d{11}(\\d{4})","$1****$2"));
		model.addAttribute("user", udo);
		List<UserPlotDO> pdoL= plotService.listbyid(getUserId());
		model.addAttribute("pdoL", pdoL);
		return prefix + "/fangwu";
	}
	/**
	 * 保存小区信息
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveplotQ")
	public R saveplotQ(UserPlotDO userPlot) {
		if(userPlot.getUsername()!=null &&userPlot.getUsername()!=""){
			RepairUserDO udo= getUser();
			udo.setName(userPlot.getUsername());
			udo.setIdentityCard(userPlot.getIdentityCard());
			userService.update(udo);
		}
		if(plotService.save(userPlot)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改小区信息
	 * @return
	 */
	@ResponseBody
	@PostMapping("/editplotQ")
	public R editplotQ(UserPlotDO userPlot) {
		if(userPlot.getUsername()!=null &&userPlot.getUsername()!=""){
			RepairUserDO udo= getUser();
			udo.setName(userPlot.getUsername());
			udo.setIdentityCard(userPlot.getIdentityCard());
			userService.update(udo);
		}
		if(plotService.update(userPlot)>0){
			return R.ok();
		}
		return R.error();
	}
	
	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<RepairUserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@Log("查看用户")
	@GetMapping("/show/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		RepairUserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		return prefix+"/show";
	}

	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(RepairUserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.update(user) > 0) {
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
		RepairUserDO oudo = userService.get(id);
		oudo.setId(id);
		oudo.setDeleteFlag(enable);
		userService.update(oudo);

		return R.ok();
	}

}
