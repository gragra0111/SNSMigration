package com.test.sns.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoMytagsDAO;
import com.test.sns.dao.oracle.OracleMessageDAO;
import com.test.sns.dao.postgresql.PostgresqlMessageDAO;
import com.test.sns.dto.mongo.MongoMytagsDTO;
import com.test.sns.dto.oracle.OracleMessageDTO;

public class MessageController {
	private MongoMytagsDAO mongoMytagsDAO;
	//private OracleMessageDAO oracleMessageDAO;
	private PostgresqlMessageDAO postgresqlMessageDAO;
	
	public MessageController(ApplicationContext context) {
		//몽고디비 mytags컬렉션
		this.mongoMytagsDAO = context.getBean("mongoMytagsDAO", MongoMytagsDAO.class);
		//오라클 message테이블
		this.postgresqlMessageDAO = context.getBean("postgresqlMessageDAO", PostgresqlMessageDAO.class);
		messageTableMigration();
	}

	public void messageTableMigration() {
		//몽고디비 데이터 저장
		List<MongoMytagsDTO> list = mongoMytagsDAO.getMytags();
		
		for(MongoMytagsDTO mongoData : list) {
			//중복 msg 찾기
			Boolean check = postgresqlMessageDAO.msgCheck(mongoData.getTagname());
			//System.out.println(check);
			if(check) {
				OracleMessageDTO oracleMessageDTO = new OracleMessageDTO();
				//메시지ID 생성
				oracleMessageDTO.setMsg_id(postgresqlMessageDAO.setMsgId());
				System.out.println(oracleMessageDTO.getMsg_id());
				oracleMessageDTO.setMsg(mongoData.getTagname());
				oracleMessageDTO.setTemp_msg_id(mongoData.get_id());
				//인서트
				postgresqlMessageDAO.insert(oracleMessageDTO);
			}
		}
	}
}
