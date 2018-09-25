package com.yinwang.jiaofei.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yinwang.common.annotation.Log;
import com.yinwang.common.controller.BaseController;
import com.yinwang.common.utils.MD5Utils;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.information.domain.RepairDO;
import com.yinwang.information.service.MaintainTypeService;
import com.yinwang.information.service.RepairService;
import com.yinwang.jiaofei.service.OrderService;
import com.yinwang.jiaofei.util.GenerateCode;
import com.yinwang.jiaofei.util.HttpXmlUtils;
import com.yinwang.jiaofei.util.ParseXMLUtils;
import com.yinwang.jiaofei.util.RandCharsUtils;
import com.yinwang.jiaofei.util.WXSignUtils;
import com.yinwang.jiaofei.vo.OrderDO;
import com.yinwang.jiaofei.vo.PayOrderEntity;
import com.yinwang.jiaofei.vo.Unifiedorder;
import com.yinwang.owneruser.domain.OwnerUserDO;
import com.yinwang.owneruser.domain.UserPlotDO;
import com.yinwang.owneruser.service.OwnerUserService;
import com.yinwang.owneruser.service.UserPlotService;
import com.yinwang.plot.domain.PlotDO;
import com.yinwang.plot.service.PlotService;

/**
 * 维修表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-25 16:17:51
 */
 
@Controller
@RequestMapping("/jiaofei/wuye")
public class JiaoFeiController extends BaseController{
	@Autowired
	private RepairService repairService;
	@Autowired
	private MaintainTypeService maintainTypeService;
	@Autowired
	UserPlotService plotService;
	@Autowired
	PlotService addplotService;
	@Autowired
	OwnerUserService userService;
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping()
	String Repair(Model model,HttpServletRequest request){
		OwnerUserDO user = this.getUser();
		String name = user.getPhone();
		if(StringUtils.isNotBlank(user.getNickname())){
			name = user.getNickname();
		}
		if(StringUtils.isNotBlank(user.getName())){
			name = user.getName();
		}
		String code = request.getParameter("code");
		System.out.println("====code======"+code);
		String urlop = "https://api.weixin.qq.com/sns/oauth2/access_token";
		String paramop="appid=wxa2ee172ca5977381&secret=8f45a11773809ba9f8ca6abb6d599dc9&code="+code+"&grant_type=authorization_code";
		String resu = sendGet(urlop, paramop);
		Map<String, String> mapop = new HashMap<String, String>();
		mapop=JSON.parseObject(resu, Map.class);
		String openid = "";
		openid=mapop.get("openid");
		System.out.println("====openid======"+mapop.get("openid"));
		System.out.println("====resu======"+resu);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deleteFlag", 0);
		map.put("pId", 1);
		OwnerUserDO udo= getUser();
		if(StringUtils.isNotBlank(udo.getIdentityCard()))
			udo.setIdentityCard(udo.getIdentityCard().replaceAll("(\\d{3})\\d{11}(\\d{4})","$1****$2"));
		OwnerUserDO udo1=userService.get(getUserId());
		model.addAttribute("user1", udo1);
		model.addAttribute("user", udo);
		List<String> cityList=addplotService.getcityList();
		model.addAttribute("cityList", cityList);
		List<PlotDO> plotList= new ArrayList<PlotDO>();
		if(cityList.size()>0)
		  plotList=addplotService.getplotList(cityList.get(0));
		model.addAttribute("plotList", plotList);
		model.addAttribute("name", name);//姓名
		model.addAttribute("openid", openid);//姓名
		model.addAttribute("phone", user.getPhone());//手机号
		
		return "jiaofei/jiaofeixiangqing";
	}
	
