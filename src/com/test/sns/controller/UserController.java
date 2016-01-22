package com.test.sns.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoUsersDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoUsersDTO;
import com.test.sns.dto.oracle.OracleUserDTO;

@Service
public class UserController {
	private final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private MongoUsersDAO mongoUsersDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	//private PostgresqlUserDAO postgresqlUserDAO;
	
	public void setMongoUsersDAO(MongoUsersDAO mongoUsersDAO) {
		this.mongoUsersDAO = mongoUsersDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}
	
	public void userTableMigration() {
		//몽고 유저 데이터 저장
		List<MongoUsersDTO> list = this.mongoUsersDAO.getUser();
		
		for(MongoUsersDTO data : list) {
			//유저ID 생성
			String userId = oracleUserDAO.setUserId();
			//String userId = postgresqlUserDAO.setUserId();
			logger.info("생성된 유저아이디 : " + userId);
			OracleUserDTO oracleUserDTO = new OracleUserDTO();
			oracleUserDTO.setTemp_user_id(data.get_id());
			oracleUserDTO.setUser_id(userId);
			oracleUserDTO.setUser_nm(data.getName());
			oracleUserDTO.setLgn_id(data.getLoginId());
			oracleUserDTO.setEmp_no(data.getEmployeeId());
			oracleUserDTO.setPst(data.getDutyName());
			oracleUserDTO.setMail(data.getEmail());
			oracleUserDTO.setCp_no(data.getCellPhoneNumber());
			oracleUserDTO.setOffc_phon_no(data.getOfficePhoneNumber());
			oracleUserDTO.setUse_yn("Y");
			//인서트
			logger.info("loginId : " + oracleUserDTO.getLgn_id());
			oracleUserDAO.insert(oracleUserDTO);
			//postgresqlUserDAO.insert(oracleUserDTO);
		}
	}
	
}