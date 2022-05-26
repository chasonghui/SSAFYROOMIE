package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModelProperty;

public class Comment {
	@ApiModelProperty(value = "댓글번호pk")
	int cno;
	@ApiModelProperty(value="게시글번호")
	int ano;
	@ApiModelProperty(value = "댓글작성자")
	String cid;
	@ApiModelProperty(value = "게시글작성자")
	String bid;
	@ApiModelProperty(value = "댓글내용")
	String content;
	@ApiModelProperty(value = "비밀댓글여부")
	boolean issecret;
	@ApiModelProperty(value = "작성일")
	String regtime;
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isIssecret() {
		return issecret;
	}
	public void setIssecret(boolean issecret) {
		this.issecret = issecret;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "Comment [cno=" + cno + ", ano=" + ano + ", cid=" + cid + ", bid=" + bid + ", content=" + content
				+ ", issecret=" + issecret + ", regtime=" + regtime + "]";
	}
	
}
