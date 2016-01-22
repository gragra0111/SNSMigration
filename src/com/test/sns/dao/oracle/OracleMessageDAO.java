package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleMessageDTO;

@Service
public class OracleMessageDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Boolean msgCheck(String tagname) {
		try {
			this.jdbcTemplate.queryForObject("SELECT msg_id FROM TEMP_TB_MESSAGE WHERE MSG=?", new Object[]{tagname}, String.class);
			return false;
		} catch(EmptyResultDataAccessException e) {
			return true;
		}
	}

	public String setMsgId() {
		return this.jdbcTemplate.queryForObject("SELECT CASE TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(NVL(MAX(create_dt),SYSDATE-1),'YYYYMMDD')) WHEN 0 THEN 'M' || TO_CHAR(SYSDATE,'YYYYMMDD') || LPAD(TO_NUMBER(SUBSTR(MAX(msg_id),10))+1,9,'0') ELSE 'M' || TO_CHAR(SYSDATE,'YYYYMMDD') || '000000000' END AS msgId FROM temp_tb_message", String.class);
	}

	public void insert(OracleMessageDTO oracleMessageDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_message (msg_id, msg, create_dt, temp_msg_id) VALUES (?, ?, SYSTIMESTAMP, ?)",
				oracleMessageDTO.getMsg_id(), oracleMessageDTO.getMsg(), oracleMessageDTO.getTemp_msg_id());
	}
	
	public String getMsgIdByMsg(String msg) {
		try {
			return this.jdbcTemplate.queryForObject("SELECT msg_id FROM TEMP_TB_MESSAGE WHERE msg = ?", new Object[]{msg}, String.class);
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
