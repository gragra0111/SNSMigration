package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleUserMessageDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2294479354220756544L;
	private String user_id;
	private String msg_id;
	private String create_id;
	private String create_dt;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
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
