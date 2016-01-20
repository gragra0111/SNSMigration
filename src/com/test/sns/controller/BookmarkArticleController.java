package com.test.sns.controller;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoBookmarklistsDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleBookmarkArticleDAO;
import com.test.sns.dao.oracle.OracleBookmarkDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlBookmarkArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlBookmarkDAO;
import com.test.sns.dto.mongo.MongoBookmarklistsDTO;
import com.test.sns.dto.oracle.OracleBookmarkArticleDTO;

public class BookmarkArticleController {
	private MongoBookmarklistsDAO mongoBookmarklistsDAO;
	//private OracleBookmarkArticleDAO oracleBookmarkArticleDAO;
	//private OracleArticleDAO oracleArticleDAO;
	//private OracleBookmarkDAO oracleBookmarkDAO;
	private PostgresqlBookmarkArticleDAO postgresqlBookmarkArticleDAO;
	private PostgresqlArticleDAO postgresqlArticleDAO;
	private PostgresqlBookmarkDAO postgresqlBookmarkDAO;
	
	public BookmarkArticleController(ApplicationContext context) {
		this.mongoBookmarklistsDAO = context.getBean("mongoBookmarklistsDAO", MongoBookmarklistsDAO.class);
		this.postgresqlBookmarkArticleDAO = context.getBean("postgresqlBookmarkArticleDAO", PostgresqlBookmarkArticleDAO.class);
		this.postgresqlArticleDAO = context.getBean("postgresqlArticleDAO", PostgresqlArticleDAO.class);
		this.postgresqlBookmarkDAO = context.getBean("postgresqlBookmarkDAO", PostgresqlBookmarkDAO.class);
		bookmarkArticleTableMigration();
	}

	private void bookmarkArticleTableMigration() {
		//몽고DB 태그 데이터 받기
		List<MongoBookmarklistsDTO> list = mongoBookmarklistsDAO.getBookmarkLists();
		
		int cnt = 0;
		for(MongoBookmarklistsDTO mongoData : list) {
			OracleBookmarkArticleDTO oracleBookmarkArticleDTO = new OracleBookmarkArticleDTO();
			//아티클ID 찾기
			String articleId = postgresqlArticleDAO.getArticleIdByTempArticleId(mongoData.getArticleId());
			//북마크ID, 유저ID 찾기
			Map<String, String> map = postgresqlBookmarkDAO.getBookmarkIdAndByTempBookmarkId(mongoData.getBookmarkId());
			if(articleId != null && map != null) {
				oracleBookmarkArticleDTO.setArticle_id(articleId);
				oracleBookmarkArticleDTO.setBkmk_id(map.get("bkmkId"));
				oracleBookmarkArticleDTO.setCreate_id(map.get("createId"));
				cnt++;
				System.out.println("cnt : " + cnt + ", articleId : " + articleId + ", bookmarkId : " + map.get("bkmkId") + ", Create_id : " + map.get("createId"));
				//인서트
				postgresqlBookmarkArticleDAO.insert(oracleBookmarkArticleDTO);
			}
		}
		
	}

}
