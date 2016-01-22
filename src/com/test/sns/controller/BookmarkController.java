package com.test.sns.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoBookmarksDAO;
import com.test.sns.dao.oracle.OracleBookmarkDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlBookmarkDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoBookmarksDTO;
import com.test.sns.dto.oracle.OracleBookmarkDTO;

@Service
public class BookmarkController {
	private final Logger logger = Logger.getLogger(BookmarkController.class);
	@Autowired
	private MongoBookmarksDAO mongoBookmarksDAO;
	@Autowired
	private OracleBookmarkDAO oracleBookmarkDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	/*private PostgresqlBookmarkDAO postgresqlBookmarkDAO;
	private PostgresqlUserDAO postgresqlUserDAO;*/
	
	public void setMongoBookmarksDAO(MongoBookmarksDAO mongoBookmarksDAO) {
		this.mongoBookmarksDAO = mongoBookmarksDAO;
	}

	public void setOracleBookmarkDAO(OracleBookmarkDAO oracleBookmarkDAO) {
		this.oracleBookmarkDAO = oracleBookmarkDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	public void bookmarkTableMigration() {
		DateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		//몽고디비 북마크 데이터 저장
		List<MongoBookmarksDTO> list = mongoBookmarksDAO.getBookmarks();
		
		for(MongoBookmarksDTO mongoData : list) {
			//사번으로 유저ID 가져오기
			String userId = oracleUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
			String createDt = format.format(mongoData.getCreateDate()).toString();
			//System.out.println(create_dt);
			if(userId != null) {
				OracleBookmarkDTO oracleBookmarkDTO = new OracleBookmarkDTO();
				//북마크ID 생성
				oracleBookmarkDTO.setBkmk_id(oracleBookmarkDAO.setBkmkId(createDt));
				oracleBookmarkDTO.setBkmk_nm(mongoData.getBookmarkName());
				oracleBookmarkDTO.setCreate_id(userId);
				oracleBookmarkDTO.setCreate_dt(createDt);
				oracleBookmarkDTO.setTemp_bkmk_id(mongoData.get_id());
				//인서트
				logger.info("oracleBookmarkDTO.getBkmk_id() : " + oracleBookmarkDTO.getBkmk_id() + "mongoData.getBookmarkName() : " + mongoData.getBookmarkName() +"oracleBookmarkDTO.getCreate_dt()" + oracleBookmarkDTO.getCreate_dt());
				oracleBookmarkDAO.insert(oracleBookmarkDTO);
			}
		}
	}
}
