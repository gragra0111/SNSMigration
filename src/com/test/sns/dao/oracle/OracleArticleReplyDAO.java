package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleArticleReplyDTO;

@Service
public class OracleArticleReplyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer setSeq(String articleId){
		String seq = this.jdbcTemplate.queryForObject("SELECT max(seq) as seq FROM temp_tb_article_reply where article_id=?", new Object[]{articleId}, String.class);
		if(seq != null) {
			return Integer.parseInt(seq) + 1;
		} else {
			return 1;
		}
	}

	public void insert(OracleArticleReplyDTO oracleArticleReplyDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_article_reply (article_id, seq, ctnt, del_stat, create_id, create_dt) VALUES (?, ?, ?, ?, ?, ?)",
				oracleArticleReplyDTO.getArticle_id(), oracleArticleReplyDTO.getSeq(), oracleArticleReplyDTO.getCtnt(), oracleArticleReplyDTO.getDel_stat(), oracleArticleReplyDTO.getCreate_id(), oracleArticleReplyDTO.getCreate_dt());
	}
}
