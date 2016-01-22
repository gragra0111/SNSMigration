package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleGroupMemberDTO;

@Service
public class OracleGroupMemberDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(OracleGroupMemberDTO oracleGroupMemberDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_group_member (grp_id, mbr_id, mbr_type, create_id, create_dt) VALUES (?, ?, ?, ?, SYSTIMESTAMP)",
				oracleGroupMemberDTO.getGrp_id(), oracleGroupMemberDTO.getMbr_id(), oracleGroupMemberDTO.getMbr_type(), oracleGroupMemberDTO.getCreate_id());
	}
}
