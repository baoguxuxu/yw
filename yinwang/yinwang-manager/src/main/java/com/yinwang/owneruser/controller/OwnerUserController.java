package com.yinwang.owneruser.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinwang.common.annotation.Log;
import com.yinwang.common.config.Constant;
import com.yinwang.common.controller.BaseController;
import com.yinwang.common.service.DictService;
import com.yinwang.common.utils.ExcelExportUtil4DIY;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.information.domain.MsgDO;
import com.yinwang.information.service.MsgService;
import com.yinwang.information.service.RepairService;
import com.yinwang.owneruser.comment.SMSContent;
import com.yinwang.owneruser.comment.SMSPlatform;
import com.yinwang.owneruser.comment.SMSTemplate;
import com.yinwang.owneruser.domain.OwnerUserDO;
import com.yinwang.owneruser.service.OwnerUserService;
import com.yinwang.plot.domain.UserPlotDO;
import com.yinwang.plot.service.UserPlotService;
import com.yinwang.smsservice.service.ISMSService;
import com.yinwang.system.service.RoleService;








import java.util.HashMap;

@RequestMapping("/owner/user")
@Controller
public class OwnerUserController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(OwnerUserController.class);
	
	private String prefix="owneruser"  ;
	@Autowired
	OwnerUserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	DictService dictService;
	@Autowired
	private MsgService msgService;
	@Autowired
	private RepairService repairService;
	@Autowired
	UserPlotService plotService;
	@Autowired
	private ISMSService sMSService;
	@RequiresPermissions("sys:owner:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		System.out.println(params.get("addTime"));
		Query query = new Query(params);
		List<OwnerUserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:chakan")
	@Log("查看用户")
	@GetMapping("/show/{id}/{address}")
	String edit(Model model, @PathVariable("id") Long id, @PathVariable("address") String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", id);
		List<Map<String, Object>> listMap = this.repairService.listMap(map);
		List<UserPlotDO> plotMap = plotService.listbyid(id);
		OwnerUserDO user = new OwnerUserDO();
		user.setId(id);
		user.setAddress(address);
		List<UserPlotDO> updo = plotService.listbyid(id);
		OwnerUserDO userDO = new OwnerUserDO();
		if(updo.size()>0){
			userDO = userService.getup(user);
		}else{
			userDO = userService.get(id);
		}
		model.addAttribute("user", userDO);
		model.addAttribute("listMap", listMap);
		model.addAttribute("plotMap", plotMap);
		return prefix+"/show";
	}
	@RequiresPermissions("sys:user:edit")
	@Log("查看用户")
	@GetMapping("/edit/{id}/{address}")
	String edit1(Model model, @PathVariable("id") Long id, @PathVariable("address") String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", id);
		List<Map<String, Object>> listMap = this.repairService.listMap(map);
		List<UserPlotDO> plotMap = plotService.listbyid(id);
		OwnerUserDO user = new OwnerUserDO();
		user.setId(id);
		user.setAddress(address);
		List<UserPlotDO> updo = plotService.listbyid(id);
		OwnerUserDO userDO = new OwnerUserDO();
		if(updo.size()>0){
			userDO = userService.getup(user);
		}else{
			userDO = userService.get(id);
		}
		model.addAttribute("user", userDO);
		model.addAttribute("listMap", listMap);
		model.addAttribute("plotMap", plotMap);
		return prefix+"/edit";
	}

	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(OwnerUserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}



	@RequiresPermissions("sys:owner:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable")
	public R updateEnable(Long id,Integer enable) {
		OwnerUserDO oudo = userService.get1(id);
		oudo.setId(id);
		oudo.setDeleteFlag(enable);
		userService.update1(oudo);

		return R.ok();
	}
	/**
	 * 催款
	 */
	@ResponseBody
	@RequestMapping(value="/cuikuan")
	public R cuikuan(Long id) {
		MsgDO msg = new MsgDO();
		msg.setName("缴费通知");
		msg.setForIds(id+"");
		msg.setForDetails("<p>您当前余额已不足，请及时缴费！</p>");
		msg.setAddTime(new Date());
		msg.setUpdateTime(new Date());
		OwnerUserDO oudo = userService.get(id);
		//短信内容
		SMSTemplate contentType = SMSContent.CUIKUAN;
		try {
			Map<String, Object> map = sMSService.sendCodeNumber(SMSPlatform.YINWANG, oudo.getPhone(), contentType);
			if(map == null){
				return R.error("验证码发送出现问题,请三分钟再试");
			}
			String code = map.get("randomCode").toString();
			Subject subject = SecurityUtils.getSubject();
			subject.getSession().setAttribute("sys.login.check.code", oudo.getPhone() + code);
			
			if(msgService.save(msg)<=0){
				return R.error();
			}
			return R.ok();
	} catch (Exception e) {
		logger.info("SMS==================验证码发送出现问题========" + oudo.getPhone() + "======");
		return R.error();
	 }
	}

	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/exportExcel")
	public void exportExcel(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "业主列表"+format.format(new Date().getTime())+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
	try {
		Query query = new Query(params);
		String type = request.getParameter("type");
		//导出当前页面数据
		if(type.equals("1")){
			List<Map<String, Object>> XxxDOs = userService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
		//导出全部数据
		if(type.equals("2")){
			List<Map<String, Object>> XxxDOs = userService.exeList(null);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
		//导出符合条件的全部数据
		if(type.equals("3")){
			query.remove("offset");
			query.remove("limit");
			List<Map<String, Object>> XxxDOs = userService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
		//导选中部分
		if(type.equals("4")){
			query.remove("offset");
			query.remove("limit");
			System.out.println("ids:"+query.get("ids"));
			List<Map<String, Object>> XxxDOs = userService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
	} catch (Exception e) {
		e.printStackTrace();
		logger.info("exportExcel出错"+e.getMessage());
		}finally{
			out.close();
		}
	}
}
