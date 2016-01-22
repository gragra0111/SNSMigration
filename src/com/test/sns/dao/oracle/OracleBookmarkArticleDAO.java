package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleBookmarkArticleDTO;

@Service
public class OracleBookmarkArticleDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(OracleBookmarkArticleDTO oracleBookmarkArticleDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_bookmark_article (bkmk_id, article_id, create_id, create_dt) VALUES (?, ?, ?, SYSTIMESTAMP)",
				oracleBookmarkArticleDTO.getBkmk_id(), oracleBookmarkArticleDTO.getArticle_id(), oracleBookmarkArticleDTO.getCreate_id());
	}
}
