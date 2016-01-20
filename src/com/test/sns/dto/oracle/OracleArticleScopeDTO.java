package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleArticleScopeDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3858962768434341989L;
	
	private String article_id;
	private String share_scope;
	private String share_user_id;
	private String share_type;
	
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getShare_scope() {
		return share_scope;
	}
	public void setShare_scope(String share_scope) {
		this.share_scope = share_scope;
	}
	public String getShare_user_id() {
		return share_user_id;
	}
	public void setShare_user_id(String share_user_id) {
		this.share_user_id = share_user_id;
	}
	public String getShare_type() {
		return share_type;
	}
	public void setShare_type(String share_type) {
		this.share_type = share_type;
	}
	
}
