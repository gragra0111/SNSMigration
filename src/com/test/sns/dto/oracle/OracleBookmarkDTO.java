package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleBookmarkDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9110381447586854900L;
	private String temp_bkmk_id;
	private String bkmk_id;
	private String bkmk_nm;
	private String create_id;
	private String create_dt;
	
	public String getBkmk_id() {
		return bkmk_id;
	}
	public void setBkmk_id(String bkmk_id) {
		this.bkmk_id = bkmk_id;
	}
	public String getBkmk_nm() {
		return bkmk_nm;
	}
	public void setBkmk_nm(String bkmk_nm) {
		this.bkmk_nm = bkmk_nm;
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
	public String getTemp_bkmk_id() {
		return temp_bkmk_id;
	}
	public void setTemp_bkmk_id(String temp_bkmk_id) {
		this.temp_bkmk_id = temp_bkmk_id;
	}
	
}