	/**
	 * 我的服务
	 * @param statue
	 * @param model
	 * @return
	 */
	@GetMapping("/listMap")
	public String listMap(String statue, Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", this.getUserId());
		map.put("statue", statue);
		List<Map<String, Object>> listMap = this.repairService.listMap(map);
		model.addAttribute("listMap", listMap);//服务列表
		return "information/repair/wodefuwu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:repair:repair")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RepairDO> repairList = repairService.list(query);
		int total = repairService.count(query);
		PageUtils pageUtils = new PageUtils(repairList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:repair:add")
	String add(){
	    return "information/repair/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:repair:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RepairDO repair = repairService.get(id);
		model.addAttribute("repair", repair);
	    return "information/repair/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( RepairDO repair){
		repair.setUserId(this.getUserId());
		if(repairService.save(repair)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/getAddressList")
	public R getAddressList(){
		
		OwnerUserDO udo= getUser();
		if(StringUtils.isNotBlank(udo.getIdentityCard()))
		udo.setIdentityCard(udo.getIdentityCard().replaceAll("(\\d{3})\\d{11}(\\d{4})","$1****$2"));
		List<UserPlotDO> pdoL= plotService.listbyid(getUserId());
		Map< String, Object> map = new HashMap<String, Object>();
		map.put("pdoL", pdoL);
		map.put("user", udo);
		return R.ok(map);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/notifycallback")
	public String notifyurl(HttpServletRequest request,HttpServletResponse response){
		//System.out.println("微信支付成功,微信发送的callback信息,请注意修改订单信息");
		InputStream is = null;
		try {
			is = request.getInputStream();//获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
			String xml = HttpXmlUtils.getContent(is, "UTF-8");
			Map<String, Object> notifyMap = ParseXMLUtils.jdomParseXml(xml);//将微信发的xml转map
					System.out.println("===xml============="+xml);	
					
			if(notifyMap.get("return_code").equals("SUCCESS")){  
	                if(notifyMap.get("result_code").equals("SUCCESS")){  
	                String ordersSn = notifyMap.get("out_trade_no").toString();//商户订单号 
	                String totalFee = notifyMap.get("total_fee").toString();//实际支付的订单金额:单位 分
	                OrderDO order = orderService.getbyno(ordersSn);
	                if(order!=null){
	                	order.setCreateTime(new Date());
	                	order.setStatus(1);
	                	order.setTotalFee(new BigDecimal(totalFee).divide(new BigDecimal(100)));
	        		orderService.update(order);
	        		 RepairDO rdo = repairService.get((long)order.getPayTime());
	        		 if(rdo!=null){
	        			 System.out.println("====rdo不是空的=====");
		        		 rdo.setStatue(2);
		        		 repairService.update(rdo); 
	        		 }

	                }
	                  
	            }  
	        }
	        //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
	        response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");  
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 微信公众号支付付款
	 * 
	 * @return JsonObject
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/wxpay/payment")
	public R  payment(String spbill_create_ip,String desc,  String repids,  String attach, Integer totalFee, String openid) {
		String tradeType = "JSAPI";
	    System.out.println("===totalFee==="+totalFee);
	    int repid = Integer.parseInt(repids);
	    System.out.println("====repid==="+repid);
		String notify_url = "https://yinwang.wx.hg.jergavin.com/jiaofei/wuye/notifycallback";
		//String notify_url = "http://test-ht.yqwmj.cn/wxpay/notify";
		// 参数组
		String appid =  "wxa2ee172ca5977381";
		//String appid =  "wxc244b03d788ed78a";
		String mch_id = "1503056031";
		//String mch_id = "1502717441";
		String secret = "F17CR7qZ4FIKVYMIvYXl7ZkmgRlX4gx6";
		//String secret = "nwjYoQDfUtJMEv0KWFfVNybakGFLBL13";
		
		String nonce_str = RandCharsUtils.getRandomString(16);
		String out_trade_no = GenerateCode.getUUID();
		// 参数：开始生成签名
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", desc);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", totalFee);
		parameters.put("spbill_create_ip", "121.69.133.202");
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", tradeType);
		parameters.put("attach", attach);
		parameters.put("openid", openid);
		String sign = WXSignUtils.createSign("UTF-8", parameters, secret);
		System.out.println("sign====="+sign);
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setBody(desc);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setTotal_fee(totalFee);
		unifiedorder.setSpbill_create_ip("121.69.133.202");
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(tradeType);
		unifiedorder.setAttach(attach);
		unifiedorder.setOpenid(openid);
		unifiedorder.setSign(sign);

		// 构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		System.out.println("==xmlInfo========"+xmlInfo);
		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String method = "POST";

		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

		Map<String, Object> retMap = ParseXMLUtils.jdomParseXml(weixinPost);
		String return_code=retMap.get("return_code").toString();
		
		System.out.println("retMap={}====="+retMap);
		System.out.println("请求统一下单接口返回结果 ==return_code={}===="+return_code);
		if("FAIL".equals(return_code)){
			return R.error("支付失败");
		}
		//支付结果
		String result_code=retMap.get("result_code").toString();
		System.out.println("请求统一下单接口返回支付结果 ==return_code={}==="+result_code);
		if("FAIL".equals(result_code)){
			String err_code=retMap.get("err_code").toString();//返回错误码
			String err_code_des=retMap.get("err_code_des").toString();//错误描述信息
			/**
			 * NOTENOUGH 余额不足
			 * ORDERPAID 商户订单已支付
			 * ORDERCLOSED 订单已关闭
			 * OUT_TRADE_NO_USED 商户订单号重复
			 */
			if("NOTENOUGH".equals(err_code)||
					"ORDERCLOSED".equals(err_code)||
					"ORDERPAID".equals(err_code)||
					"OUT_TRADE_NO_USED".equals(err_code)){
				return R.error(err_code_des);
			}
			return R.error("支付失败");
		}
		String prepay_id=retMap.get("prepay_id").toString();
		System.out.println("==prepay_id==========="+prepay_id);
		SortedMap<Object, Object> parameters1 = new TreeMap<Object, Object>();
		parameters1.put("appId", appid);
		long timetoken = System.currentTimeMillis() / 1000;
		parameters1.put("timeStamp", timetoken + "");
		parameters1.put("nonceStr", nonce_str);
		parameters1.put("package", "prepay_id="+prepay_id);
		parameters1.put("signType", "MD5");

		Date currentTime =new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderNo ="yw"+ formatter.format(currentTime); 
		OrderDO order = new OrderDO();
		order.setAppid(appid);
		order.setBody(desc);
		order.setCreateTime(new Date());
		order.setCreateUser(getUserId());
		order.setMchId(mch_id);
		order.setNotifyUrl(notify_url);
		order.setOrderNo(orderNo);
		order.setOutOrderNo(out_trade_no);
		order.setPayWay("WX");
		order.setAttach(attach);
		order.setSpbillCreateIp(spbill_create_ip);
		order.setPayTime(repid);
		order.setStatus(0);
		order.setGroup("0");
		order.setTotalFee(new BigDecimal(totalFee).divide(new BigDecimal(100)));
		order.setTradeType(tradeType);
		orderService.save(order);
		
		String resign = WXSignUtils.createSign("UTF-8", parameters1,secret);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("appId", appid);
		ret.put("timeStamp", timetoken + "");
		ret.put("nonceStr", nonce_str);
		ret.put("package", "prepay_id="+prepay_id);
		ret.put("signType", "MD5");
		ret.put("paySign", resign);
		return R.ok(ret);
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:repair:remove")
	public R remove( Long id){
		if(repairService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:repair:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		repairService.batchRemove(ids);
		return R.ok();
	}
	
	@PostMapping("/getpaytime")
	@ResponseBody
	R getpaytime(String address) {
		OrderDO odo = new OrderDO();
		odo.setAttach(address);
		odo.setBody("物业费");
		odo.setCreateUser(getUserId());
		//查询列表数据
		List<OrderDO> orderList = orderService.list(odo);
		if(orderList.size()>0){
			Date now = new Date();
			Date newDate = stepMonth(now, -6);
			if(newDate.before(orderList.get(0).getCreateTime())){
				return R.error();
			}
		}
		return R.ok();
	}
	
	public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);
 
        return c.getTime();
    }
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
}
