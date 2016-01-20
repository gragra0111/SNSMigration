package com.test.sns.dto.mongo;

import java.io.Serializable;
import java.util.ArrayList;

public class MongoMytagsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5741421358779389651L;
	private String _id;
	private String user;
	private String tagname;
	private String cnt;
	private ArrayList<Object> addList;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public ArrayList<Object> getAddList() {
		return addList;
	}
	public void setAddList(ArrayList<Object> addList) {
		this.addList = addList;
	}
	
}
