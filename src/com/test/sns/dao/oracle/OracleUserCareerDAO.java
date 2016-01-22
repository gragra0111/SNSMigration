package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleUserCareerDTO;

@Service
public class OracleUserCareerDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(OracleUserCareerDTO oracleUserCareerDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_user_career (USER_ID, SEQ, START_DD, END_DD, CTNT, CREATE_DT, UPDATE_DT) VALUES (?, ?, ?, ?, ?, ?, ?)",
				oracleUserCareerDTO.getUser_id(), oracleUserCareerDTO.getSeq(), oracleUserCareerDTO.getStart_dd(), oracleUserCareerDTO.getEnd_dd(), oracleUserCareerDTO.getCtnt(), oracleUserCareerDTO.getCreate_dt(), oracleUserCareerDTO.getUpdate_dt());
	}
}
