package com.test.sns.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoCareersDAO;
import com.test.sns.dao.oracle.OracleUserCareerDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserCareerDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoCareersDTO;
import com.test.sns.dto.oracle.OracleUserCareerDTO;

@Service
public class UserCareerController {
	private final Logger logger = Logger.getLogger(UserCareerController.class);
	@Autowired
	private MongoCareersDAO mongoCareersDAO;
	@Autowired
	private OracleUserCareerDAO oracleUserCareerDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	/*private PostgresqlUserCareerDAO postgresqlUserCareerDAO;
	private PostgresqlUserDAO postgresqlUserDAO;*/
	
	public void setMongoCareersDAO(MongoCareersDAO mongoCareersDAO) {
		this.mongoCareersDAO = mongoCareersDAO;
	}

	public void setOracleUserCareerDAO(OracleUserCareerDAO oracleUserCareerDAO) {
		this.oracleUserCareerDAO = oracleUserCareerDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	@SuppressWarnings("unchecked")
	public void userCareerMigration() {
		DateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		//몽고 데이터 가져오기
		List<MongoCareersDTO> list = mongoCareersDAO.getCareers();
		
		for(MongoCareersDTO mongoData : list) {
			OracleUserCareerDTO oracleUserCareerDTO = new OracleUserCareerDTO();
			LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) mongoData.getUser();
			Iterator<String> keyData = map.keySet().iterator();
			String k = null, empId = null;
			while(keyData.hasNext()) {
				k = keyData.next();
				if(k.equals("employeeId")) {
					empId = (map.get(k));
				}
			}
			//유저ID 찾기
			String userId = oracleUserDAO.getUserIdByEmpNo(empId);
			if(userId != null) {
				String createDt = format.format(mongoData.getCreateDate());
				String period = mongoData.getPeriod().replaceAll(" ~", "");
				String startDd = period.substring(0,10).replaceAll("-", "");
				oracleUserCareerDTO.setUser_id(userId);
				oracleUserCareerDTO.setSeq(String.valueOf(mongoData.getSortnum()));
				oracleUserCareerDTO.setStart_dd(startDd);
				if(period.length() > 10) {
					oracleUserCareerDTO.setEnd_dd(period.substring(period.length()-10,period.length()).replaceAll("-", ""));
				}
				oracleUserCareerDTO.setCtnt(mongoData.getContent());
				oracleUserCareerDTO.setCreate_dt(createDt);
				oracleUserCareerDTO.setUpdate_dt(createDt);
				//인서트
				logger.info("userId : " + oracleUserCareerDTO.getUser_id() 
							+ ", startDd : " + oracleUserCareerDTO.getStart_dd()
							+ ", endDd : " + oracleUserCareerDTO.getEnd_dd()
							+ ", ctnt : " + oracleUserCareerDTO.getCtnt());
				oracleUserCareerDAO.insert(oracleUserCareerDTO);
			}
		}
	}

}
