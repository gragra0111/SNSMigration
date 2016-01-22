package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleHashtagDTO;

@Service
public class OracleHashtagDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String setHashtagId() {
		return this.jdbcTemplate.queryForObject("SELECT"
				+ " CASE TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(NVL(MAX(create_dt),SYSDATE-1),'YYYYMMDD'))"
				+ " WHEN 0 THEN 'T' || TO_CHAR(SYSDATE,'YYYYMMDD') || LPAD(TO_NUMBER(SUBSTR(MAX(hashtag_id),10))+1,9,'0')"
				+ " ELSE 'T' || TO_CHAR(SYSDATE,'YYYYMMDD') || '000000000' END AS hashtagId FROM temp_tb_hashtag"
				, String.class);
	}

	public void insert(OracleHashtagDTO oracleHashtagDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_hashtag (HASHTAG_ID, HASHTAG, USE_CNT, CREATE_DT, UPDATE_DT) VALUES (?, ?, ?, SYSTIMESTAMP, SYSTIMESTAMP)",
				oracleHashtagDTO.getHashtag_id(), oracleHashtagDTO.getHashtag(), oracleHashtagDTO.getUse_cnt());
	}

	public String getTagIdByTagNm(String tag) {
		try {
			return this.jdbcTemplate.queryForObject("SELECT hashtag_id FROM temp_tb_hashtag WHERE HASHTAG = ?", new Object[]{tag}, String.class);
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insert(String articleId, String hashtagId) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_article_hashtag (ARTICLE_ID, HASHTAG_ID) VALUES (?, ?)",
				articleId, hashtagId);
	}
}
