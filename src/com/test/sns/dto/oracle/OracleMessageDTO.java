package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleMessageDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6380107562923608820L;
	private String temp_msg_id;
	private String msg_id;
	private String msg;
	private String create_dt;
	
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
	}
	public String getTemp_msg_id() {
		return temp_msg_id;
	}
	public void setTemp_msg_id(String temp_msg_id) {
		this.temp_msg_id = temp_msg_id;
	}
	
}
