package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleArticleLikeDTO;

@Service
public class OracleArticleLikeDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(OracleArticleLikeDTO oracleArticleLikeDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_article_like (article_id, user_id, del_stat, create_dt) VALUES (?, ?, ?, SYSTIMESTAMP)",
				oracleArticleLikeDTO.getArticle_id(), oracleArticleLikeDTO.getUser_id(), oracleArticleLikeDTO.getDel_stat());
	}
}
