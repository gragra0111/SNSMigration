package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleArticleScopeDTO;

@Service
public class OracleArticleScopeDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(OracleArticleScopeDTO oracleArticleScopeDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_article_scope (ARTICLE_ID, SHARE_SCOPE, SHARE_USER_ID, SHARE_TYPE) VALUES (?, ?, ?, ?)",
				oracleArticleScopeDTO.getArticle_id(), oracleArticleScopeDTO.getShare_scope(), oracleArticleScopeDTO.getShare_user_id(), oracleArticleScopeDTO.getShare_type());
	}
}
