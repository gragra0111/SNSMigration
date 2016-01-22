package com.test.sns.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoDeptsDAO;
import com.test.sns.dao.oracle.OracleDeptDAO;
import com.test.sns.dao.postgresql.PostgresqlDeptDAO;
import com.test.sns.dto.mongo.MongoDeptsDTO;
import com.test.sns.dto.oracle.OracleDeptDTO;

@Service
public class DeptController {
	private final Logger logger = Logger.getLogger(DeptController.class);
	@Autowired
	private MongoDeptsDAO mongoDeptsDAO;
	@Autowired
	private OracleDeptDAO oracleDeptDAO;
	//private PostgresqlDeptDAO postgresqlDeptDAO;
	
	public void setMongoDeptsDAO(MongoDeptsDAO mongoDeptsDAO) {
		this.mongoDeptsDAO = mongoDeptsDAO;
	}

	public void setOracleDeptDAO(OracleDeptDAO oracleDeptDAO) {
		this.oracleDeptDAO = oracleDeptDAO;
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
				logger.info("oracleDeptDTO.getDept_id() : " + oracleDeptDTO.getDept_id() + ", data.getDeptName() : " + data.getDeptName());
				oracleDeptDAO.insert(oracleDeptDTO);
			}
		}
	}
}
