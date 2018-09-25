package com.yinwang.owneruser.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.yinwang.carousel.domain.BannerDO;
import com.yinwang.carousel.service.BannerService;
import com.yinwang.common.annotation.Log;
import com.yinwang.common.controller.BaseController;
import com.yinwang.common.utils.MD5Utils;
import com.yinwang.common.utils.R;
import com.yinwang.common.utils.ShiroUtils;
import com.yinwang.information.domain.NoticeDO;
import com.yinwang.information.service.NoticeService;
import com.yinwang.owneruser.comment.SMSContent;
import com.yinwang.owneruser.comment.SMSPlatform;
import com.yinwang.owneruser.comment.SMSTemplate;
import com.yinwang.owneruser.domain.OwnerUserDO;
import com.yinwang.owneruser.service.OwnerUserService;
import com.yinwang.smsservice.service.ISMSService;


@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private BannerService bannerService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	OwnerUserService userService;
	@Autowired
	private ISMSService sMSService;
	
	@Log("请求访问主页")
	@GetMapping({ "" })
	String indexs(Model model) {
		
		// 查询banner数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isEnable", "0");
		List<BannerDO> bannerList = bannerService.list(params);
		// 查询banner数据
		Map<String, Object> paramsNotice = new HashMap<String, Object>();
		OwnerUserDO udo = this.getUser();
		if(udo != null)
			paramsNotice.put("user_id", this.getUserId());
		List<NoticeDO> noticeList = noticeService.list(paramsNotice);

		model.addAttribute("bannerList", bannerList);
		model.addAttribute("noticeList", noticeList);
		return "index";
	}
	
	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		
		// 查询banner数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isEnable", "0");
		List<BannerDO> bannerList = bannerService.list(params);
		// 查询banner数据
		Map<String, Object> paramsNotice = new HashMap<String, Object>();
		OwnerUserDO udo = this.getUser();
		if(udo != null)
			paramsNotice.put("user_id", this.getUserId());
		List<NoticeDO> noticeList = noticeService.list(paramsNotice);

		model.addAttribute("bannerList", bannerList);
		model.addAttribute("noticeList", noticeList);
		return "index";
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
			OwnerUserDO udo= userService.getbyname(username);
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
	@Log("注册")
	@PostMapping("/zhuce")
	@ResponseBody
	R ajaxZhuce(String username, String password, String nickname, String codenum) {
		if (StringUtils.isBlank(username)) {
			return R.error("手机号码不能为空");
		}
		password = MD5Utils.encrypt(username, password);
		OwnerUserDO udo= new OwnerUserDO();
		Subject subject = SecurityUtils.getSubject();
		String captcha =subject.getSession().getAttribute("sys.login.check.code").toString();
		if (captcha == null || "".equals(captcha)) {
			return R.error("验证码已失效，请重新点击发送验证码");
		}
		// session中存放的验证码是手机号+验证码
		if (!captcha.equalsIgnoreCase(username + codenum)) {
			return R.error("手机验证码错误");
		}
		Map<String, Object> mapP = new HashMap<String, Object>();
		mapP.put("username", username);
		boolean flag = userService.exit(mapP);
		if (flag) {
			return R.error("手机号码已存在");
		}
		udo.setUsername(username);
		udo.setPhone(username);
		udo.setPassword(password);
		udo.setNickname(nickname);
		udo.setBalance(0.00);
		udo.setDeleteFlag(0);
		udo.setRegisterTime(new Date());
		if (userService.save(udo) > 0) {
			return R.ok();
		}
		return R.error();
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
		OwnerUserDO udo= userService.getbyname(username);
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
}
