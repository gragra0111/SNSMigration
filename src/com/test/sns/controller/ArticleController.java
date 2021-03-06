package com.test.sns.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoArticlesDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoArticlesDTO;
import com.test.sns.dto.oracle.OracleArticleDTO;

@Service
public class ArticleController {
	private final Logger logger = Logger.getLogger(ArticleController.class);
	@Autowired
	private MongoArticlesDAO mongoArticlesDAO;
	@Autowired
	private OracleArticleDAO oracleArticleDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	/*private PostgresqlArticleDAO postgresqlArticleDAO;
	private PostgresqlUserDAO postgresqlUserDAO;*/
	
	public void setMongoArticlesDAO(MongoArticlesDAO mongoArticlesDAO) {
		this.mongoArticlesDAO = mongoArticlesDAO;
	}

	public void setOracleArticleDAO(OracleArticleDAO oracleArticleDAO) {
		this.oracleArticleDAO = oracleArticleDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}
	
	@SuppressWarnings("unchecked")
	public void articleTableMigration() {
		DateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		//몽고DB 아티클 데이터 받기
		List<MongoArticlesDTO> list = mongoArticlesDAO.getArticles();
		
		for(MongoArticlesDTO mongoData : list) {
			//유저ID 찾기
			String userId = oracleUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
			if(userId != null) {
				OracleArticleDTO oracleArticleDTO = new OracleArticleDTO();
				oracleArticleDTO.setCreate_id(userId);
				oracleArticleDTO.setUpdate_id(userId);
				String createDt = format.format(mongoData.getCreated());
				oracleArticleDTO.setCreate_dt(createDt);
				oracleArticleDTO.setUpdate_dt(createDt);
				//아티클ID 생성
				String articleId = oracleArticleDAO.setArticleId(createDt);
				oracleArticleDTO.setArticle_id(articleId);
				oracleArticleDTO.setTemp_article_id(mongoData.get_id());
				//아티클 타입
				String articleType = mongoData.getArticleType();
				switch (articleType) {
					case "tabPost" : oracleArticleDTO.setArticle_type("101"); break;
					case "tabPhoto" : oracleArticleDTO.setArticle_type("102"); break;
					case "tabVideo" : oracleArticleDTO.setArticle_type("103"); break;
					case "tabFile" : oracleArticleDTO.setArticle_type("104"); break;
					case "tabLink" : oracleArticleDTO.setArticle_type("105"); break;
				}
				//아티클 원본 글 태그, 특수문자 제거
				String textWithoutTag = mongoData.getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
				String ctnt = textWithoutTag.replaceAll("&nbsp;", "");
				oracleArticleDTO.setCtnt(ctnt);
				//아티클 원본 글
				LinkedHashMap<String,String> map = (LinkedHashMap<String, String>) mongoData.getAttContent();
				Iterator<String> keyData = map.keySet().iterator();
				String k = null, v = null, ctntRaw = null;
				while(keyData.hasNext()) {
					k = keyData.next();
					if(k.equals("body")) {
						v = (map.get(k));
						if(v != null) {
							ctntRaw = mongoData.getContent() + " " + v;
						} else {
							ctntRaw = mongoData.getContent();
						}
					}
				}
				oracleArticleDTO.setCtnt_raw(ctntRaw);
				//삭제상태
				String delStat = mongoData.getIsDeleted();
				if(delStat == null || delStat.equals("false")) {
					oracleArticleDTO.setDel_stat("101");
				} else {
					oracleArticleDTO.setDel_stat("102");
				}
				
				//인서트
				logger.info("articleId : "+oracleArticleDTO.getArticle_id()+", articleType : "+oracleArticleDTO.getArticle_type()+", del_stat : "+oracleArticleDTO.getDel_stat());
				oracleArticleDAO.insert(oracleArticleDTO);
			}
		}
	}

}
