package com.yinwang.plot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.yinwang.plot.dao.PlotDao;
import com.yinwang.plot.domain.PlotDO;
import com.yinwang.plot.domain.TArea;
import com.yinwang.plot.service.PlotService;



@Service
public class PlotServiceImpl implements PlotService {
	@Autowired
	private PlotDao plotDao;
	
	@Override
	public PlotDO get(Long id){
		return plotDao.get(id);
	}
	
	@Override
	public List<PlotDO> list(Map<String, Object> map){
		return plotDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return plotDao.count(map);
	}
	
	@Override
	public int save(PlotDO plot){
		return plotDao.save(plot);
	}
	
	@Override
	public int update(PlotDO plot){
		return plotDao.update(plot);
	}
	
	@Override
	public int remove(Integer id){
		return plotDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return plotDao.batchRemove(ids);
	}
	//地区下拉框事件数据  
		@Override
		public String selectAreaDate(){
			List<TArea> provArea = findArea(1,100000);//所有省  
			List<TArea> cityArea = findArea(2,null);//所有市
			List<TArea> distArea = findArea(3,null);//所有区
			
			JSONArray json = new JSONArray();//全部信息
			//遍历省  保存省信息
			if(provArea!=null && provArea.size()>0){
				for (TArea prov : provArea) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("provCode", prov.getCode());
					map.put("provName", prov.getAreaName());
					map.put("city", cityAreaLevel(prov.getCode(),cityArea,distArea));//市
					System.out.println("json==="+map.toString());
					json.add(map);
				}
			}
			
			return json.toJSONString();
		}
		//查找  等级、父级编码
		@Override
		public List<TArea> findArea(Integer level, Integer parentCode) {
			TArea tare= new TArea();
			tare.setAreaLevel(level);
			tare.setParentCode(parentCode);
			return plotDao.listArea(tare);
		}
		
		//对应的市
		public JSONArray cityAreaLevel(Integer parentCode,List<TArea> cityArea,List<TArea> dictArea){
			JSONArray json = new JSONArray();
			if(cityArea!=null && cityArea.size()>0){
				for (TArea area : cityArea) {
					if(area.getParentCode().equals(parentCode)){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("cityCode", area.getCode());//市code码
						map.put("cityName", area.getAreaName());//市名称
						map.put("dist", dictAreaLevel(area.getCode(),dictArea));//区
						json.add(map);
					}
				}
			}
			return json;
		}
		//对应的区
		public JSONArray dictAreaLevel(Integer parentCode,List<TArea> dictArea){
			JSONArray json = new JSONArray();
			
			if(dictArea!=null && dictArea.size()>0){
				for (TArea area : dictArea) {
					if(area.getParentCode().equals(parentCode)){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("distCode", area.getCode());//区code码
						map.put("distName", area.getAreaName());//区名称
						
						json.add(map);
					}
				}
			}
			return json;
		}
		
	
}
