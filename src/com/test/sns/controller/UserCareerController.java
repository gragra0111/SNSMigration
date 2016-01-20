package com.test.sns.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.mongo.MongoCareersDAO;
import com.test.sns.dao.oracle.OracleUserCareerDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlUserCareerDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoCareersDTO;
import com.test.sns.dto.oracle.OracleUserCareerDTO;

public class UserCareerController {
	private MongoCareersDAO mongoCareersDAO;
	//private OracleUserCareerDAO oracleUserCareerDAO;
	//private OracleUserDAO oracleUserDAO;
	private PostgresqlUserCareerDAO postgresqlUserCareerDAO;
	private PostgresqlUserDAO postgresqlUserDAO;
	
	public UserCareerController(ApplicationContext context) {
		this.mongoCareersDAO = context.getBean("mongoCareersDAO", MongoCareersDAO.class);
		this.postgresqlUserCareerDAO = context.getBean("postgresqlUserCareerDAO", PostgresqlUserCareerDAO.class);
		this.postgresqlUserDAO = context.getBean("postgresqlUserDAO", PostgresqlUserDAO.class);
		userCareerMigration();
	}

	@SuppressWarnings("unchecked")
	private void userCareerMigration() {
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
			String userId = postgresqlUserDAO.getUserIdByEmpNo(empId);
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
				System.out.println("userId : " + oracleUserCareerDTO.getUser_id() 
								+ ", startDd : " + oracleUserCareerDTO.getStart_dd()
								+ ", endDd : " + oracleUserCareerDTO.getEnd_dd()
								+ ", ctnt : " + oracleUserCareerDTO.getCtnt());
				postgresqlUserCareerDAO.insert(oracleUserCareerDTO);
			}
		}
	}

}
