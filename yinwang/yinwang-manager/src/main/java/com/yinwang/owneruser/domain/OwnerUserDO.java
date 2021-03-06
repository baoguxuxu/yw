package com.yinwang.owneruser.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OwnerUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//微信id
	private String openId;
	//昵称
	private String nickname;
	//密码
	private String password;
	//手机号
	private String phone;
	//头像
	private String heardUrl;
	//真实姓名
	private String name;
	//身份证号
	private String identityCard;
	//注册时间
	private Date registerTime;
	//注册时间
	private String registerDate;
	//消费金额
	private Double payNum;
	//服务次数
	private Integer serveNum;
	//余额
	private Double balance;
	//返还
	private Double restitution;
	//缴费日期
	private Date payTime;
	//最后登录时间
	private Date loginTime;
	private String loginDate;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	//0：是；1：否
	private Integer deleteFlag;
	//详细地址
	private String address;
	//房屋关系
	private String relation;
	//物业费
	private Double wuyefei;
	//面积
	private Double mianji;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：微信id
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：微信id
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：头像
	 */
	public void setHeardUrl(String heardUrl) {
		this.heardUrl = heardUrl;
	}
	/**
	 * 获取：头像
	 */
	public String getHeardUrl() {
		return heardUrl;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：身份证号
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdentityCard() {
		return identityCard;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	/**
	 * 设置：消费金额
	 */
	public void setPayNum(Double payNum) {
		this.payNum = payNum;
	}
	/**
	 * 获取：消费金额
	 */
	public Double getPayNum() {
		return payNum;
	}
	/**
	 * 设置：服务次数
	 */
	public void setServeNum(Integer serveNum) {
		this.serveNum = serveNum;
	}
	/**
	 * 获取：服务次数
	 */
	public Integer getServeNum() {
		return serveNum;
	}
	/**
	 * 设置：余额
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	/**
	 * 获取：余额
	 */
	public Double getBalance() {
		return balance;
	}
	/**
	 * 设置：返还
	 */
	public void setRestitution(Double restitution) {
		this.restitution = restitution;
	}
	/**
	 * 获取：返还
	 */
	public Double getRestitution() {
		return restitution;
	}
	/**
	 * 设置：缴费日期
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：缴费日期
	 */
	public Date getPayTime() {
		return payTime;
	}
	/**
	 * 设置：最后登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取：最后登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
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
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
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
