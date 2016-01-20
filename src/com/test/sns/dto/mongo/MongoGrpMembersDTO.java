package com.test.sns.dto.mongo;

import java.io.Serializable;

public class MongoGrpMembersDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1740453370347626141L;
	private String _id;
	private String userid;
	private String groupid;
	private String memberid;
	private String membername;
	private String memberdutyName;
	private String memberdeptName;
	private String memberempno;
	private String created;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getMemberdutyName() {
		return memberdutyName;
	}
	public void setMemberdutyName(String memberdutyName) {
		this.memberdutyName = memberdutyName;
	}
	public String getMemberdeptName() {
		return memberdeptName;
	}
	public void setMemberdeptName(String memberdeptName) {
		this.memberdeptName = memberdeptName;
	}
	public String getMemberempno() {
		return memberempno;
	}
	public void setMemberempno(String memberempno) {
		this.memberempno = memberempno;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
}
