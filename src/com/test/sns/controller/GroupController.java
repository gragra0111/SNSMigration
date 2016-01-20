package com.test.sns.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoGroupsDAO;
import com.test.sns.dao.oracle.OracleGroupDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlGroupDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoGroupsDTO;
import com.test.sns.dto.oracle.OracleGroupDTO;

public class GroupController {
	private MongoGroupsDAO mongoGroupsDAO;
	//private OracleGroupDAO oracleGroupDAO;
	//private OracleUserDAO oracleUserDAO;
	private PostgresqlGroupDAO postgresqlGroupDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	
	public GroupController(ApplicationContext context) {
		this.mongoGroupsDAO = context.getBean("mongoGroupsDAO", MongoGroupsDAO.class);
		this.postgresqlGroupDAO = context.getBean("postgresqlGroupDAO", PostgresqlGroupDAO.class);
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		groupTableMigration();
	}
	
	public void groupTableMigration() {
		//몽고 그룹 데이터 저장
		List<MongoGroupsDTO> list = this.mongoGroupsDAO.getGroup();
		
		for(MongoGroupsDTO data : list) {
			OracleGroupDTO oracleGroupDTO = new OracleGroupDTO();
			//그룹ID 생성
			String grpId = postgresqlGroupDAO.setGroupId();
			//System.out.println(grpId);
			//로그인ID로 유저ID 찾기
			String userId = postgresqlUserDAO.getUserIdByLgnId(data.getUser());
			//System.out.println(userId);
			if(userId != null) {
				oracleGroupDTO.setTemp_grp_id(data.get_id());
				oracleGroupDTO.setGrp_id(grpId);
				oracleGroupDTO.setGrp_nm(data.getName());
				oracleGroupDTO.setGrp_expn(data.getDesc());
				oracleGroupDTO.setCreate_id(userId);
				//인서트
				System.out.println("grp_id : "+grpId+", user_id : "+userId);
				this.postgresqlGroupDAO.insert(oracleGroupDTO);
			}
		}
	}
}
