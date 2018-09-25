package com.yinwang.information.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.alibaba.fastjson.JSON;
import com.yinwang.common.controller.BaseController;
import com.yinwang.common.utils.PageUtils;
import com.yinwang.common.utils.Query;
import com.yinwang.common.utils.R;
import com.yinwang.information.domain.MaintainTypeDO;
import com.yinwang.information.domain.RepairDO;
import com.yinwang.information.service.MaintainTypeService;
import com.yinwang.information.service.RepairService;
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
@RequestMapping("/information/repair")
public class RepairController extends BaseController{
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
	
	
	@GetMapping()
	String Repair(Model model){
		OwnerUserDO user = this.getUser();
		String name = user.getPhone();
		if(StringUtils.isNotBlank(user.getNickname())){
			name = user.getNickname();
		}
		if(StringUtils.isNotBlank(user.getName())){
			name = user.getName();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deleteFlag", 0);
		map.put("pId", 1);
		//类型
		List<MaintainTypeDO> listType = maintainTypeService.list(map);
		map.put("pId", 2);
		//类别
		List<MaintainTypeDO> listCategory = maintainTypeService.list(map);
		
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
		
		
		
		model.addAttribute("listType", listType);//类型
		model.addAttribute("listCategory", listCategory);//类别
		model.addAttribute("listType0", listType.get(0));//类型
		model.addAttribute("listCategory0", listCategory.get(0));//类别
		model.addAttribute("name", name);//姓名
		model.addAttribute("phone", user.getPhone());//手机号
		
		return "information/repair/weixiuxiangqing";
	}
	
	/**
	 * 我的服务
	 * @param statue
	 * @param model
	 * @return
	 */
	@GetMapping("/listMap")
	public String listMap(String statue, Model model,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", this.getUserId());
		map.put("statue", statue);
		List<Map<String, Object>> listMap = this.repairService.listMap(map);
		String code = request.getParameter("code");
		System.out.println("====code======"+code);
		String urlop = "https://api.weixin.qq.com/sns/oauth2/access_token";
		String paramop="appid=wxa2ee172ca5977381&secret=8f45a11773809ba9f8ca6abb6d599dc9&code="+code+"&grant_type=authorization_code";
		String resu = sendGet(urlop, paramop);
		Map<String, String> mapop = new HashMap<String, String>();
		mapop=JSON.parseObject(resu, Map.class);
		String openid = "";
		openid=mapop.get("openid");
		model.addAttribute("openid", openid);//姓名
		model.addAttribute("listMap", listMap);//服务列表
		model.addAttribute("statue", statue);
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
