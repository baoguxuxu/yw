package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 消息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-12 16:00:47
 */
public class MsgDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//消息名称
	private String name;
	//推送类型
	private Integer type;
	//推送对象
	private String forNames;
	//推送对象id集
	private String forIds;
	//0：小区；1：个人
	private Integer forType;
	//推送内容
	private String forDetails;
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
	 * 设置：消息名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：消息名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：推送类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：推送类型
	 */
	public Integer getType() {
		return type;
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
	 * 设置：推送对象id集
	 */
	public void setForIds(String forIds) {
		this.forIds = forIds;
	}
	/**
	 * 获取：推送对象id集
	 */
	public String getForIds() {
		return forIds;
	}
	/**
	 * 设置：0：小区；1：个人
	 */
	public void setForType(Integer forType) {
		this.forType = forType;
	}
	/**
	 * 获取：0：小区；1：个人
	 */
	public Integer getForType() {
		return forType;
	}
	/**
	 * 设置：推送内容
	 */
	public void setForDetails(String forDetails) {
		this.forDetails = forDetails;
	}
	/**
	 * 获取：推送内容
	 */
	public String getForDetails() {
		return forDetails;
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
}
