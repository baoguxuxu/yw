package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 公告小区中间表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-20 09:18:37
 */
public class NoticePlotDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//公告ID
	private Long noticeId;
	//小区ID
	private Long plotId;
	//添加时间
	private Date addTime;
	
	public NoticePlotDO(){}

	public NoticePlotDO(Long noticeId,Long plotId){
		this.noticeId = noticeId;
		this.plotId = plotId;
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
	 * 设置：公告ID
	 */
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	/**
	 * 获取：公告ID
	 */
	public Long getNoticeId() {
		return noticeId;
	}
	/**
	 * 设置：小区ID
	 */
	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}
	/**
	 * 获取：小区ID
	 */
	public Long getPlotId() {
		return plotId;
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
