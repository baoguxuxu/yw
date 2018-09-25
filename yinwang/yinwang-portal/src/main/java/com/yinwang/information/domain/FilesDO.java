package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 附件表，用于存储附件的关联表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-19 14:23:59
 */
public class FilesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//类型 1:投诉 2:维修
	private Integer type;
	//关联表ID
	private Integer tableId;
	//地址
	private String url;
	//状态
	private Integer statue;
	//添加时间
	private Date addTime;
	
	public FilesDO(Integer type,Integer tableId,String url){
		this.type = type;
		this.tableId = tableId;
		this.url = url;
		this.statue = 0;
		this.addTime = new Date();
	}

	public FilesDO() {
		super();
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：关联表ID
	 */
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
	/**
	 * 获取：关联表ID
	 */
	public Integer getTableId() {
		return tableId;
	}
	/**
	 * 设置：地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：状态
	 */
	public void setStatue(Integer statue) {
		this.statue = statue;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatue() {
		return statue;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}
}
