package com.yinwang.owneruser.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;



/**
 * 维修表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-25 16:17:51
 */
public class RepairDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户ID
	private Long userId;
	//地址id
	private Long addId;
	//报修类型
	private Long typeId;
	//报修类别
	private Long categoryId;
	//维修时间
	private Date repairTime;
	//详情
	private String details;
	//添加时间
	private Date addTime;
	//金额
	private BigDecimal money;
	//状态:0：申请、待报价；1：代付款；2：待评价；3：已完成；5：删除
	private Integer statue;
	
	private Integer repairStatue;
	private Long repairUserId;
	private BigDecimal serviceCharge;
	private String replacementItem;
	private BigDecimal priceGoods;
	private MultipartFile[] listImg; //维修图

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
	 * 设置：地址id
	 */
	public void setAddId(Long addId) {
		this.addId = addId;
	}
	/**
	 * 获取：地址id
	 */
	public Long getAddId() {
		return addId;
	}
	/**
	 * 设置：报修类型
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：报修类型
	 */
	public Long getTypeId() {
		return typeId;
	}
	/**
	 * 设置：报修类别
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：报修类别
	 */
	public Long getCategoryId() {
		return categoryId;
	}
	/**
	 * 设置：维修时间
	 */
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	/**
	 * 获取：维修时间
	 */
	public Date getRepairTime() {
		return repairTime;
	}
	/**
	 * 设置：详情
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * 获取：详情
	 */
	public String getDetails() {
		return details;
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
	 * 设置：金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：状态:0：申请、待报价；1：代付款；2：已完成；5：删除
	 */
	public void setStatue(Integer statue) {
		this.statue = statue;
	}
	/**
	 * 获取：状态:0：申请、待报价；1：代付款；2：已完成；5：删除
	 */
	public Integer getStatue() {
		return statue;
	}
	public MultipartFile[] getListImg() {
		return listImg;
	}
	public void setListImg(MultipartFile[] listImg) {
		this.listImg = listImg;
	}
	public Integer getRepairStatue() {
		return repairStatue;
	}
	public void setRepairStatue(Integer repairStatue) {
		this.repairStatue = repairStatue;
	}
	public Long getRepairUserId() {
		return repairUserId;
	}
	public void setRepairUserId(Long repairUserId) {
		this.repairUserId = repairUserId;
	}
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getReplacementItem() {
		return replacementItem;
	}
	public void setReplacementItem(String replacementItem) {
		this.replacementItem = replacementItem;
	}
	public BigDecimal getPriceGoods() {
		return priceGoods;
	}
	public void setPriceGoods(BigDecimal priceGoods) {
		this.priceGoods = priceGoods;
	}
	
}
