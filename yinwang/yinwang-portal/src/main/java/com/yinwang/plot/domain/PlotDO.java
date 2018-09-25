package com.yinwang.plot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 小区表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-13 15:26:20
 */
public class PlotDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//小区名称
	private String name;
	//联系人
	private String userName;
	//联系电话
	private String phone;
	//用户id
	private Integer userId;
	//省
	private String province;
	//市
	private String city;
	//小区地址
	private String address;
	//详细地址
	private String detailedAddress;
	//小区经纬度
	private String longitude;
	//小区楼数
	private Integer towerNum;
	//单元数
	private Integer unitNum;
	//层数
	private Integer tierNum;
	//物业费
	private Double propertyFee;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	//0：是；1：否
	private Integer daleteFlag;

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
	 * 设置：小区名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：小区名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：联系人
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：联系人
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
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
	 * 设置：省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：小区地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：小区地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：详细地址
	 */
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	/**
	 * 获取：详细地址
	 */
	public String getDetailedAddress() {
		return detailedAddress;
	}
	/**
	 * 设置：小区经纬度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：小区经纬度
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * 设置：小区楼数
	 */
	public void setTowerNum(Integer towerNum) {
		this.towerNum = towerNum;
	}
	/**
	 * 获取：小区楼数
	 */
	public Integer getTowerNum() {
		return towerNum;
	}
	/**
	 * 设置：单元数
	 */
	public void setUnitNum(Integer unitNum) {
		this.unitNum = unitNum;
	}
	/**
	 * 获取：单元数
	 */
	public Integer getUnitNum() {
		return unitNum;
	}
	/**
	 * 设置：层数
	 */
	public void setTierNum(Integer tierNum) {
		this.tierNum = tierNum;
	}
	/**
	 * 获取：层数
	 */
	public Integer getTierNum() {
		return tierNum;
	}
	/**
	 * 设置：物业费
	 */
	public void setPropertyFee(Double propertyFee) {
		this.propertyFee = propertyFee;
	}
	/**
	 * 获取：物业费
	 */
	public Double getPropertyFee() {
		return propertyFee;
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
	public void setDaleteFlag(Integer daleteFlag) {
		this.daleteFlag = daleteFlag;
	}
	/**
	 * 获取：0：是；1：否
	 */
	public Integer getDaleteFlag() {
		return daleteFlag;
	}
}
