package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 公告表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-13 13:10:58
 */
public class NoticeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//公告标题
	private String title;
	//推送对象
	private String forNames;
	//推送对象id
	private String forIds;
	//公告内容
	private String details;
	//推送人数
	private Integer forNum;
	//阅读人数
	private Integer num;
	//添加时间
	private Date addTime;
	//添加时间
	private String addTime1;
	//修改时间
	private Date updateTime;
	//0：是；1：否
	private Integer daleteFlag;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：公告标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：公告标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：推送对象
	 */
	public void setForNames(String forNames) {
		this.forNames = forNames;
	}
	/**
	 * 获取：推送对象
	 */
	public String getForNames() {
		return forNames;
	}
	/**
	 * 设置：推送对象id
	 */
	public void setForIds(String forIds) {
		this.forIds = forIds;
	}
	/**
	 * 获取：推送对象id
	 */
	public String getForIds() {
		return forIds;
	}
	/**
	 * 设置：公告内容
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * 获取：公告内容
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * 设置：推送人数
	 */
	public void setForNum(Integer forNum) {
		this.forNum = forNum;
	}
	/**
	 * 获取：推送人数
	 */
	public Integer getForNum() {
		return forNum;
	}
	/**
	 * 设置：阅读人数
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：阅读人数
	 */
	public Integer getNum() {
		return num;
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
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：0：是；1：否
	 */
	public void setDaleteFlag(Integer daleteFlag) {
		this.daleteFlag = daleteFlag;
	}
	/**
	 * 获取：0：是；1：否
	 */
	public Integer getDaleteFlag() {
		return daleteFlag;
	}
	public String getAddTime1() {
		return addTime1;
	}
	public void setAddTime1(String addTime1) {
		this.addTime1 = addTime1;
	}
	
}
