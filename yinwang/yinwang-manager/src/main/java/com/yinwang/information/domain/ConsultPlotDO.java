package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 资讯小区中间表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-17 16:22:30
 */
public class ConsultPlotDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//资讯ID
	private Long consultId;
	//小区ID
	private Long plotId;
	
	
	public ConsultPlotDO(){};
	public ConsultPlotDO(Long consultId,Long plotId){
		this.consultId = consultId;
		this.plotId = plotId;
	};

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：资讯ID
	 */
	public void setConsultId(Long consultId) {
		this.consultId = consultId;
	}
	/**
	 * 获取：资讯ID
	 */
	public Long getConsultId() {
		return consultId;
	}
	/**
	 * 设置：小区ID
	 */
	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}
	/**
	 * 获取：小区ID
	 */
	public Long getPlotId() {
		return plotId;
	}
}
