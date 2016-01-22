package com.test.sns.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.mongo.MongoArticlesDAO;
import com.test.sns.dao.oracle.OracleFileDAO;
import com.test.sns.dao.oracle.OracleUserDAO;
import com.test.sns.dao.postgresql.PostgresqlFileDAO;
import com.test.sns.dao.postgresql.PostgresqlUserDAO;
import com.test.sns.dto.mongo.MongoArticlesDTO;
import com.test.sns.dto.oracle.OracleFileDTO;

@Service
public class FileController {
	private final Logger logger = Logger.getLogger(FileController.class);
	@Autowired
	private MongoArticlesDAO mongoArticlesDAO;
	@Autowired
	private OracleFileDAO oracleFileDAO;
	@Autowired
	private OracleUserDAO oracleUserDAO;
	/*private PostgresqlFileDAO postgresqlFileDAO;
	private PostgresqlUserDAO postgresqlUserDAO;*/
	
	public void setMongoArticlesDAO(MongoArticlesDAO mongoArticlesDAO) {
		this.mongoArticlesDAO = mongoArticlesDAO;
	}

	public void setOracleFileDAO(OracleFileDAO oracleFileDAO) {
		this.oracleFileDAO = oracleFileDAO;
	}

	public void setOracleUserDAO(OracleUserDAO oracleUserDAO) {
		this.oracleUserDAO = oracleUserDAO;
	}

	@SuppressWarnings("unchecked")
	public void fileTableMigration() {
		DateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		//몽고DB 아티클 데이터 받기
		List<MongoArticlesDTO> list = mongoArticlesDAO.getArticles();
		
		for(MongoArticlesDTO mongoData : list) {
			if(mongoData.getArticleType().equals("tabFile") || mongoData.getArticleType().equals("tabPhoto")) {
				OracleFileDTO oracleFileDTO = new OracleFileDTO();
				String createDt = format.format(mongoData.getCreated());
				//유저ID 찾기
				String userId = oracleUserDAO.getUserIdByEmpNo(mongoData.getEmployeeId());
				if(userId != null) {
					oracleFileDTO.setTemp_article_id(mongoData.get_id());
					//System.out.println(mongoData.get_id());
					oracleFileDTO.setCreate_id(userId);
					//System.out.println(user_id);
					oracleFileDTO.setCreate_dt(createDt);
					//System.out.println(create_dt);
					ArrayList<Object> arrayList = mongoData.getAttFile();
					for(int i=0; i<arrayList.size(); i++) {
						//파일ID 생성
						String fileId = oracleFileDAO.setFileId(createDt);
						oracleFileDTO.setFile_id(fileId);
						//System.out.println(file_id);
						LinkedHashMap<String,String> map = (LinkedHashMap<String, String>) arrayList.get(i);
						Iterator<String> keyData = map.keySet().iterator();
						String k, v;
						while(keyData.hasNext()) {
							k = keyData.next();
							if(k.equals("size")) {
								v = String.valueOf(map.get(k));
								oracleFileDTO.setVolume(v);
							} else if(k.equals("key")) {
								v = map.get(k);
								//System.out.println(v);
								String[] result = v.split("/");
								
								if(result.length == 2) {
									oracleFileDTO.setPath("/"+result[0]);
									oracleFileDTO.setPhysic_file_nm(result[1]);
									oracleFileDTO.setLogic_file_nm(result[1]);
								} else if(result.length == 5) {
									String path = "/" + result[0] + "/" + result[1] + "/" + result[2] + "/" + result[3];
									//System.out.println(path);
									oracleFileDTO.setPath(path);
									oracleFileDTO.setPhysic_file_nm(result[4]);
									oracleFileDTO.setLogic_file_nm(result[4]);
								}
							} else if(k.equals("name")) {
								v = map.get(k);
								int idx = v.lastIndexOf(".");
								if(idx != -1) {
									oracleFileDTO.setExt(v.substring(idx+1, v.length()));
								}
								//System.out.println(oracleFileDTO.getExt());
							}
		                } 
						//인서트
						logger.info(oracleFileDTO.getFile_id() +" : "+ oracleFileDTO.getPath() +" : "+ oracleFileDTO.getPhysic_file_nm() +" : "+ oracleFileDTO.getLogic_file_nm() +" : "+ oracleFileDTO.getExt() +" : "+ oracleFileDTO.getVolume() +" : "+ oracleFileDTO.getCreate_id() +" : "+ oracleFileDTO.getCreate_dt() +" : "+ oracleFileDTO.getTemp_article_id());
						oracleFileDAO.insert(oracleFileDTO);
					}
				}
			}
		}
	}
}
