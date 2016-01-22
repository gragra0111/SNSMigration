package com.test.sns.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoMytagsDAO;
import com.test.sns.dao.oracle.OracleMessageDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.oracle.OracleUserMessageDAO;
import com.test.sns.dao.postgresql.PostgresqlMessageDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserMessageDAO;
import com.test.sns.dto.mongo.MongoMytagsDTO;
import com.test.sns.dto.oracle.OracleUserMessageDTO;

@Service
public class UserMessageController {
	private final Logger logger = Logger.getLogger(UserMessageController.class);
	@Autowired
	private MongoMytagsDAO mongoMytagsDAO;
	@Autowired
	private OracleUserMessageDAO oracleUserMessageDAO;
	@Autowired
	private OracleMessageDAO oracleMessageDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	/*@Autowired
	private PostgresqlUserMessageDAO postgresqlUserMessageDAO;
	@Autowired
	private PostgresqlMessageDAO postgresqlMessageDAO;
	@Autowired
	private PostgresqlUserDAO postgresqlUserDAO;*/
	
	
	public void setMongoMytagsDAO(MongoMytagsDAO mongoMytagsDAO) {
		this.mongoMytagsDAO = mongoMytagsDAO;
	}

	public void setOracleUserMessageDAO(OracleUserMessageDAO oracleUserMessageDAO) {
		this.oracleUserMessageDAO = oracleUserMessageDAO;
	}

	public void setOracleMessageDAO(OracleMessageDAO oracleMessageDAO) {
		this.oracleMessageDAO = oracleMessageDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	public void userMessageTableMigration() {
		//몽고디비 데이터 저장
		List<MongoMytagsDTO> list = mongoMytagsDAO.getMytags();
		
		for(MongoMytagsDTO mongoData : list) {
			OracleUserMessageDTO oracleUserMessageDTO = new OracleUserMessageDTO();
			//메시지로 메시지ID 찾기
			String msgId = oracleMessageDAO.getMsgIdByMsg(mongoData.getTagname());
			//사번으로 유저ID 찾기
			String createId = oracleUserDAO.getUserIdByEmpNo(mongoData.getUser());
			//System.out.println(mongoData.get_id() +" : "+ msgId +" : "+ createId);
			if(createId != null) {
				for(Object data : mongoData.getAddList()) {
					String empNo = data.toString().substring(42, 47);
					//사번으로 유저ID 찾기
					String userId = oracleUserDAO.getUserIdByEmpNo(empNo);
					if(userId != null) {
						oracleUserMessageDTO.setUser_id(userId);
						oracleUserMessageDTO.setMsg_id(msgId);
						oracleUserMessageDTO.setCreate_id(createId);
						//인서트
						logger.info("userId : " + userId + ", msgId : " + msgId + ", createId : " + createId);
						oracleUserMessageDAO.insert(oracleUserMessageDTO);
					}
				}
			}
		}
	}

	
}
