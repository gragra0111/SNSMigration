package com.test.sns.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoDeptsDAO;
import com.test.sns.dao.oracle.OracleDeptDAO;
import com.test.sns.dao.postgresql.PostgresqlDeptDAO;
import com.test.sns.dto.mongo.MongoDeptsDTO;
import com.test.sns.dto.oracle.OracleDeptDTO;

public class DeptController {
	private MongoDeptsDAO mongoDeptsDAO;
	//private OracleDeptDAO oracleDeptDAO;
	private PostgresqlDeptDAO postgresqlDeptDAO;
	
	public DeptController(ApplicationContext context) {
		this.mongoDeptsDAO = context.getBean("mongoDeptsDAO", MongoDeptsDAO.class);
		this.postgresqlDeptDAO = context.getBean("postgresqlDeptDAO", PostgresqlDeptDAO.class);
		deptTableMigration();
	}
	
	public void deptTableMigration() {
		//몽고 부서 데이터 저장
		List<MongoDeptsDTO> list = mongoDeptsDAO.getDepts();
		
		for(MongoDeptsDTO data : list) {
			if(!data.getDeptCode().equals("")) {
				OracleDeptDTO oracleDeptDTO = new OracleDeptDTO();
				oracleDeptDTO.setDept_id(data.getDeptCode());
				oracleDeptDTO.setParent_dept_id(data.getParentDeptCode());
				oracleDeptDTO.setDept_nm(data.getDeptName());
				oracleDeptDTO.setUse_yn("Y");
				//인서트
				System.out.println(oracleDeptDTO.getDept_id());
				postgresqlDeptDAO.insert(oracleDeptDTO);
			}
		}
	}
}
