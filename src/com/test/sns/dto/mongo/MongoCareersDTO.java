package com.test.sns.dto.mongo;

import java.io.Serializable;
import java.util.Date;

public class MongoCareersDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789552111829856214L;
	private String content;
	private Date createDate;
	private String period;
	private int sortnum;
	private Object user;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public int getSortnum() {
		return sortnum;
	}
	public void setSortnum(int sortnum) {
		this.sortnum = sortnum;
	}
	public Object getUser() {
		return user;
	}
	public void setUser(Object user) {
		this.user = user;
	}
}
