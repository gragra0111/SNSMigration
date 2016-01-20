package com.test.sns.dto.mongo;

import java.io.Serializable;

public class MongoTaglistsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6249866581709205144L;
	private String _id;
	private String tagname;
	private String cnt;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
		
}
