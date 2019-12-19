package com.wangjie.cms.entity;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable{                  ;
	private int id                   ;  // id
	private String keywords             ; // keywords
	private String description          ;   // description
	private String companyName          ; //公司名称
	private String mainBusinessProducts ; //  主营产品
	private String address              ;  // 地址
	private String registeredCapital    ; //    注册资本
	private String foundingTime         ;  // 成立时间
	private String annualCheckDate      ;  // 年检日期
	private String annualCheckStatus    ;  //  年检状态
	private String rmk                  ;  //备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMainBusinessProducts() {
		return mainBusinessProducts;
	}
	public void setMainBusinessProducts(String mainBusinessProducts) {
		this.mainBusinessProducts = mainBusinessProducts;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getFoundingTime() {
		return foundingTime;
	}
	public void setFoundingTime(String foundingTime) {
		this.foundingTime = foundingTime;
	}
	public String getAnnualCheckDate() {
		return annualCheckDate;
	}
	public void setAnnualCheckDate(String annualCheckDate) {
		this.annualCheckDate = annualCheckDate;
	}
	public String getAnnualCheckStatus() {
		return annualCheckStatus;
	}
	public void setAnnualCheckStatus(String annualCheckStatus) {
		this.annualCheckStatus = annualCheckStatus;
	}
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	
	
	
}
