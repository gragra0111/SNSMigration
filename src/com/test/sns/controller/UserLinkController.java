package com.test.sns.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoMylinksDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.oracle.OracleUserLinkDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserLinkDAO;
import com.test.sns.dto.mongo.MongoMylinksDTO;
import com.test.sns.dto.oracle.OracleUserLinkDTO;

public class UserLinkController {
	private MongoMylinksDAO mongoMylinksDAO;
	//private OracleUserDAO oracleUserDAO;
	//private OracleUserLinkDAO oracleUserLinkDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlUserLinkDAO postgresqlUserLinkDAO;
	
	public UserLinkController(ApplicationContext context) {
		this.mongoMylinksDAO = context.getBean("mongoMylinksDAO", MongoMylinksDAO.class);
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		this.postgresqlUserLinkDAO = context.getBean("postgresqlUserLinkDAO", PostgresqlUserLinkDAO.class);
		userLinkTableMigration();
	}
	
	public void userLinkTableMigration() {
		//몽고 데이터 받아오기
		List<MongoMylinksDTO> list = mongoMylinksDAO.getMyLinks();
		
		for(MongoMylinksDTO mongoData : list) {
			OracleUserLinkDTO oracleUserLinkDTO = new OracleUserLinkDTO();
			String empNo = mongoData.getUser().toString().substring(12, 17);
			String userId = postgresqlUserDAO.getUserIdByEmpNo(empNo);
			if(userId != null) {
				oracleUserLinkDTO.setUser_id(userId);
				oracleUserLinkDTO.setSeq(postgresqlUserLinkDAO.setSeq(userId));
				oracleUserLinkDTO.setLink_nm(mongoData.getTitle());
				oracleUserLinkDTO.setLink_url(mongoData.getUrl());
				//인서트
				System.out.println("userId : "+userId+", seq : "+oracleUserLinkDTO.getSeq()+", Link_nm : "+oracleUserLinkDTO.getLink_nm());
				postgresqlUserLinkDAO.insert(oracleUserLinkDTO);
			}
		}
	}
}
