package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OracleArticleFileDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert() {
		this.jdbcTemplate.update("INSERT INTO temp_tb_article_file (article_id, file_id) SELECT article_id, file_id FROM temp_tb_article AA, temp_tb_file BB WHERE AA.TEMP_ARTICLE_ID = BB.TEMP_ARTICLE_ID");
	}
}
