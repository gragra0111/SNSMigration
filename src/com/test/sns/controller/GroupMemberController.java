package com.test.sns.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoGrpMembersDAO;
import com.test.sns.dao.oracle.OracleGroupDAO;
import com.test.sns.dao.oracle.OracleGroupMemberDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlGroupDAO;
import com.test.sns.dao.postgresql.PostgresqlGroupMemberDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoGrpMembersDTO;
import com.test.sns.dto.oracle.OracleGroupDTO;
import com.test.sns.dto.oracle.OracleGroupMemberDTO;
import com.test.sns.dto.oracle.OracleUserDTO;

@Service
public class GroupMemberController {
	private final Logger logger = Logger.getLogger(GroupMemberController.class);
	@Autowired
	private MongoGrpMembersDAO mongoGrpMembersDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	@Autowired
	private OracleGroupDAO oracleGroupDAO;
	@Autowired
	private OracleGroupMemberDAO oracleGroupMemberDAO;
	/*private PostgresqlUserDAO postgresqlUserDAO;
	private PostgresqlGroupDAO postgresqlGroupDAO;
	private PostgresqlGroupMemberDAO postgresqlGroupMemberDAO;*/
	
	public void setMongoGrpMembersDAO(MongoGrpMembersDAO mongoGrpMembersDAO) {
		this.mongoGrpMembersDAO = mongoGrpMembersDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	public void setOracleGroupDAO(OracleGroupDAO oracleGroupDAO) {
		this.oracleGroupDAO = oracleGroupDAO;
	}

	public void setOracleGroupMemberDAO(OracleGroupMemberDAO oracleGroupMemberDAO) {
		this.oracleGroupMemberDAO = oracleGroupMemberDAO;
	}

	public void groupMemberTableMigration() {
		//몽고 그룹멤버 정보
		List<MongoGrpMembersDTO> monogoGrpMembersDto = this.mongoGrpMembersDAO.getGroupMembers();
		//오라클 그룹 정보
		List<OracleGroupDTO> oraGroupDto = oracleGroupDAO.getGroup();
		//오라클 유저 정보
		List<OracleUserDTO> oraUsers = oracleUserDAO.getUsers();
		
		for(MongoGrpMembersDTO mongoGrpMemberData : monogoGrpMembersDto) {
			OracleGroupMemberDTO oracleGroupMemberDTO = new OracleGroupMemberDTO();
			for(OracleGroupDTO oraGroupData : oraGroupDto) {
				if(mongoGrpMemberData.getGroupid().equals(oraGroupData.getTemp_grp_id())) {
					oracleGroupMemberDTO.setGrp_id(oraGroupData.getGrp_id());
					oracleGroupMemberDTO.setCreate_id(oraGroupData.getCreate_id());
				}
			}
			for(OracleUserDTO oraUsersData : oraUsers) {
				if(mongoGrpMemberData.getMemberid().equals(oraUsersData.getLgn_id())) {
					oracleGroupMemberDTO.setMbr_id(oraUsersData.getUser_id());
				}
			}
			oracleGroupMemberDTO.setMbr_type("101");
			//인서트
			if(oracleGroupMemberDTO.getGrp_id() != null && oracleGroupMemberDTO.getMbr_id() != null) {
				logger.info("setGrp_id : " + oracleGroupMemberDTO.getGrp_id() + ", setCreate_id : " + oracleGroupMemberDTO.getCreate_id() + " ,setMbr_type : " + oracleGroupMemberDTO.getMbr_type() +" ,setMbr_id : " + oracleGroupMemberDTO.getMbr_id());
				oracleGroupMemberDAO.insert(oracleGroupMemberDTO);
			}
		}
	}

}
