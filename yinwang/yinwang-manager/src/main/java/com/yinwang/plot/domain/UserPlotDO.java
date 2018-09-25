package com.yinwang.plot.domain;

import java.io.Serializable;

/**
 * 用户小区表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-18 15:21:14
 */
public class UserPlotDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//用户id
	private Integer userId;
	//小区id
	private Integer plotId;
	//是否删除
	private Integer deleteFlag;
	//详细地址
	private String address;
	//房屋关系
	private String relation;
	//房东姓名
	private String username;
	//房东姓名
	private String identityCard;
	//房东姓名
	private String fangdongname;
	//房东身份证
	private String fangdongid;
	//房东手机号
	private String fangdongphone;
	//物业费
	private Double wuyefei;
	//面积
	private Double mianji;

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
	 * 设置：用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：小区id
	 */
	public void setPlotId(Integer plotId) {
		this.plotId = plotId;
	}
	/**
	 * 获取：小区id
	 */
	public Integer getPlotId() {
		return plotId;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：房屋关系
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}
	/**
	 * 获取：房屋关系
	 */
	public String getRelation() {
		return relation;
	}
	public String getFangdongname() {
		return fangdongname;
	}
	public void setFangdongname(String fangdongname) {
		this.fangdongname = fangdongname;
	}
	public String getFangdongid() {
		return fangdongid;
	}
	public void setFangdongid(String fangdongid) {
		this.fangdongid = fangdongid;
	}
	public String getFangdongphone() {
		return fangdongphone;
	}
	public void setFangdongphone(String fangdongphone) {
		this.fangdongphone = fangdongphone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public Double getWuyefei() {
		return wuyefei;
	}
	public void setWuyefei(Double wuyefei) {
		this.wuyefei = wuyefei;
	}
	public Double getMianji() {
		return mianji;
	}
	public void setMianji(Double mianji) {
		this.mianji = mianji;
	}
	
}
