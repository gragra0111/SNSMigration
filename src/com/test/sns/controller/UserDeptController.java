package com.test.sns.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoUsersDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.oracle.OracleUserDeptDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDeptDAO;
import com.test.sns.dto.mongo.MongoUsersDTO;
import com.test.sns.dto.oracle.OracleUserDeptDTO;

@Service
public class UserDeptController {
	private final Logger logger = Logger.getLogger(UserDeptController.class);
	@Autowired
	private MongoUsersDAO mongoUsersDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	@Autowired
	private OracleUserDeptDAO oracleUserDeptDAO;
	/*private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlUserDeptDAO postgresqlUserDeptDAO;*/
	
	public void setMongoUsersDAO(MongoUsersDAO mongoUsersDAO) {
		this.mongoUsersDAO = mongoUsersDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	public void setOracleUserDeptDAO(OracleUserDeptDAO oracleUserDeptDAO) {
		this.oracleUserDeptDAO = oracleUserDeptDAO;
	}

	public void userDeptTableMigration() {
		//몽고 유저테이블에서 정보가져오기
		List<MongoUsersDTO> list = this.mongoUsersDAO.getUser();
		
		for(MongoUsersDTO mongoData : list) {
			OracleUserDeptDTO oracleUserDeptDTO = new OracleUserDeptDTO();
			//유저ID 찾기
			String userId = oracleUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
			if(userId != null) {
				oracleUserDeptDTO.setUser_id(userId);
				oracleUserDeptDTO.setDept_id(mongoData.getDeptCode());
				//인서트
				logger.info("userId : " + userId + ", DeptCode : " + oracleUserDeptDTO.getDept_id());
				oracleUserDeptDAO.insert(oracleUserDeptDTO);
			}
		}
	}
	
}
