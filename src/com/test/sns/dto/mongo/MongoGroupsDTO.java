package com.test.sns.dto.mongo;

import java.io.Serializable;

public class MongoGroupsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7421177372907544669L;
	private String _id;
	private String user;
	private String desc;
	private String name;
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
