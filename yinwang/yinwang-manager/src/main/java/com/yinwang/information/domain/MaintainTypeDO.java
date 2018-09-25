package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 维修类型类别表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-23 15:11:36
 */
public class MaintainTypeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String name;
	//
	private Long pId;
	//
	private Date addTime;
	//
	private Date updateTime;
	//0：是；1：否
	private Integer deleteFlag;

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
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setPId(Long pId) {
		this.pId = pId;
	}
	/**
	 * 获取：
	 */
	public Long getPId() {
		return pId;
	}
	/**
	 * 设置：
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
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
