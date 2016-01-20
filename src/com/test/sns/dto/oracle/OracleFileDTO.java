package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleFileDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7603151639487252941L;
	private String file_id;
	private String path;
	private String physic_file_nm;
	private String logic_file_nm;
	private String ext;
	private String volume;
	private String create_id;
	private String create_dt;
	private String temp_article_id;
	
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPhysic_file_nm() {
		return physic_file_nm;
	}
	public void setPhysic_file_nm(String physic_file_nm) {
		this.physic_file_nm = physic_file_nm;
	}
	public String getLogic_file_nm() {
		return logic_file_nm;
	}
	public void setLogic_file_nm(String logic_file_nm) {
		this.logic_file_nm = logic_file_nm;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
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
	public String getTemp_article_id() {
		return temp_article_id;
	}
	public void setTemp_article_id(String temp_article_id) {
		this.temp_article_id = temp_article_id;
	}
	
}
