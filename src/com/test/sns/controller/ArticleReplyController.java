package com.test.sns.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoRepliesDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleArticleReplyDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleReplyDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoRepliesDTO;
import com.test.sns.dto.oracle.OracleArticleReplyDTO;

public class ArticleReplyController {
	private MongoRepliesDAO mongoRepliesDAO;
	//private OracleArticleReplyDAO oracleArticleReplyDAO;
	//private OracleArticleDAO oracleArticleDAO;
	//private OracleUserDAO oracleUserDAO;
	private PostgresqlArticleReplyDAO postgresqlArticleReplyDAO;
	private PostgresqlArticleDAO postgresqlArticleDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	
	public ArticleReplyController(ApplicationContext context) {
		this.mongoRepliesDAO = context.getBean("mongoRepliesDAO", MongoRepliesDAO.class);
		this.postgresqlArticleReplyDAO = context.getBean("postgresqlArticleReplyDAO", PostgresqlArticleReplyDAO.class);
		this.postgresqlArticleDAO = context.getBean("postgresqlArticleDAO", PostgresqlArticleDAO.class);
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		articleReplyTableMigration();
	}


	private void articleReplyTableMigration() {
		DateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		//몽고DB 태그 데이터 받기
		List<MongoRepliesDTO> list = mongoRepliesDAO.getArticleReply();

		for(MongoRepliesDTO mongoData : list) {
			OracleArticleReplyDTO oracleArticleReplyDTO = new OracleArticleReplyDTO();
			String articleId = postgresqlArticleDAO.getArticleIdByTempArticleId(mongoData.getArticle_id());
			//System.out.println(articleId);
			if(articleId != null) { //아티클이 있을 경우에만
				//생성자ID 찾기
				String createId = postgresqlUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
				if(createId != null) { //생성자가 있을 경우에만
					//순번 생성
					String seq = String.valueOf(postgresqlArticleReplyDAO.setSeq(articleId));
					//생성일시
					String createDt = format.format(mongoData.getCreated());
					oracleArticleReplyDTO.setArticle_id(articleId);
					oracleArticleReplyDTO.setSeq(seq);
					oracleArticleReplyDTO.setCtnt(mongoData.getContent());
					oracleArticleReplyDTO.setDel_stat("101");
					oracleArticleReplyDTO.setCreate_id(createId);
					oracleArticleReplyDTO.setCreate_dt(createDt);
					//인서트
					System.out.println(mongoData.get_id());
					System.out.println("Article_id : " + oracleArticleReplyDTO.getArticle_id() + ", Seq : " + oracleArticleReplyDTO.getSeq() + ", Create_id : " + oracleArticleReplyDTO.getCreate_id() + ", Create_dt : " + oracleArticleReplyDTO.getCreate_dt());
					postgresqlArticleReplyDAO.insert(oracleArticleReplyDTO);
				}
			}
		}
		
	}
	
}
