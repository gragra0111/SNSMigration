package com.test.sns.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoArticlesDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleArticleScopeDAO;
import com.test.sns.dao.oracle.OracleGroupDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleScopeDAO;
import com.test.sns.dao.postgresql.PostgresqlGroupDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoArticlesDTO;
import com.test.sns.dto.oracle.OracleArticleScopeDTO;

@Service
public class ArticleScopeController {
	private final Logger logger = Logger.getLogger(ArticleScopeController.class);
	@Autowired
	private MongoArticlesDAO mongoArticlesDAO;
	@Autowired
	private OracleArticleScopeDAO oracleArticleScopeDAO;
	@Autowired
	private OracleArticleDAO oracleArticleDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	@Autowired
	private OracleGroupDAO oracleGroupDAO;
	/*private PostgresqlArticleScopeDAO postgresqlArticleScopeDAO;
	private PostgresqlArticleDAO postgresqlArticleDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlGroupDAO postgresqlGroupDAO;*/
	
	public void setMongoArticlesDAO(MongoArticlesDAO mongoArticlesDAO) {
		this.mongoArticlesDAO = mongoArticlesDAO;
	}

	public void setOracleArticleScopeDAO(OracleArticleScopeDAO oracleArticleScopeDAO) {
		this.oracleArticleScopeDAO = oracleArticleScopeDAO;
	}

	public void setOracleArticleDAO(OracleArticleDAO oracleArticleDAO) {
		this.oracleArticleDAO = oracleArticleDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	public void setOracleGroupDAO(OracleGroupDAO oracleGroupDAO) {
		this.oracleGroupDAO = oracleGroupDAO;
	}
	
	@SuppressWarnings("unchecked")
	public void articleScopeTableMigration() {
		//몽고DB 아티클 데이터 받기
		List<MongoArticlesDTO> list = mongoArticlesDAO.getArticles();
		
		for(MongoArticlesDTO mongoData : list) {
			//아티클ID 찾기
			String articleId = oracleArticleDAO.getArticleIdByTempArticleId(mongoData.get_id());
			if(articleId != null) {
				String createUserId = oracleUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
				ArrayList<Object> shareTo = mongoData.getShareTo();
				System.out.println(shareTo.size());
				
				if(shareTo.size() == 0) {	//공유범위가 지정이 안되있는 경우
					OracleArticleScopeDTO oracleArticleScopeDTO = new OracleArticleScopeDTO();
					oracleArticleScopeDTO.setArticle_id(articleId);
					oracleArticleScopeDTO.setShare_scope("PUBLIC");
					oracleArticleScopeDTO.setShare_user_id("PUBLIC");
					oracleArticleScopeDTO.setShare_type("101");
					logger.info("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
					oracleArticleScopeDAO.insert(oracleArticleScopeDTO);
				} else {	//공유범위가 지정이 되있는 경우
					OracleArticleScopeDTO oracleArticleScopeDTO = new OracleArticleScopeDTO();
					for(int i=0; i<shareTo.size(); i++) {
						LinkedHashMap<String,String> map = (LinkedHashMap<String, String>) shareTo.get(i);
						Iterator<String> keyData = map.keySet().iterator();
						String k=null, key=null, kind=null;
						while(keyData.hasNext()) {
							k = keyData.next();
							if(k.equals("key")) {
								key = map.get(k);
							} else if(k.equals("kind")) {
								kind = map.get(k);
							}
		                }
						if(kind.equals("user")) {
							String shareId = oracleUserDAO.getUserIdByEmpNo(key);
							if(shareId != null) {
								oracleArticleScopeDTO.setArticle_id(articleId);
								oracleArticleScopeDTO.setShare_scope(shareId);
								oracleArticleScopeDTO.setShare_user_id(shareId);
								oracleArticleScopeDTO.setShare_type("102");
								logger.info("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
								oracleArticleScopeDAO.insert(oracleArticleScopeDTO);
							}
						} else if(kind.equals("group")) {
							List<Map<String, String>> shareList = oracleGroupDAO.getUserIdByTempGroupId(key);
							oracleArticleScopeDTO.setArticle_id(articleId);
							oracleArticleScopeDTO.setShare_type("103");
							for(Map<String, String> data : shareList) {
								if(data.get("shareScope") != null) {
									oracleArticleScopeDTO.setShare_scope(data.get("shareScope"));
									oracleArticleScopeDTO.setShare_user_id(data.get("shareUserId"));
									logger.info("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
									oracleArticleScopeDAO.insert(oracleArticleScopeDTO);
								}
							}
						}
					}
					//ADMIN 추가
					oracleArticleScopeDTO.setArticle_id(articleId);
					oracleArticleScopeDTO.setShare_scope("ADMIN");
					oracleArticleScopeDTO.setShare_user_id("ADMIN");
					oracleArticleScopeDTO.setShare_type("998");
					logger.info("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
					oracleArticleScopeDAO.insert(oracleArticleScopeDTO);
					//본인 추가
					oracleArticleScopeDTO.setArticle_id(articleId);
					oracleArticleScopeDTO.setShare_scope(createUserId);
					oracleArticleScopeDTO.setShare_user_id(createUserId);
					oracleArticleScopeDTO.setShare_type("999");
					logger.info("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
					oracleArticleScopeDAO.insert(oracleArticleScopeDTO);
				}
			}
		}
	}
	
}
