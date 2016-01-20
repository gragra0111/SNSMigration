package com.test.sns.dto.mongo;

import java.io.Serializable;

public class MongoBookmarklistsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1075836395122361535L;
	private String _id;
	private String articleId;
	private String bookmarkId;
	private String employeeId;
	private String loginId;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getBookmarkId() {
		return bookmarkId;
	}
	public void setBookmarkId(String bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
}
