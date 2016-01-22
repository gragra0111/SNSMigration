package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleArticleDTO;

@Service
public class OracleArticleDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String setArticleId(String create_dt) {
		return this.jdbcTemplate.queryForObject("SELECT"
				+ " CASE TO_NUMBER(TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYYMMDD')) - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYYMMDD'))"
				+ " WHEN 0 THEN 'A' || TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYYMMDD') || LPAD(TO_NUMBER(SUBSTR(MAX(article_id),10))+1,9,'0')"
				+ " ELSE 'A' || TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYYMMDD') || '000000000' END AS articleId FROM temp_tb_article"
				, new Object[] {create_dt, create_dt, create_dt}
				, String.class);
	}

	public void insert(OracleArticleDTO oracleArticleDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_article (ARTICLE_ID, ARTICLE_TYPE, CTNT, CTNT_RAW, DEL_STAT, CREATE_ID, CREATE_DT, UPDATE_ID, UPDATE_DT, TEMP_ARTICLE_ID) VALUES (?, ?, ?, ?, ?, ?, TO_DATE(?,'YYMMDDHHMISS'), ?, TO_DATE(?,'YYMMDDHHMISS'), ?)",
				oracleArticleDTO.getArticle_id(), oracleArticleDTO.getArticle_type(), oracleArticleDTO.getCtnt(), oracleArticleDTO.getCtnt_raw(), oracleArticleDTO.getDel_stat(), oracleArticleDTO.getCreate_id(), oracleArticleDTO.getCreate_dt(), oracleArticleDTO.getUpdate_id(), oracleArticleDTO.getUpdate_dt(), oracleArticleDTO.getTemp_article_id());
	}

	public String getArticleIdByTempArticleId(String temp_article_id) {
		try {
			return this.jdbcTemplate.queryForObject("SELECT ARTICLE_ID FROM TEMP_TB_ARTICLE WHERE TEMP_ARTICLE_ID = ?", new Object[]{temp_article_id}, String.class);
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
