package com.test.sns.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoBookmarksDAO;
import com.test.sns.dao.oracle.OracleBookmarkDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlBookmarkDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoBookmarksDTO;
import com.test.sns.dto.oracle.OracleBookmarkDTO;

public class BookmarkController {
	private MongoBookmarksDAO mongoBookmarksDAO;
	//private OracleBookmarkDAO oracleBookmarkDAO;
	//private OracleUserDAO oracleUserDAO;
	private PostgresqlBookmarkDAO postgresqlBookmarkDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	
	public BookmarkController(ApplicationContext context) {
		//몽고디비 북마크컬렉션
		this.mongoBookmarksDAO = context.getBean("mongoBookmarksDAO", MongoBookmarksDAO.class);
		//오라클 북마크테이블
		this.postgresqlBookmarkDAO = context.getBean("postgresqlBookmarkDAO", PostgresqlBookmarkDAO.class);
		//오라클 유저테이블
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		bookmarkTableMigration();
	}

	public void bookmarkTableMigration() {
		DateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		//몽고디비 북마크 데이터 저장
		List<MongoBookmarksDTO> list = mongoBookmarksDAO.getBookmarks();
		
		for(MongoBookmarksDTO mongoData : list) {
			//사번으로 유저ID 가져오기
			String userId = postgresqlUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
			String createDt = format.format(mongoData.getCreateDate()).toString();
			//System.out.println(create_dt);
			if(userId != null) {
				OracleBookmarkDTO oracleBookmarkDTO = new OracleBookmarkDTO();
				//북마크ID 생성
				oracleBookmarkDTO.setBkmk_id(postgresqlBookmarkDAO.setBkmkId(createDt));
				System.out.println(oracleBookmarkDTO.getBkmk_id());
				oracleBookmarkDTO.setBkmk_nm(mongoData.getBookmarkName());
				oracleBookmarkDTO.setCreate_id(userId);
				oracleBookmarkDTO.setCreate_dt(createDt);
				oracleBookmarkDTO.setTemp_bkmk_id(mongoData.get_id());
				System.out.println(oracleBookmarkDTO.getCreate_dt());
				//인서트
				postgresqlBookmarkDAO.insert(oracleBookmarkDTO);
			}
		}
	}
}
