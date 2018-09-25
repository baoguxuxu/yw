package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 10:11:20
 */
public class QuestionnaireDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//问卷标题
	private String title;
	//问卷简介
	private String details;
	//推送对象
	private String forNames;
	//推送对象集
	private String forIds;
	//调查人数
	private Integer number;
	//参与人数
	private Integer participant;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//开始时间
	private String startTime1;
	//结束时间
	private String endTime1;
	//0：是；1：否
	private Integer isShow;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	//0：是；1：否
	private Integer deleteFlag;

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
	 * 设置：问卷标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：问卷标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：问卷简介
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * 获取：问卷简介
	 */
	public String getDetails() {
		return details;
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
	 * 设置：推送对象集
	 */
	public void setForIds(String forIds) {
		this.forIds = forIds;
	}
	/**
	 * 获取：推送对象集
	 */
	public String getForIds() {
		return forIds;
	}
	/**
	 * 设置：调查人数
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：调查人数
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * 设置：参与人数
	 */
	public void setParticipant(Integer participant) {
		this.participant = participant;
	}
	/**
	 * 获取：参与人数
	 */
	public Integer getParticipant() {
		return participant;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：0：是；1：否
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	/**
	 * 获取：0：是；1：否
	 */
	public Integer getIsShow() {
		return isShow;
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
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 获取：0：是；1：否
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public String getStartTime1() {
		return startTime1;
	}
	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}
	public String getEndTime1() {
		return endTime1;
	}
	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}
}
