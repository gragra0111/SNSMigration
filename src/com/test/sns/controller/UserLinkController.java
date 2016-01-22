package com.test.sns.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoMylinksDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.oracle.OracleUserLinkDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserLinkDAO;
import com.test.sns.dto.mongo.MongoMylinksDTO;
import com.test.sns.dto.oracle.OracleUserLinkDTO;

@Service
public class UserLinkController {
	private final Logger logger = Logger.getLogger(UserLinkController.class);
	@Autowired
	private MongoMylinksDAO mongoMylinksDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	@Autowired
	private OracleUserLinkDAO oracleUserLinkDAO;
	/*private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlUserLinkDAO postgresqlUserLinkDAO;*/
	
	public void setMongoMylinksDAO(MongoMylinksDAO mongoMylinksDAO) {
		this.mongoMylinksDAO = mongoMylinksDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	public void setOracleUserLinkDAO(OracleUserLinkDAO oracleUserLinkDAO) {
		this.oracleUserLinkDAO = oracleUserLinkDAO;
	}

	public void userLinkTableMigration() {
		//몽고 데이터 받아오기
		List<MongoMylinksDTO> list = mongoMylinksDAO.getMyLinks();
		
		for(MongoMylinksDTO mongoData : list) {
			OracleUserLinkDTO oracleUserLinkDTO = new OracleUserLinkDTO();
			String empNo = mongoData.getUser().toString().substring(12, 17);
			String userId = oracleUserDAO.getUserIdByEmpNo(empNo);
			if(userId != null) {
				oracleUserLinkDTO.setUser_id(userId);
				oracleUserLinkDTO.setSeq(oracleUserLinkDAO.setSeq(userId));
				oracleUserLinkDTO.setLink_nm(mongoData.getTitle());
				oracleUserLinkDTO.setLink_url(mongoData.getUrl());
				//인서트
				logger.info("userId : "+userId+", seq : "+oracleUserLinkDTO.getSeq()+", Link_nm : "+oracleUserLinkDTO.getLink_nm());
				oracleUserLinkDAO.insert(oracleUserLinkDTO);
			}
		}
	}
}
