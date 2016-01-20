package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleUserLinkDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2041370900275310130L;
	private String user_id;
	private Integer seq;
	private String link_nm;
	private String link_url;
	private String create_dt;
	private String update_dt;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getLink_nm() {
		return link_nm;
	}
	public void setLink_nm(String link_nm) {
		this.link_nm = link_nm;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	
}
