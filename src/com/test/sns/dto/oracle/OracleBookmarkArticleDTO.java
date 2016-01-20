package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleBookmarkArticleDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6634009906847066903L;
	private String bkmk_id;
	private String article_id;
	private String create_id;
	private String create_dt;
	
	public String getBkmk_id() {
		return bkmk_id;
	}
	public void setBkmk_id(String bkmk_id) {
		this.bkmk_id = bkmk_id;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
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
