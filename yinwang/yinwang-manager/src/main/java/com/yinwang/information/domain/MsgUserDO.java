package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 消息用户中间表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-16 15:52:25
 */
public class MsgUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户ID
	private Long userId;
	//消息ID
	private Long msgId;
	//状态
	private Integer statue;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	
	public MsgUserDO(){}
	
	public MsgUserDO(Long userId,Long msgId){
		this.userId = userId;
		this.msgId = msgId;
		this.statue = 0;
		this.addTime = new Date();
		this.updateTime = new Date();
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
	 * 设置：消息ID
	 */
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	/**
	 * 获取：消息ID
	 */
	public Long getMsgId() {
		return msgId;
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
}
