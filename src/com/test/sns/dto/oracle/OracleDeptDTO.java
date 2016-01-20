package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleDeptDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7176059889863397746L;
	private String dept_id;
	private String parent_dept_id;
	private String dept_nm;
	private String use_yn;
	
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getParent_dept_id() {
		return parent_dept_id;
	}
	public void setParent_dept_id(String parent_dept_id) {
		this.parent_dept_id = parent_dept_id;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	
}
