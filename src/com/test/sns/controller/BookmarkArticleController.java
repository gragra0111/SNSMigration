package com.test.sns.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoBookmarklistsDAO;
import com.test.sns.dao.oracle.OracleArticleDAO;
import com.test.sns.dao.oracle.OracleBookmarkArticleDAO;
import com.test.sns.dao.oracle.OracleBookmarkDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlBookmarkArticleDAO;
import com.test.sns.dao.postgresql.PostgresqlBookmarkDAO;
import com.test.sns.dto.mongo.MongoBookmarklistsDTO;
import com.test.sns.dto.oracle.OracleBookmarkArticleDTO;

@Service
public class BookmarkArticleController {
	private final Logger logger = Logger.getLogger(BookmarkArticleController.class);
	@Autowired
	private MongoBookmarklistsDAO mongoBookmarklistsDAO;
	@Autowired
	private OracleBookmarkArticleDAO oracleBookmarkArticleDAO;
	@Autowired
	private OracleArticleDAO oracleArticleDAO;
	@Autowired
	private OracleBookmarkDAO oracleBookmarkDAO;
	/*@Autowired
	private PostgresqlBookmarkArticleDAO postgresqlBookmarkArticleDAO;
	@Autowired
	private PostgresqlArticleDAO postgresqlArticleDAO;
	@Autowired
	private PostgresqlBookmarkDAO postgresqlBookmarkDAO;*/

	public void setMongoBookmarklistsDAO(MongoBookmarklistsDAO mongoBookmarklistsDAO) {
		this.mongoBookmarklistsDAO = mongoBookmarklistsDAO;
	}

	public void setOracleBookmarkArticleDAO(OracleBookmarkArticleDAO oracleBookmarkArticleDAO) {
		this.oracleBookmarkArticleDAO = oracleBookmarkArticleDAO;
	}

	public void setOracleArticleDAO(OracleArticleDAO oracleArticleDAO) {
		this.oracleArticleDAO = oracleArticleDAO;
	}

	public void setOracleBookmarkDAO(OracleBookmarkDAO oracleBookmarkDAO) {
		this.oracleBookmarkDAO = oracleBookmarkDAO;
	}

	public void bookmarkArticleTableMigration() {
		//몽고DB 태그 데이터 받기
		List<MongoBookmarklistsDTO> list = mongoBookmarklistsDAO.getBookmarkLists();
		
		int cnt = 0;
		for(MongoBookmarklistsDTO mongoData : list) {
			OracleBookmarkArticleDTO oracleBookmarkArticleDTO = new OracleBookmarkArticleDTO();
			//아티클ID 찾기
			String articleId = oracleArticleDAO.getArticleIdByTempArticleId(mongoData.getArticleId());
			//북마크ID, 유저ID 찾기
			Map<String, String> map = oracleBookmarkDAO.getBookmarkIdAndByTempBookmarkId(mongoData.getBookmarkId());
			if(articleId != null && map != null) {
				oracleBookmarkArticleDTO.setArticle_id(articleId);
				oracleBookmarkArticleDTO.setBkmk_id(map.get("bkmkId"));
				oracleBookmarkArticleDTO.setCreate_id(map.get("createId"));
				//인서트
				cnt++;
				logger.info("cnt : " + cnt + ", articleId : " + articleId + ", bookmarkId : " + map.get("bkmkId") + ", Create_id : " + map.get("createId"));
				oracleBookmarkArticleDAO.insert(oracleBookmarkArticleDTO);
			}
		}
		
	}

}
