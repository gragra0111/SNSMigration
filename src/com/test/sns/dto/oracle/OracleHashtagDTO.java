package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleHashtagDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6793185913448437318L;
	private String hashtag_id;
	private String hashtag;
	private String use_cnt;
	private String create_dt;
	private String update_dt;
	
	public String getHashtag_id() {
		return hashtag_id;
	}
	public void setHashtag_id(String hashtag_id) {
		this.hashtag_id = hashtag_id;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getUse_cnt() {
		return use_cnt;
	}
	public void setUse_cnt(String use_cnt) {
		this.use_cnt = use_cnt;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_id(String create_dt) {
		this.create_dt = create_dt;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	
}
