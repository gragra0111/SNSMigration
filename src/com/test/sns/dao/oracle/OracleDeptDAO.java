package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleDeptDTO;

@Service
public class OracleDeptDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(OracleDeptDTO oracleDeptDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_dept (dept_id, parent_dept_id, dept_nm, use_yn) VALUES (?, ?, ?, ?)",
				oracleDeptDTO.getDept_id(), oracleDeptDTO.getParent_dept_id(), oracleDeptDTO.getDept_nm(), oracleDeptDTO.getUse_yn());
	}
}