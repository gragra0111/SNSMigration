package com.test.sns.dao.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleUserDTO;

@Service
public class OracleUserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String setUserId() {
		return this.jdbcTemplate.queryForObject("SELECT CASE TO_NUMBER(TO_CHAR(SYSDATE,'YYYY')) - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYY')) WHEN 0 THEN 'U' || TO_CHAR(SYSDATE,'YYYY') || LPAD(TO_NUMBER(SUBSTR(MAX(user_id),10))+1,9,'0') ELSE 'U' || TO_CHAR(SYSDATE,'YYYY') || '000000001' END AS userId FROM temp_tb_user", String.class);
	}
	
	public void insert(OracleUserDTO oracleUserDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_user (user_id, user_nm, lgn_id, emp_no, pst, mail, cp_no, offc_phon_no, use_yn, create_dt, temp_user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)",
				oracleUserDTO.getUser_id(), oracleUserDTO.getUser_nm(), oracleUserDTO.getLgn_id(), oracleUserDTO.getEmp_no(), oracleUserDTO.getPst(), oracleUserDTO.getMail(), oracleUserDTO.getCp_no(), oracleUserDTO.getOffc_phon_no(), oracleUserDTO.getUse_yn(), oracleUserDTO.getTemp_user_id());
	}

	public List<OracleUserDTO> getUsers() {
		return this.jdbcTemplate.query("SELECT * FROM temp_tb_user"
				, new RowMapper<OracleUserDTO>() {
					public OracleUserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						OracleUserDTO oracleUserDTO = new OracleUserDTO();
						oracleUserDTO.setUser_id(rs.getString("user_id"));
						oracleUserDTO.setUser_nm(rs.getString("user_nm"));
						oracleUserDTO.setLgn_id(rs.getString("lgn_id"));
						oracleUserDTO.setEmp_no(rs.getString("emp_no"));
						oracleUserDTO.setPst(rs.getString("pst"));
						oracleUserDTO.setRnk(rs.getString("rnk"));
						oracleUserDTO.setMail(rs.getString("mail"));
						oracleUserDTO.setCp_no(rs.getString("cp_no"));
						oracleUserDTO.setOffc_phon_no(rs.getString("offc_phon_no"));
						oracleUserDTO.setProfile_img(rs.getString("profile_img"));
						oracleUserDTO.setUse_yn(rs.getString("use_yn"));
						oracleUserDTO.setCreate_dt(rs.getString("create_dt"));
						return oracleUserDTO;
					}});
	}

	public String getUserIdByEmpNo(String empNo) {
		try {
			return this.jdbcTemplate.queryForObject("SELECT user_id FROM temp_tb_user where emp_no = ?", new Object[]{empNo}, String.class);
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getUserIdByLgnId(String user) {
		try {
			return this.jdbcTemplate.queryForObject("SELECT user_id FROM temp_tb_user where lgn_id = ?", new Object[]{user}, String.class);
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
