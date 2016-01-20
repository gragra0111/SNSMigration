package com.test.sns.dto.oracle;

import java.io.Serializable;

public class OracleUserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7411638874132116114L;
	private String temp_user_id;
	private String user_id;
	private String user_nm;
	private String lgn_id;
	private String nation;
	private String emp_no;
	private String pst;
	private String rnk;
	private String mail;
	private String cp_no;
	private String offc_phon_no;
	private String profile_img;
	private String use_yn;
	private String create_id;
	private String create_dt;
	
	
	public String getTemp_user_id() {
		return temp_user_id;
	}
	public void setTemp_user_id(String temp_user_id) {
		this.temp_user_id = temp_user_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getLgn_id() {
		return lgn_id;
	}
	public void setLgn_id(String lgn_id) {
		this.lgn_id = lgn_id;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getPst() {
		return pst;
	}
	public void setPst(String pst) {
		this.pst = pst;
	}
	public String getRnk() {
		return rnk;
	}
	public void setRnk(String rnk) {
		this.rnk = rnk;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCp_no() {
		return cp_no;
	}
	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}
	public String getOffc_phon_no() {
		return offc_phon_no;
	}
	public void setOffc_phon_no(String offc_phon_no) {
		this.offc_phon_no = offc_phon_no;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
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
