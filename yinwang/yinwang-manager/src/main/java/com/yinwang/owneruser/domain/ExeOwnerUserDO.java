package com.yinwang.owneruser.domain;

import java.io.Serializable;
import java.util.Date;

public class ExeOwnerUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//昵称
	private String nickname;
	//手机号
	private String phone;
	//真实姓名
	private String name;
	//注册时间
	private String registerTime;
	//消费金额
	private Double payNum;
	//服务次数
	private Integer serveNum;
	//余额
	private Double balance;
	//缴费日期
	private String payTime;
	//最后登录时间
	private String loginTime;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPayNum() {
		return payNum;
	}
	public void setPayNum(Double payNum) {
		this.payNum = payNum;
	}
	public Integer getServeNum() {
		return serveNum;
	}
	public void setServeNum(Integer serveNum) {
		this.serveNum = serveNum;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	

}
