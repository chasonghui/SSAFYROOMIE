package com.ssafy.vue.dto;

public class InterestDto {
	private String userid;
	private long aptCode;
	private String aptName;
	private String dongName;
	private String sidoName;
	private String gugunName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String recentPrice;
	
	public InterestDto() {
	}
	public InterestDto(String userid, long aptCode) {
		this.userid = userid;
		this.aptCode = aptCode;
	}
	public InterestDto(String userid, long aptCode, String aptName, String dongName, String sidoName, String gugunName,
			int buildYear, String jibun, String lat, String lng, String recentPrice) {
		this.userid = userid;
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.dongName = dongName;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.recentPrice = recentPrice;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}
	@Override
	public String toString() {
		return "InterestDto [userid=" + userid + ", aptCode=" + aptCode + ", aptName=" + aptName + ", dongName="
				+ dongName + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", buildYear=" + buildYear
				+ ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", recentPrice=" + recentPrice + "]";
	}
	
}
