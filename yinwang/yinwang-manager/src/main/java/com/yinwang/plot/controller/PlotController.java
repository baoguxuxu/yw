package com.yinwang.plot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.springframework.web.multipart.MultipartFile;

import com.yinwang.owneruser.domain.OwnerUserDO;
import com.yinwang.plot.domain.PlotDO;
import com.yinwang.plot.service.PlotService;
import com.yinwang.system.config.ExcelUtils;
import com.yinwang.system.domain.RoleDO;
import com.yinwang.system.domain.UserDO;
import com.yinwang.system.service.RoleService;
import com.yinwang.system.service.UserService;
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
	@Autowired
	RoleService roleService;
	@Autowired
	UserService userService;
	
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
		List<UserDO> users = userService.list(null);
		model.addAttribute("users", users);
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
	    return "plot/add";
	}
	
	@GetMapping("/exportplot")
	String exportplot(Model model){
		return "plot/exportplot";
	}
	@PostMapping("/exportplotQ")
	@ResponseBody
	public R  exportplotQ(MultipartFile file){
		int num = 0;
		InputStream in=null;
		Workbook book=null;
		try{
			if(file != null){
				in = file.getInputStream();
				book =ExcelUtils.getBook(in);
				Sheet sheet = book.getSheetAt(0);
				Row row=null;
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					try {
						row = sheet.getRow(rowNum);
						String name = ExcelUtils.getCellFormatValue(row.getCell((short)0));
						String province = ExcelUtils.getCellFormatValue(row.getCell((short)1));
						String city = ExcelUtils.getCellFormatValue(row.getCell((short)2));
						String address = ExcelUtils.getCellFormatValue(row.getCell((short)3));
						String towerNum = ExcelUtils.getCellFormatValue(row.getCell((short)4));
						String unitNum = ExcelUtils.getCellFormatValue(row.getCell((short)5));
						String tierNum = ExcelUtils.getCellFormatValue(row.getCell((short)6));
						String userName = ExcelUtils.getCellFormatValue(row.getCell((short)7));
						String phone = ExcelUtils.getCellFormatValue(row.getCell((short)8));
						PlotDO pdo = new PlotDO();
						if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(province)&& StringUtils.isNotBlank(city)&& StringUtils.isNotBlank(address)&& StringUtils.isNotBlank(userName)&& StringUtils.isNotBlank(phone)){
							pdo.setName(name);
							pdo.setProvince(province);
							pdo.setCity(city);
							pdo.setAddress(address);
							pdo.setDetailedAddress(address);
							pdo.setTowerNum(Integer.parseInt(towerNum));
							pdo.setUnitNum(Integer.parseInt(unitNum));
							pdo.setTierNum(Integer.parseInt(tierNum));
							pdo.setUserName(userName);
							pdo.setPhone(phone);
							pdo.setDaleteFlag(0);
						}else{
							return R.error("导入失败======第"+rowNum+"行不能为空==================");
						}
						plotService.save(pdo);
						num ++;
					} catch (Exception e) {
						System.out.println("导入失败======第"+rowNum+"条==================");
						e.printStackTrace();
					}
				}
				return R.ok("上传成功,共增加["+num+"]条");
			}else{
				return R.error("请选择导入的文件!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(book!=null)
					book.close();
				if(book!=null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return R.error();
	}

	@GetMapping("/show/{id}")
	@RequiresPermissions("sys:plot:chakan")
	String edit(@PathVariable("id") Long id,Model model){
		PlotDO plot = plotService.get(id);
		model.addAttribute("plot", plot);
//		List<UserDO> udo = userService.getbyphone(plot.getPhone());
//		
//		List<RoleDO> roles = roleService.listbyid(udo.get(0).getUserId());
//		model.addAttribute("roles", roles);
//		model.addAttribute("loginname", udo.get(0).getUsername());
	    return "plot/show";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( PlotDO plot){
		UserDO user = userService.get(Long.parseLong(plot.getUserName()));
		plot.setUserName(user.getName());
		plot.setPhone(user.getMobile());
		plot.setDaleteFlag(0);
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
