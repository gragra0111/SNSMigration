package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleUserDeptDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5135074292841478630L;
	private String user_id;
	private String dept_id;
	private String dty;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getDty() {
		return dty;
	}
	public void setDty(String dty) {
		this.dty = dty;
	}
	
}
