package com.test.sns.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoArticlesDAO;
import com.test.sns.dao.mongo.MongoTaglistsDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleHashtagDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlHashtagDAO;
import com.test.sns.dto.mongo.MongoArticlesDTO;
import com.test.sns.dto.mongo.MongoTaglistsDTO;
import com.test.sns.dto.oracle.OracleHashtagDTO;

public class HashtagController {
	private MongoTaglistsDAO mongoTaglistsDAO;
	//private OracleHashtagDAO oracleHashtagDAO;
	private MongoArticlesDAO mongoArticlesDAO;
	//private OracleArticleDAO oracleArticleDAO;
	private PostgresqlHashtagDAO postgresqlHashtagDAO;
	private PostgresqlArticleDAO postgresqlArticleDAO;
	
	public HashtagController(ApplicationContext context) {
		this.mongoTaglistsDAO = context.getBean("mongoTaglistsDAO", MongoTaglistsDAO.class);
		this.postgresqlHashtagDAO = context.getBean("postgresqlHashtagDAO", PostgresqlHashtagDAO.class);
		this.mongoArticlesDAO = context.getBean("mongoArticlesDAO", MongoArticlesDAO.class);
		this.postgresqlArticleDAO = context.getBean("postgresqlArticleDAO", PostgresqlArticleDAO.class);
		hashtagTableMigration();
		articleHashtagTableMigration();
	}

	private void hashtagTableMigration() {
		//몽고DB 태그 데이터 받기
		List<MongoTaglistsDTO> list = mongoTaglistsDAO.getTaglists();
		
		for(MongoTaglistsDTO mongoData : list) {
			OracleHashtagDTO oracleHashtagDTO = new OracleHashtagDTO();
			//해시태그ID 생성
			String hashtagId = postgresqlHashtagDAO.setHashtagId();
			oracleHashtagDTO.setHashtag_id(hashtagId);
			oracleHashtagDTO.setHashtag(mongoData.getTagname());
			oracleHashtagDTO.setUse_cnt(mongoData.getCnt());
			//인서트
			System.out.println("Hashtag_id : " + oracleHashtagDTO.getHashtag_id() + ", Hashtag : " + oracleHashtagDTO.getHashtag() + ", Use_cnt : " + oracleHashtagDTO.getUse_cnt());
			postgresqlHashtagDAO.insert(oracleHashtagDTO);
		}
	}
	
	private void articleHashtagTableMigration() {
		//몽고DB 아티클 데이터 받기
		List<MongoArticlesDTO> list = mongoArticlesDAO.getArticles();
		
		for(MongoArticlesDTO mongoData : list) {
			String articleId = postgresqlArticleDAO.getArticleIdByTempArticleId(mongoData.get_id());
			ArrayList<String> arrayList = mongoData.getTagList();
			for(String tag : arrayList) {
				System.out.println(tag);
				String hashtagId = postgresqlHashtagDAO.getTagIdByTagNm(tag);
				//인서트
				if(articleId != null && hashtagId != null) {
					System.out.println("articleId : " + articleId + ", hashtagId : " + hashtagId);
					postgresqlHashtagDAO.insert(articleId, hashtagId);
				}
			}
		}
	}

}
