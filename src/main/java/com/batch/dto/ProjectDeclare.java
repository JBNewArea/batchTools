/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.batch.dto;
import java.util.Date;

/**
 * 项目入库Entity
 * @author freedom_taojie
 * @version 2018-04-10
 */
public class ProjectDeclare {
	
	private static final long serialVersionUID = 1L;
	private String declareDate;		// 申报时间
	private String projectCode;		// 项目代码
	private String projectType;		// 项目类型
	private String projectAttribute;		// 项目属性
	private String projectName;		// 项目名称
	private String projectUnit;		// 申报单位
	private String legalUnitInfo;		// 法人单位信息
	private String money;		// 项目投资额
	private String guobiao;		// 国标行业
	private String guanli;		// 管理行业
	private String buildPlace;		// 建设地点
	private String buildDetailPlace;		// 建设详细地址
	private String scaleandinfo;		// 建设规模及内容
	private String name;		// 姓名
	private String phone;		// 联系方式
	
	private String nameFirst;	//名称
	private String statusFirst;	//办理状态
	private String wenhaoFirst;	//办结文号
	private Date riqiFirst;	//日期
	private String bumenFirst;	//部门
	private Date caozuodateFirst;	//实际操作时间
	
	private String bihao;//编号，用于填充excel
	private String beizhu; //用于读取备注填充remarks
	
	
	private String transstreet;//所属街道
	private String transterrace;//所属平台
	
	private String longitude;//经度
	private String latitude;//纬度
	private String platform;
	
	private Date dateStart;
	private Date dateEnd;
	
	private String dateYear;
	
	
	
	
	private ProjectDeclare projectDeclare;		// 项目申报ID 父类
	private String handleStatus;		// 办理状态
	private String endCode;		// 办结文号
	private String date;		// 日期
	private String office;		// 部门
	private String caozuoDate;		// 实际操作时间
	
	public String getDateYear() {
		return dateYear;
	}

	public void setDateYear(String dateYear) {
		this.dateYear = dateYear;
	}

	public ProjectDeclare() {
		super();
	}
	
												
	public String getBihao() {
		return bihao;
	}

	public void setBihao(String bihao) {
		this.bihao = bihao;
	}

	public String getDeclareDate() {
		return declareDate;
	}

	public void setDeclareDate(String declareDate) {
		this.declareDate = declareDate;
	}
	
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	public String getProjectAttribute() {
		return projectAttribute;
	}

	public void setProjectAttribute(String projectAttribute) {
		this.projectAttribute = projectAttribute;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectUnit() {
		return projectUnit;
	}

	public void setProjectUnit(String projectUnit) {
		this.projectUnit = projectUnit;
	}
	
	public String getLegalUnitInfo() {
		return legalUnitInfo;
	}

	public void setLegalUnitInfo(String legalUnitInfo) {
		this.legalUnitInfo = legalUnitInfo;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	public String getGuobiao() {
		return guobiao;
	}

	public void setGuobiao(String guobiao) {
		this.guobiao = guobiao;
	}
	
	public String getGuanli() {
		return guanli;
	}

	public void setGuanli(String guanli) {
		this.guanli = guanli;
	}
	
	public String getBuildPlace() {
		return buildPlace;
	}

	public void setBuildPlace(String buildPlace) {
		this.buildPlace = buildPlace;
	}
	
	public String getBuildDetailPlace() {
		return buildDetailPlace;
	}

	public void setBuildDetailPlace(String buildDetailPlace) {
		this.buildDetailPlace = buildDetailPlace;
	}
	
	public String getScaleandinfo() {
		return scaleandinfo;
	}

	public void setScaleandinfo(String scaleandinfo) {
		this.scaleandinfo = scaleandinfo;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}
	
	public String getStatusFirst() {
		return statusFirst;
	}

	public void setStatusFirst(String statusFirst) {
		this.statusFirst = statusFirst;
	}

	public String getWenhaoFirst() {
		return wenhaoFirst;
	}

	public void setWenhaoFirst(String wenhaoFirst) {
		this.wenhaoFirst = wenhaoFirst;
	}

	public Date getRiqiFirst() {
		return riqiFirst;
	}

	public void setRiqiFirst(Date riqiFirst) {
		this.riqiFirst = riqiFirst;
	}

	public String getBumenFirst() {
		return bumenFirst;
	}

	public void setBumenFirst(String bumenFirst) {
		this.bumenFirst = bumenFirst;
	}

	public Date getCaozuodateFirst() {
		return caozuodateFirst;
	}

	public void setCaozuodateFirst(Date caozuodateFirst) {
		this.caozuodateFirst = caozuodateFirst;
	}
	
	
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getTransstreet() {
		return transstreet;
	}

	public void setTransstreet(String transstreet) {
		this.transstreet = transstreet;
	}

	public String getTransterrace() {
		return transterrace;
	}

	public void setTransterrace(String transterrace) {
		this.transterrace = transterrace;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public ProjectDeclare getProjectDeclare() {
		return projectDeclare;
	}

	public void setProjectDeclare(ProjectDeclare projectDeclare) {
		this.projectDeclare = projectDeclare;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getEndCode() {
		return endCode;
	}

	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getCaozuoDate() {
		return caozuoDate;
	}

	public void setCaozuoDate(String caozuoDate) {
		this.caozuoDate = caozuoDate;
	}
	
	
}