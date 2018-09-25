package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;



/**
 * 咨询表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-13 14:42:52
 */
public class ConsultDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//0：物业动态；1：小区广告；2：小区新闻
	private Integer type;
	//推送小区
	private String forNames;
	//推送小区id
	private String forIds;
	//标题
	private String title;
	//图片
	private String url;
	//是否禁用
	private Integer isDisabled;
	//内容
	private String details;
	//浏览量
	private Integer browseNum;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	//0：是；1：否
	private Integer deleteFlag;
	
	private MultipartFile imgFile;
	
	private Long userId;

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
	 * 设置：0：物业动态；1：小区广告；2：小区新闻
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：0：物业动态；1：小区广告；2：小区新闻
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：推送小区
	 */
	public void setForNames(String forNames) {
		this.forNames = forNames;
	}
	/**
	 * 获取：推送小区
	 */
	public String getForNames() {
		return forNames;
	}
	/**
	 * 设置：推送小区id
	 */
	public void setForIds(String forIds) {
		this.forIds = forIds;
	}
	/**
	 * 获取：推送小区id
	 */
	public String getForIds() {
		return forIds;
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
	 * 设置：图片
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：图片
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：是否禁用
	 */
	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
	/**
	 * 获取：是否禁用
	 */
	public Integer getIsDisabled() {
		return isDisabled;
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
	 * 设置：浏览量
	 */
	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}
	/**
	 * 获取：浏览量
	 */
	public Integer getBrowseNum() {
		return browseNum;
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
	public MultipartFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
