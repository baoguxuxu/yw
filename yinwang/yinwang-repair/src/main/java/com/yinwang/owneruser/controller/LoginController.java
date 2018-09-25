package com.yinwang.owneruser.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinwang.common.annotation.Log;
import com.yinwang.common.controller.BaseController;
import com.yinwang.common.utils.MD5Utils;
import com.yinwang.common.utils.R;
import com.yinwang.common.utils.ShiroUtils;
import com.yinwang.owneruser.comment.SMSContent;
import com.yinwang.owneruser.comment.SMSPlatform;
import com.yinwang.owneruser.comment.SMSTemplate;
import com.yinwang.owneruser.dao.FilesDao;
import com.yinwang.owneruser.domain.FilesDO;
import com.yinwang.owneruser.domain.RepairDO;
import com.yinwang.owneruser.domain.RepairUserDO;
import com.yinwang.owneruser.service.OwnerUserService;
import com.yinwang.owneruser.service.RepairService;
import com.yinwang.smsservice.service.ISMSService;


@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	OwnerUserService userService;
	@Autowired
	private ISMSService sMSService;
	@Autowired
	private RepairService repairService;
	@Autowired
	private FilesDao filesDao;
	
	
	@Log("请求访问维修列表")
	@GetMapping({ "/index" })
	String index(int repairStatue,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repair_user_id", this.getUserId());
		map.put("repairStatue", repairStatue);
		List<Map<String, Object>> listMap = repairService.listMap(map);
		model.addAttribute("listMap", listMap);
		model.addAttribute("repairStatue", repairStatue);
		return "index";
	}
	
	@Log("请求访问维修详情")
	@GetMapping({ "/queryRepair" })
	String queryRepair(int id,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Map<String, Object> mapRepair = repairService.queryRepair(map);
		model.addAttribute("mapRepair", mapRepair);
		Map<String,Object> fileMap = new HashMap<String, Object>();
		fileMap.put("type", "2");
		fileMap.put("tableId", id);
		List<FilesDO> listFile = filesDao.list(fileMap);
		model.addAttribute("listFile", listFile);
		model.addAttribute("user", this.getUser());
		return "list-xiangqing";
	}
	
	
	
	@GetMapping({ "/jieshao" })
	String jieshao(Model model) {
		return "jieshao";
	}

	@GetMapping("/login")
	String login() {
		return "user/denglu";
	}
	@GetMapping("/zhuce")
	String zhuce() {
		return "user/zhuce";
	}
	@GetMapping("/wangjimima")
	String wangjimima() {
		return "user/wangjimima";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			Map<String, Object> mapP = new HashMap<String, Object>();
			mapP.put("username", username);
			boolean flag = userService.exit(mapP);
			if (!flag) {
				return R.error("该手机号码未注册");
			}
			RepairUserDO udo= userService.getbyname(username);
			if(udo.getDeleteFlag()==1){
				return R.error("禁止登录，请联系客服");
			}
			subject.login(token);
			udo.setLoginTime(new Date());
			userService.update(udo);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}
	
	@Log("忘记密码")
	@PostMapping("/retpwd")
	@ResponseBody
	R retpwd(String username, String password,  String codenum) {
		if (StringUtils.isBlank(username)) {
			return R.error("手机号码不能为空");
		}
		Map<String, Object> mapP = new HashMap<String, Object>();
		mapP.put("username", username);
		boolean flag = userService.exit(mapP);
		if (!flag) {
			return R.error("该手机号码未注册");
		}
		password = MD5Utils.encrypt(username, password);
		RepairUserDO udo= userService.getbyname(username);
		Subject subject = SecurityUtils.getSubject();
		String captcha =subject.getSession().getAttribute("sys.login.check.code").toString();
		if (captcha == null || "".equals(captcha)) {
			return R.error("验证码已失效，请重新点击发送验证码");
		}
		// session中存放的验证码是手机号+验证码
		if (!captcha.equalsIgnoreCase(username + codenum)) {
			return R.error("手机验证码错误");
		}
		udo.setPassword(password);
		if (userService.update(udo) > 0) {
			return R.ok();
		}
		return R.error();
	}
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}
	
	/**
	 * @说明 发送验证码
	 * 暂时只有注册
	 * @author tmn
	 * @update tmn
	 * @param phone		手机号
	 * @param type		类型 0：注册   1实名认证   
	 * @param request
	 * @param response
	 */
	@PostMapping("/verificationcodeNumber")
	@ResponseBody
	   R verificationcodeNumber(String phone,String type) {
		if (phone == null || "".equals(phone)) {
			return R.error("手机号码不能为空");
		}
		
		//短信内容
		SMSTemplate contentType = SMSContent.ZHUCE;
		//短信内容
		if("0".equals(type)){
			contentType = SMSContent.ZHUCE;//注册
		}
		if("1".equals(type)){
			contentType = SMSContent.ZHAOHUI_LOGIN;//找回密码
			Map<String, Object> mapP = new HashMap<String, Object>();
			mapP.put("username", phone);
			boolean flag = userService.exit(mapP);
			if (!flag) {
				return R.error("该手机号码未注册");
			}
		}
		
		try {
				Map<String, Object> map = sMSService.sendCodeNumber(SMSPlatform.YINWANG, phone, contentType);
				if(map == null){
					return R.error("验证码发送出现问题,请三分钟再试");
				}
				String code = map.get("randomCode").toString();
				Subject subject = SecurityUtils.getSubject();
				subject.getSession().setAttribute("sys.login.check.code", phone + code);
				
				return R.ok();
		} catch (Exception e) {
			logger.info("SMS==================验证码发送出现问题========" + phone + "======");
			return R.error("验证码发送出现问题,请三分钟再试");
		}
	}
	
	
	@PostMapping("/repair/repairStatue")
	@ResponseBody
	R repairStatue(Long id, int repairStatue) {
		try {
			//维修状态1:未接单；2：已接单；3：已报价，服务中；5：完成；
			RepairDO  repair = repairService.get(id);
			if(repairStatue == 2 && repair.getRepairStatue() != 1){
				return R.error("您抢单晚了");
			}
			
			if(repairStatue == 1 && !(repair.getRepairStatue() == 2 || repair.getRepairStatue() == 3)){
				return R.error("无法取消订单");
			}
			//状态:0：申请、待报价；1：代付款；2：待评价；3：已完成；5：删除
			if(repairStatue == 5 && (repair.getStatue() != 2 && repair.getStatue() != 3)){
				return R.error("订单还未完成");
			}
			if(repairStatue == 5){
				repair.setStatue(3);
			}
			repair.setRepairStatue(repairStatue);
			repair.setRepairUserId(getUserId());
			this.repairService.update(repair);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("操作失败");
		}
	}
	
	@Log("报价")
	@PostMapping("/repair/baojia")
	@ResponseBody
	R baojia(Long id,Double service_charge, String replacement_item,Double price_goods) {
		try {
			RepairDO repair = repairService.get(id);
			if(repair.getRepairStatue() != 2){
				return R.error("报价错误");
			}
			if(repair.getRepairStatue() == 2){
				repair.setServiceCharge(new BigDecimal(service_charge));
		        repair.setStatue(Integer.valueOf(1));
				repair.setRepairStatue(3);
				repair.setReplacementItem(replacement_item);
				repair.setPriceGoods(new BigDecimal(price_goods));
				repair.setMoney(new BigDecimal((service_charge+price_goods)));
				repair.setRepairUserId(getUserId());
				this.repairService.update(repair);
			}
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("操作失败");
		}
	}
	
	@Log("请求访问个人中心")
	@GetMapping({ "/myindex" })
	String myindex(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repair_user_id", this.getUserId());
		Map<String, Object> userMap = repairService.queryUserRepair(map);
		model.addAttribute("userMap", userMap);
		model.addAttribute("user", getUser());
		return "gerenzhongxin";
	}
	
}
