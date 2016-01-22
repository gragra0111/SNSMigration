package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleUserDeptDTO;

@Service
public class OracleUserDeptDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(OracleUserDeptDTO oracleUserDeptDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_user_dept (user_id, dept_id) VALUES (?, ?)",
				oracleUserDeptDTO.getUser_id(), oracleUserDeptDTO.getDept_id());
	}
}