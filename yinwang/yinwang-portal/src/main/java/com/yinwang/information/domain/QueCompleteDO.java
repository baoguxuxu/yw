package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 已完成问卷调查表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 18:17:53
 */
public class QueCompleteDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户ID
	private Long userId;
	//问卷ID
	private Long questionnaireId;
	//时间
	private Date addTime;

	public QueCompleteDO(){}
	
	public QueCompleteDO(Long userId,Long questionnaireId){
		this.userId = userId;
		this.questionnaireId = questionnaireId;
		this.addTime = new Date();
	}
	
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
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：问卷ID
	 */
	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * 获取：问卷ID
	 */
	public Long getQuestionnaireId() {
		return questionnaireId;
	}
	/**
	 * 设置：时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：时间
	 */
	public Date getAddTime() {
		return addTime;
	}
}
