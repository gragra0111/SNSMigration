package com.test.sns.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoGroupsDAO;
import com.test.sns.dao.oracle.OracleGroupDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlGroupDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoGroupsDTO;
import com.test.sns.dto.oracle.OracleGroupDTO;

@Service
public class GroupController {
	private final Logger logger = Logger.getLogger(GroupController.class);
	@Autowired
	private MongoGroupsDAO mongoGroupsDAO;
	@Autowired
	private OracleGroupDAO oracleGroupDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	/*private PostgresqlGroupDAO postgresqlGroupDAO;
	private PostgresqlUserDAO postgresqlUserDAO;*/
	
	public void setMongoGroupsDAO(MongoGroupsDAO mongoGroupsDAO) {
		this.mongoGroupsDAO = mongoGroupsDAO;
	}

	public void setOracleGroupDAO(OracleGroupDAO oracleGroupDAO) {
		this.oracleGroupDAO = oracleGroupDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	public void groupTableMigration() {
		//몽고 그룹 데이터 저장
		List<MongoGroupsDTO> list = this.mongoGroupsDAO.getGroup();
		
		for(MongoGroupsDTO data : list) {
			logger.info(data.getUser());
			OracleGroupDTO oracleGroupDTO = new OracleGroupDTO();
			//그룹ID 생성
			String grpId = oracleGroupDAO.setGroupId();
			//로그인ID로 유저ID 찾기
			String userId = oracleUserDAO.getUserIdByLgnId(data.getUser());
			if(userId != null) {
				oracleGroupDTO.setTemp_grp_id(data.get_id());
				oracleGroupDTO.setGrp_id(grpId);
				oracleGroupDTO.setGrp_nm(data.getName());
				oracleGroupDTO.setGrp_expn(data.getDesc());
				oracleGroupDTO.setCreate_id(userId);
				//인서트
				logger.info("grp_id : "+grpId+", user_id : "+userId);
				oracleGroupDAO.insert(oracleGroupDTO);
			}
		}
	}
}
