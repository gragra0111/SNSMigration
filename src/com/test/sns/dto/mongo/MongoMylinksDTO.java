package com.test.sns.dto.mongo;

import java.io.Serializable;

public class MongoMylinksDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2387388671821856879L;
	private String _id;
	private String url;
	private String title;
	private String createDate;
	private Object user;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Object getUser() {
		return user;
	}
	public void setUser(Object user) {
		this.user = user;
	}
	
}
