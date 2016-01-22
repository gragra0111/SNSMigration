package com.test.sns.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoMytagsDAO;
import com.test.sns.dao.oracle.OracleMessageDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlMessageDAO;
import com.test.sns.dto.mongo.MongoMytagsDTO;
import com.test.sns.dto.oracle.OracleMessageDTO;

@Service
public class MessageController {
	private final Logger logger = Logger.getLogger(MessageController.class);
	@Autowired
	private MongoMytagsDAO mongoMytagsDAO;
	@Autowired
	private OracleMessageDAO oracleMessageDAO;
	//private PostgresqlMessageDAO postgresqlMessageDAO;
	
	public void setMongoMytagsDAO(MongoMytagsDAO mongoMytagsDAO) {
		this.mongoMytagsDAO = mongoMytagsDAO;
	}

	public void setOracleMessageDAO(OracleMessageDAO oracleMessageDAO) {
		this.oracleMessageDAO = oracleMessageDAO;
	}

	public void messageTableMigration() {
		//몽고디비 데이터 저장
		List<MongoMytagsDTO> list = mongoMytagsDAO.getMytags();
		
		for(MongoMytagsDTO mongoData : list) {
			//중복 msg 찾기
			Boolean check = oracleMessageDAO.msgCheck(mongoData.getTagname());
			if(check) {
				OracleMessageDTO oracleMessageDTO = new OracleMessageDTO();
				//메시지ID 생성
				oracleMessageDTO.setMsg_id(oracleMessageDAO.setMsgId());
				oracleMessageDTO.setMsg(mongoData.getTagname());
				oracleMessageDTO.setTemp_msg_id(mongoData.get_id());
				//인서트
				logger.info("oracleMessageDTO.getMsg_id() : " + oracleMessageDTO.getMsg_id());
				oracleMessageDAO.insert(oracleMessageDTO);
			}
		}
	}
}
