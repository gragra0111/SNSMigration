package com.test.sns.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoArticlesDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleArticleLikeDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleLikeDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoArticlesDTO;
import com.test.sns.dto.oracle.OracleArticleLikeDTO;

@Service
public class ArticleLikeController {
	private final Logger logger = Logger.getLogger(ArticleLikeController.class);
	@Autowired
	private MongoArticlesDAO mongoArticlesDAO;
	@Autowired
	private OracleArticleDAO oracleArticleDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	@Autowired
	private OracleArticleLikeDAO oracleArticleLikeDAO;
	/*private PostgresqlArticleDAO postgresqlArticleDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlArticleLikeDAO postgresqlArticleLikeDAO;*/
	
	public void setMongoArticlesDAO(MongoArticlesDAO mongoArticlesDAO) {
		this.mongoArticlesDAO = mongoArticlesDAO;
	}

	public void setOracleArticleDAO(OracleArticleDAO oracleArticleDAO) {
		this.oracleArticleDAO = oracleArticleDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	public void setOracleArticleLikeDAO(OracleArticleLikeDAO oracleArticleLikeDAO) {
		this.oracleArticleLikeDAO = oracleArticleLikeDAO;
	}
	
	@SuppressWarnings("unchecked")
	public void articleLikeTableMigration() {
		//몽고DB 태그 데이터 받기
		List<MongoArticlesDTO> list = mongoArticlesDAO.getArticles();
		
		for(MongoArticlesDTO mongoData : list) {
			OracleArticleLikeDTO oracleArticleLikeDTO = new OracleArticleLikeDTO();
			//아티클ID 찾기
			String articleId = oracleArticleDAO.getArticleIdByTempArticleId(mongoData.get_id());
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
						String userId = oracleUserDAO.getUserIdByEmpNo(empId);
						if(userId != null) {
							oracleArticleLikeDTO.setArticle_id(articleId);
							oracleArticleLikeDTO.setUser_id(userId);
							oracleArticleLikeDTO.setDel_stat("101");
							//인서트
							logger.info("Article_id : " + oracleArticleLikeDTO.getArticle_id() + ", User_id : " + oracleArticleLikeDTO.getUser_id());
							oracleArticleLikeDAO.insert(oracleArticleLikeDTO);
						}
					}
				}
			}
		}
	}

}
