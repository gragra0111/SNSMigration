package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleGroupMemberDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3000062697413422603L;
	private String grp_id;
	private String mbr_id;
	private String mbr_type;
	private String create_id;
	private String create_dt;
	
	public String getGrp_id() {
		return grp_id;
	}
	public void setGrp_id(String grp_id) {
		this.grp_id = grp_id;
	}
	public String getMbr_id() {
		return mbr_id;
	}
	public void setMbr_id(String mbr_id) {
		this.mbr_id = mbr_id;
	}
	public String getMbr_type() {
		return mbr_type;
	}
	public void setMbr_type(String mbr_type) {
		this.mbr_type = mbr_type;
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
