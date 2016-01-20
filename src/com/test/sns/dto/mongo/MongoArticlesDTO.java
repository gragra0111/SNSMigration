package com.test.sns.dto.mongo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MongoArticlesDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8502498279106220938L;
	private String __v;
	private String _id;
	private ArrayList<Object> reply;
	private ArrayList<Object> likeListPerson;
	private String likeCount;
	private ArrayList<Object> shareKey;
	private ArrayList<Object> shareTo;
	private ArrayList<String> tagList;
	private ArrayList<Object> attFile;
	private Object attContent;
	private String articleType;
	private String content;
	private String edited;
	private Date created;
	private String groupCode;
	private String groupName;
	private String dutyName;
	private String titleName;
	private String email;
	private String deptCode;
	private String deptName;
	private String nickName;
	private String name;
	private String loginId;
	private String employeeId;
	private String isDeleted;
	
	public String get__v() {
		return __v;
	}
	public void set__v(String __v) {
		this.__v = __v;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	public void setAttContent(String attContent) {
		this.attContent = attContent;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEdited() {
		return edited;
	}
	public void setEdited(String edited) {
		this.edited = edited;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public ArrayList<Object> getReply() {
		return reply;
	}
	public void setReply(ArrayList<Object> reply) {
		this.reply = reply;
	}
	public ArrayList<Object> getLikeListPerson() {
		return likeListPerson;
	}
	public void setLikeListPerson(ArrayList<Object> likeListPerson) {
		this.likeListPerson = likeListPerson;
	}
	public ArrayList<Object> getShareKey() {
		return shareKey;
	}
	public void setShareKey(ArrayList<Object> shareKey) {
		this.shareKey = shareKey;
	}
	public ArrayList<Object> getShareTo() {
		return shareTo;
	}
	public void setShareTo(ArrayList<Object> shareTo) {
		this.shareTo = shareTo;
	}
	public ArrayList<String> getTagList() {
		return tagList;
	}
	public void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}
	public ArrayList<Object> getAttFile() {
		return attFile;
	}
	public void setAttFile(ArrayList<Object> attFile) {
		this.attFile = attFile;
	}
	public Object getAttContent() {
		return attContent;
	}
	public void setAttContent(Object attContent) {
		this.attContent = attContent;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
