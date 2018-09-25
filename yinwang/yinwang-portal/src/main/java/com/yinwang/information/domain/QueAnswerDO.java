package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问卷答案表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 13:47:58
 */
public class QueAnswerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户ID
	private Long userId;
	//题目ID
	private Long topicId;
	//选项ID
	private Long optionId;
	//填空题答案
	private String content;
	//添加时间
	private Date addTime;
	
	private String checked;
	private String name;

	
	public QueAnswerDO(){}
	/**
	 * 
	 * @param userId 用户ID
	 * @param topicId 题目ID
	 * @param optionId 选项ID
	 * @param content 内容
	 */
	public QueAnswerDO(Long userId,Long topicId,Long optionId,String content){
		this.userId = userId;
		this.topicId = topicId;
		this.optionId = optionId;
		this.content = content;
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
	 * 设置：题目ID
	 */
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	/**
	 * 获取：题目ID
	 */
	public Long getTopicId() {
		return topicId;
	}
	/**
	 * 设置：选项ID
	 */
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	/**
	 * 获取：选项ID
	 */
	public Long getOptionId() {
		return optionId;
	}
	/**
	 * 设置：填空题答案
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：填空题答案
	 */
	public String getContent() {
		return content;
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
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
