package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleUserCareerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3222974237694422525L;
	private String user_id;
	private String seq;
	private String start_dd;
	private String end_dd;
	private String ctnt;
	private String create_dt;
	private String update_dt;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStart_dd() {
		return start_dd;
	}
	public void setStart_dd(String start_dd) {
		this.start_dd = start_dd;
	}
	public String getEnd_dd() {
		return end_dd;
	}
	public void setEnd_dd(String end_dd) {
		this.end_dd = end_dd;
	}
	public String getCtnt() {
		return ctnt;
	}
	public void setCtnt(String ctnt) {
		this.ctnt = ctnt;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	
}
