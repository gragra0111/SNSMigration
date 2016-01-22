package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleUserLinkDTO;

@Service
public class OracleUserLinkDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer setSeq(String userId){
		String seq = this.jdbcTemplate.queryForObject("SELECT max(seq) as seq FROM temp_tb_user_link WHERE user_id=?", new Object[]{userId}, String.class);
		System.out.println(seq);
		if(seq != null) {
			return Integer.parseInt(seq) + 1;
		} else {
			return 1;
		}
	}

	public void insert(OracleUserLinkDTO oracleUserLinkDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_user_link (user_id, seq, link_nm, link_url, create_dt) VALUES (?, ?, ?, ?, SYSTIMESTAMP)",
				oracleUserLinkDTO.getUser_id(), oracleUserLinkDTO.getSeq(), oracleUserLinkDTO.getLink_nm(), oracleUserLinkDTO.getLink_url());
	}
}
