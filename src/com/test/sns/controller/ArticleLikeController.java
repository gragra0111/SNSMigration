package com.test.sns.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoArticlesDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleArticleLikeDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleLikeDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoArticlesDTO;
import com.test.sns.dto.oracle.OracleArticleLikeDTO;

public class ArticleLikeController {
	private MongoArticlesDAO mongoArticlesDAO;
	//private OracleArticleDAO oracleArticleDAO;
	//private OracleUserDAO oracleUserDAO;
	//private OracleArticleLikeDAO oracleArticleLikeDAO;
	private PostgresqlArticleDAO postgresqlArticleDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlArticleLikeDAO postgresqlArticleLikeDAO;
	
	public ArticleLikeController(ApplicationContext context) {
		this.mongoArticlesDAO = context.getBean("mongoArticlesDAO", MongoArticlesDAO.class);
		this.postgresqlArticleLikeDAO = context.getBean("postgresqlArticleLikeDAO", PostgresqlArticleLikeDAO.class);
		this.postgresqlArticleDAO = context.getBean("postgresqlArticleDAO", PostgresqlArticleDAO.class);
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		articleLikeTableMigration();
	}

	@SuppressWarnings("unchecked")
	private void articleLikeTableMigration() {
		//몽고DB 태그 데이터 받기
		List<MongoArticlesDTO> list = mongoArticlesDAO.getArticles();
		
		for(MongoArticlesDTO mongoData : list) {
			OracleArticleLikeDTO oracleArticleLikeDTO = new OracleArticleLikeDTO();
			//아티클ID 찾기
			String articleId = postgresqlArticleDAO.getArticleIdByTempArticleId(mongoData.get_id());
			if(articleId != null) {
				ArrayList<Object> arrayList = mongoData.getLikeListPerson();
				for(Object data : arrayList) {
					LinkedHashMap<String,String> map = (LinkedHashMap<String, String>) data;
					Iterator<String> keyData = map.keySet().iterator();
					String k=null, empId=null;
					while(keyData.hasNext()) {
						k = keyData.next();
						if(k.equals("employeeId")) {
							empId = map.get(k);
						}
	                }
					//System.out.println(empId);
					if(empId != null) {
						//유저ID 찾기
						String userId = postgresqlUserDAO.getUserIdByEmpNo(empId);
						if(userId != null) {
							oracleArticleLikeDTO.setArticle_id(articleId);
							oracleArticleLikeDTO.setUser_id(userId);
							oracleArticleLikeDTO.setDel_stat("101");
							//인서트
							System.out.println("Article_id : " + oracleArticleLikeDTO.getArticle_id() + ", User_id : " + oracleArticleLikeDTO.getUser_id());
							postgresqlArticleLikeDAO.insert(oracleArticleLikeDTO);
						}
					}
				}
			}
		}
	}
	
}
