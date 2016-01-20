package com.test.sns.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

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

public class ArticleScopeController {
	private MongoArticlesDAO mongoArticlesDAO;
	//private OracleArticleScopeDAO oracleArticleScopeDAO;
	//private OracleArticleDAO oracleArticleDAO;
	//private OracleUserDAO oracleUserDAO;
	//private OracleGroupDAO oracleGroupDAO;
	private PostgresqlArticleScopeDAO postgresqlArticleScopeDAO;
	private PostgresqlArticleDAO postgresqlArticleDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlGroupDAO postgresqlGroupDAO;
	
	public ArticleScopeController(ApplicationContext context) {
		this.mongoArticlesDAO = context.getBean("mongoArticlesDAO", MongoArticlesDAO.class);
		this.postgresqlArticleScopeDAO = context.getBean("postgresqlArticleScopeDAO", PostgresqlArticleScopeDAO.class);
		this.postgresqlArticleDAO = context.getBean("postgresqlArticleDAO", PostgresqlArticleDAO.class);
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		this.postgresqlGroupDAO = context.getBean("postgresqlGroupDAO", PostgresqlGroupDAO.class);
		articleScopeTableMigration();
	}

	@SuppressWarnings("unchecked")
	private void articleScopeTableMigration() {
		//몽고DB 아티클 데이터 받기
		List<MongoArticlesDTO> list = mongoArticlesDAO.getArticles();
		
		for(MongoArticlesDTO mongoData : list) {
			//아티클ID 찾기
			String articleId = postgresqlArticleDAO.getArticleIdByTempArticleId(mongoData.get_id());
			if(articleId != null) {
				String createUserId = postgresqlUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
				ArrayList<Object> shareTo = mongoData.getShareTo();
				System.out.println(shareTo.size());
				
				if(shareTo.size() == 0) {	//공유범위가 지정이 안되있는 경우
					OracleArticleScopeDTO oracleArticleScopeDTO = new OracleArticleScopeDTO();
					oracleArticleScopeDTO.setArticle_id(articleId);
					oracleArticleScopeDTO.setShare_scope("PUBLIC");
					oracleArticleScopeDTO.setShare_user_id("PUBLIC");
					oracleArticleScopeDTO.setShare_type("101");
					System.out.println("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
					postgresqlArticleScopeDAO.insert(oracleArticleScopeDTO);
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
							String shareId = postgresqlUserDAO.getUserIdByEmpNo(key);
							if(shareId != null) {
								oracleArticleScopeDTO.setArticle_id(articleId);
								oracleArticleScopeDTO.setShare_scope(shareId);
								oracleArticleScopeDTO.setShare_user_id(shareId);
								oracleArticleScopeDTO.setShare_type("102");
								System.out.println("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
								postgresqlArticleScopeDAO.insert(oracleArticleScopeDTO);
							}
						} else if(kind.equals("group")) {
							List<Map<String, String>> shareList = postgresqlGroupDAO.getUserIdByTempGroupId(key);
							oracleArticleScopeDTO.setArticle_id(articleId);
							oracleArticleScopeDTO.setShare_type("103");
							for(Map<String, String> data : shareList) {
								if(data.get("shareScope") != null) {
									oracleArticleScopeDTO.setShare_scope(data.get("shareScope"));
									oracleArticleScopeDTO.setShare_user_id(data.get("shareUserId"));
									System.out.println("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
									postgresqlArticleScopeDAO.insert(oracleArticleScopeDTO);
								}
							}
						}
					}
					//ADMIN 추가
					oracleArticleScopeDTO.setArticle_id(articleId);
					oracleArticleScopeDTO.setShare_scope("ADMIN");
					oracleArticleScopeDTO.setShare_user_id("ADMIN");
					oracleArticleScopeDTO.setShare_type("998");
					System.out.println("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + ", Share_type : " + oracleArticleScopeDTO.getShare_type());
					postgresqlArticleScopeDAO.insert(oracleArticleScopeDTO);
					//본인 추가
					oracleArticleScopeDTO.setArticle_id(articleId);
					oracleArticleScopeDTO.setShare_scope(createUserId);
					oracleArticleScopeDTO.setShare_user_id(createUserId);
					oracleArticleScopeDTO.setShare_type("999");
					System.out.println("Article_id : " + oracleArticleScopeDTO.getArticle_id() + ", Share_scope : " + oracleArticleScopeDTO.getShare_scope() + ", Share_user_id : " + oracleArticleScopeDTO.getShare_user_id() + "Share_type : " + oracleArticleScopeDTO.getShare_type());
					postgresqlArticleScopeDAO.insert(oracleArticleScopeDTO);
				}
			}
		}
	}
	
}
