package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问卷题目表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 11:32:26
 */
public class TopicDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//问卷id
	private Integer questionnaireId;
	//标题
	private String name;
	//内容
	private String details;
	//0：单选；1：多选;2:填写
	private Integer isRadio;
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
	 * 设置：问卷id
	 */
	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * 获取：问卷id
	 */
	public Integer getQuestionnaireId() {
		return questionnaireId;
	}
	/**
	 * 设置：标题
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：标题
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：内容
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * 获取：内容
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * 设置：0：是；1：否
	 */
	public void setIsRadio(Integer isRadio) {
		this.isRadio = isRadio;
	}
	/**
	 * 获取：0：是；1：否
	 */
	public Integer getIsRadio() {
		return isRadio;
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
