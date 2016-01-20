package com.test.sns.dto.mongo;

import java.io.Serializable;

public class MongoDeptsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7975483908196257402L;
	private String _id;
	private String deptCode;
	private String deptManager;
	private String deptName;
	private String parentDeptCode;
	private String parentDeptName;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptManager() {
		return deptManager;
	}
	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getParentDeptCode() {
		return parentDeptCode;
	}
	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}
	public String getParentDeptName() {
		return parentDeptName;
	}
	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}
	
}
