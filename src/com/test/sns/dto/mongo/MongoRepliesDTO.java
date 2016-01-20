package com.test.sns.dto.mongo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MongoRepliesDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -363562297220358982L;
	private String _id;
	private String article_id;
	private String content;
	private String dutyName;
	private String titleName;
	private String email;
	private String deptCode;
	private String deptName;
	private String nickName;
	private String name;
	private String loginId;
	private String employeeId;
	private Date created;
	private ArrayList<Object> likeListPerson;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public ArrayList<Object> getLikeListPerson() {
		return likeListPerson;
	}
	public void setLikeListPerson(ArrayList<Object> likeListPerson) {
		this.likeListPerson = likeListPerson;
	}
	
}
