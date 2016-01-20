package com.test.sns.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoMytagsDAO;
import com.test.sns.dao.oracle.OracleMessageDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.oracle.OracleUserMessageDAO;
import com.test.sns.dao.postgresql.PostgresqlMessageDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserMessageDAO;
import com.test.sns.dto.mongo.MongoMytagsDTO;
import com.test.sns.dto.oracle.OracleUserMessageDTO;

public class UserMessageController {
	private MongoMytagsDAO mongoMytagsDAO;
	//private OracleUserMessageDAO oracleUserMessageDAO;
	//private OracleMessageDAO oracleMessageDAO;
	//private OracleUserDAO oracleUserDAO;
	private PostgresqlUserMessageDAO postgresqlUserMessageDAO;
	private PostgresqlMessageDAO postgresqlMessageDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	
	public UserMessageController(ApplicationContext context) {
		this.mongoMytagsDAO = context.getBean("mongoMytagsDAO", MongoMytagsDAO.class);
		this.postgresqlUserMessageDAO = context.getBean("postgresqlUserMessageDAO", PostgresqlUserMessageDAO.class);
		this.postgresqlMessageDAO = context.getBean("postgresqlMessageDAO", PostgresqlMessageDAO.class);
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		userMessageTableMigration();
	}

	private void userMessageTableMigration() {
		//몽고디비 데이터 저장
		List<MongoMytagsDTO> list = mongoMytagsDAO.getMytags();
		
		for(MongoMytagsDTO mongoData : list) {
			OracleUserMessageDTO oracleUserMessageDTO = new OracleUserMessageDTO();
			//메시지로 메시지ID 찾기
			String msgId = postgresqlMessageDAO.getMsgIdByMsg(mongoData.getTagname());
			//사번으로 유저ID 찾기
			String createId = postgresqlUserDAO.getUserIdByEmpNo(mongoData.getUser());
			//System.out.println(mongoData.get_id() +" : "+ msgId +" : "+ createId);
			if(createId != null) {
				for(Object data : mongoData.getAddList()) {
					String empNo = data.toString().substring(42, 47);
					//사번으로 유저ID 찾기
					String userId = postgresqlUserDAO.getUserIdByEmpNo(empNo);
					if(userId != null) {
						oracleUserMessageDTO.setUser_id(userId);
						oracleUserMessageDTO.setMsg_id(msgId);
						oracleUserMessageDTO.setCreate_id(createId);
						//인서트
						postgresqlUserMessageDAO.insert(oracleUserMessageDTO);
					}
				}
			}
		}
	}

	
}
