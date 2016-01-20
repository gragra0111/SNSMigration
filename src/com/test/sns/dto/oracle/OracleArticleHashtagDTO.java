package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleArticleHashtagDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3556667139883497734L;
	private String article_id;
	private String hashtag_id;
	
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getHashtag_id() {
		return hashtag_id;
	}
	public void setHashtag_id(String hashtag_id) {
		this.hashtag_id = hashtag_id;
	}
	
}
