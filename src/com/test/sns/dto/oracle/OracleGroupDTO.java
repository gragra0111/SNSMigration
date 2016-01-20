package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleGroupDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5738936429667681549L;
	private String temp_grp_id;
	private String grp_id;
	private String grp_nm;
	private String grp_expn;
	private String create_id;
	private String create_dt;
	
	public String getTemp_grp_id() {
		return temp_grp_id;
	}
	public void setTemp_grp_id(String temp_grp_id) {
		this.temp_grp_id = temp_grp_id;
	}
	public String getGrp_id() {
		return grp_id;
	}
	public void setGrp_id(String grp_id) {
		this.grp_id = grp_id;
	}
	public String getGrp_nm() {
		return grp_nm;
	}
	public void setGrp_nm(String grp_nm) {
		this.grp_nm = grp_nm;
	}
	public String getGrp_expn() {
		return grp_expn;
	}
	public void setGrp_expn(String grp_expn) {
		this.grp_expn = grp_expn;
	}
	public String getCreate_id() {
		return create_id;
	}
	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	
}
