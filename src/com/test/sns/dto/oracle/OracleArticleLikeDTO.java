package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleArticleLikeDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6543162722228556551L;
	private String article_id;
	private String user_id;
	private String del_stat;
	private String create_dt;
	
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDel_stat() {
		return del_stat;
	}
	public void setDel_stat(String del_stat) {
		this.del_stat = del_stat;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	
}
