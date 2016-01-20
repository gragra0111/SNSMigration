package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleArticleReplyDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3668995550269220456L;
	private String article_id;
	private String seq;
	private String ctnt;
	private String del_stat;
	private String create_id;
	private String create_dt;
	
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCtnt() {
		return ctnt;
	}
	public void setCtnt(String ctnt) {
		this.ctnt = ctnt;
	}
	public String getDel_stat() {
		return del_stat;
	}
	public void setDel_stat(String del_stat) {
		this.del_stat = del_stat;
	}
	public String getCreate_id() {
		return create_id;
	}
	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	
}
