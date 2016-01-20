package com.test.sns.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoUsersDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.oracle.OracleUserDeptDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDeptDAO;
import com.test.sns.dto.mongo.MongoUsersDTO;
import com.test.sns.dto.oracle.OracleUserDeptDTO;

public class UserDeptController {
	private MongoUsersDAO mongoUsersDAO;
	//private OracleUserDAO oracleUserDAO;
	//private OracleUserDeptDAO oracleUserDeptDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlUserDeptDAO postgresqlUserDeptDAO;
	
	public UserDeptController(ApplicationContext context) {
		this.mongoUsersDAO = context.getBean("mongoUsersDAO", MongoUsersDAO.class);
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		this.postgresqlUserDeptDAO = context.getBean("postgresqlUserDeptDAO", PostgresqlUserDeptDAO.class);
		userDeptTableMigration();
	}
	
	public void userDeptTableMigration() {
		//몽고 유저테이블에서 정보가져오기
		List<MongoUsersDTO> list = this.mongoUsersDAO.getUser();
		
		for(MongoUsersDTO mongoData : list) {
			OracleUserDeptDTO oracleUserDeptDTO = new OracleUserDeptDTO();
			//유저ID 찾기
			String userId = postgresqlUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
			if(userId != null) {
				oracleUserDeptDTO.setUser_id(userId);
				oracleUserDeptDTO.setDept_id(mongoData.getDeptCode());
				//인서트
				System.out.println("userId : " + userId + ", DeptCode : " + oracleUserDeptDTO.getDept_id());
				postgresqlUserDeptDAO.insert(oracleUserDeptDTO);
			}
		}
	}
	
}
