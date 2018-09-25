package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;



/**
 * 投诉表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-13 14:32:12
 */
public class ComplaintDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//投诉类型
	private Integer type;
	//标题
	private String title;
	//投诉内容
	private String content;
	//小区ID
	private Integer plotId;
	//投诉人id
	private Integer userId;
	//状态0:处理1:完成
	private Integer statue;
	
	private Date addTime;
	
	private MultipartFile[] listImg; //投诉图
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
	 * 设置：投诉类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：投诉类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：投诉内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：投诉内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：小区ID
	 */
	public void setPlotId(Integer plotId) {
		this.plotId = plotId;
	}
	/**
	 * 获取：小区ID
	 */
	public Integer getPlotId() {
		return plotId;
	}
	/**
	 * 设置：投诉人id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：投诉人id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：状态0:处理1:完成
	 */
	public void setStatue(Integer statue) {
		this.statue = statue;
	}
	/**
	 * 获取：状态0:处理1:完成
	 */
	public Integer getStatue() {
		return statue;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public MultipartFile[] getListImg() {
		return listImg;
	}
	public void setListImg(MultipartFile[] listImg) {
		this.listImg = listImg;
	}
	
}
