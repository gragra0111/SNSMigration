package com.test.sns.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoArticlesDAO;
import com.test.sns.dao.mongo.MongoTaglistsDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleHashtagDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlHashtagDAO;
import com.test.sns.dto.mongo.MongoArticlesDTO;
import com.test.sns.dto.mongo.MongoTaglistsDTO;
import com.test.sns.dto.oracle.OracleHashtagDTO;

@Service
public class HashtagController {
	private final Logger logger = Logger.getLogger(HashtagController.class);
	@Autowired
	private MongoTaglistsDAO mongoTaglistsDAO;
	@Autowired
	private MongoArticlesDAO mongoArticlesDAO;
	@Autowired
	private OracleHashtagDAO oracleHashtagDAO;
	@Autowired
	private OracleArticleDAO oracleArticleDAO;
	/*private PostgresqlHashtagDAO postgresqlHashtagDAO;
	private PostgresqlArticleDAO postgresqlArticleDAO;*/
	
	public void setMongoTaglistsDAO(MongoTaglistsDAO mongoTaglistsDAO) {
		this.mongoTaglistsDAO = mongoTaglistsDAO;
	}

	public void setMongoArticlesDAO(MongoArticlesDAO mongoArticlesDAO) {
		this.mongoArticlesDAO = mongoArticlesDAO;
	}

	public void setOracleHashtagDAO(OracleHashtagDAO oracleHashtagDAO) {
		this.oracleHashtagDAO = oracleHashtagDAO;
	}

	public void setOracleArticleDAO(OracleArticleDAO oracleArticleDAO) {
		this.oracleArticleDAO = oracleArticleDAO;
	}

	public void hashtagTableMigration() {
		//몽고DB 태그 데이터 받기
		List<MongoTaglistsDTO> list = mongoTaglistsDAO.getTaglists();
		
		for(MongoTaglistsDTO mongoData : list) {
			OracleHashtagDTO oracleHashtagDTO = new OracleHashtagDTO();
			//해시태그ID 생성
			String hashtagId = oracleHashtagDAO.setHashtagId();
			oracleHashtagDTO.setHashtag_id(hashtagId);
			oracleHashtagDTO.setHashtag(mongoData.getTagname());
			oracleHashtagDTO.setUse_cnt(mongoData.getCnt());
			//인서트
			logger.info("Hashtag_id : " + oracleHashtagDTO.getHashtag_id() + ", Hashtag : " + oracleHashtagDTO.getHashtag() + ", Use_cnt : " + oracleHashtagDTO.getUse_cnt());
			oracleHashtagDAO.insert(oracleHashtagDTO);
		}
	}
	
	public void articleHashtagTableMigration() {
		//몽고DB 아티클 데이터 받기
		List<MongoArticlesDTO> list = mongoArticlesDAO.getArticles();
		
		for(MongoArticlesDTO mongoData : list) {
			String articleId = oracleArticleDAO.getArticleIdByTempArticleId(mongoData.get_id());
			ArrayList<String> arrayList = mongoData.getTagList();
			for(String tag : arrayList) {
				System.out.println(tag);
				String hashtagId = oracleHashtagDAO.getTagIdByTagNm(tag);
				//인서트
				if(articleId != null && hashtagId != null) {
					logger.info("articleId : " + articleId + ", hashtagId : " + hashtagId);
					oracleHashtagDAO.insert(articleId, hashtagId);
				}
			}
		}
	}

}
