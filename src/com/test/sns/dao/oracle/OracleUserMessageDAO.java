package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleUserMessageDTO;

@Service
public class OracleUserMessageDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(OracleUserMessageDTO oracleUserMessageDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_user_message (user_id, msg_id, create_id, create_dt) VALUES (?, ?, ?, SYSTIMESTAMP)",
				oracleUserMessageDTO.getUser_id(), oracleUserMessageDTO.getMsg_id(), oracleUserMessageDTO.getCreate_id());
	}
	
}
