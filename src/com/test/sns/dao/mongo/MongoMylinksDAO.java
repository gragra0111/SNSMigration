package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.test.sns.dto.mongo.MongoMylinksDTO;

public class MongoMylinksDAO {
	private static String COLLECTION_NAME = "mylinks";
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoMylinksDTO> getMyLinks() {
		List<MongoMylinksDTO> list = mongoTemplate.findAll(MongoMylinksDTO.class, COLLECTION_NAME);
		return list;
	}
}
