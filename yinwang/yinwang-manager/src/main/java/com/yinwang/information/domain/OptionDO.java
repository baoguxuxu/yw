package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问卷选项表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 12:58:18
 */
public class OptionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//问卷题目id
	private Integer topicId;
	//选项名称
	private String name;
	//选择次数
	private Integer num;
	//选择题目总次数
	private Integer totalNum;
	//占比
	private Double accountFor;
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
	 * 设置：问卷题目id
	 */
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	/**
	 * 获取：问卷题目id
	 */
	public Integer getTopicId() {
		return topicId;
	}
	/**
	 * 设置：选项名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：选项名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：选择次数
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：选择次数
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：占比
	 */
	public void setAccountFor(Double accountFor) {
		this.accountFor = accountFor;
	}
	/**
	 * 获取：占比
	 */
	public Double getAccountFor() {
		return accountFor;
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
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
}
