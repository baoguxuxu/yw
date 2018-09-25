package com.yinwang.jiaofei.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 支付订单
 * 
 */
public class PayOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private long id;
	/**
	 * 支付订单号
	 */
	private String orderNo;
	/**
	 * 支付项目订单号
	 */
	private String outOrderNo;
	/**
	 * 支付金额
	 */
	private BigDecimal totalFee;
	/**
	 * descr
	 */
	private String body;
	/**
	 * attach
	 */
	private String attach;
	/**
	 * 购买时间
	 */
	private Date createTime;
	/**
	 * 支付用户
	 */
	private Long createUser;
	/**
	 * 购买时间
	 */
	private Long payTime;
	/**
	 * ZFB、WX
	 */
	private String payWay;
	/**
	 * ZFB、WX
	 */
	private String tradeType;
	/**
	 * 订单状态0=待支付，1=已支付，-1=支付异常
	 */
	private Integer status;
	/**
	 * 第三方支付订单号
	 */
	private String tradeNo;
	/**
	 * 支付ip
	 */
	private String spbillCreateIp;
	/**
	 * 回调地址
	 */
	private String notifyUrl;
	/**
	 * 队列key
	 */
	private String queueKey;
	/**
	 * appid
	 */
	private String appid;
	/**
	 * 商户id
	 */
	private String mchId;
	/**
	 * 回调参数（JSON）
	 */
	private String callRequestBody;
	/**
	 * 分组
	 */
	private String group;

	/**
	 * 设置：
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public long getId() {
		return id;
	}
	/**
	 * 设置：支付订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：支付订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：支付项目订单号
	 */
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	/**
	 * 获取：支付项目订单号
	 */
	public String getOutOrderNo() {
		return outOrderNo;
	}
	/**
	 * 设置：支付金额
	 */
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * 获取：支付金额
	 */
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	/**
	 * 设置：descr
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * 获取：descr
	 */
	public String getBody() {
		return body;
	}
	/**
	 * 设置：attach
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}
	/**
	 * 获取：attach
	 */
	public String getAttach() {
		return attach;
	}
	/**
	 * 设置：购买时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：购买时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：支付用户
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：支付用户
	 */
	public Long getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：购买时间
	 */
	public void setPayTime(Long payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：购买时间
	 */
	public Long getPayTime() {
		return payTime;
	}
	/**
	 * 设置：ZFB、WX
	 */
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	/**
	 * 获取：ZFB、WX
	 */
	public String getPayWay() {
		return payWay;
	}
	/**
	 * 设置：ZFB、WX
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * 获取：ZFB、WX
	 */
	public String getTradeType() {
		return tradeType;
	}
	/**
	 * 设置：订单状态0=待支付，1=已支付，-1=支付异常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态0=待支付，1=已支付，-1=支付异常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：第三方支付订单号
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * 获取：第三方支付订单号
	 */
	public String getTradeNo() {
		return tradeNo;
	}
	/**
	 * 设置：支付ip
	 */
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	/**
	 * 获取：支付ip
	 */
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	/**
	 * 设置：回调地址
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	/**
	 * 获取：回调地址
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * 设置：队列key
	 */
	public void setQueueKey(String queueKey) {
		this.queueKey = queueKey;
	}
	/**
	 * 获取：队列key
	 */
	public String getQueueKey() {
		return queueKey;
	}
	/**
	 * 设置：appid
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * 获取：appid
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * 设置：商户id
	 */
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	/**
	 * 获取：商户id
	 */
	public String getMchId() {
		return mchId;
	}
	/**
	 * 设置：回调参数（JSON）
	 */
	public void setCallRequestBody(String callRequestBody) {
		this.callRequestBody = callRequestBody;
	}
	/**
	 * 获取：回调参数（JSON）
	 */
	public String getCallRequestBody() {
		return callRequestBody;
	}
	/**
	 * 设置：分组
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * 获取：分组
	 */
	public String getGroup() {
		return group;
	}
}
