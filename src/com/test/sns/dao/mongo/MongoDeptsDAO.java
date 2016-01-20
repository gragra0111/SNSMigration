package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.test.sns.dto.mongo.MongoDeptsDTO;

public class MongoDeptsDAO {
	private static String COLLECTION_NAME = "depts";
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoDeptsDTO> getDepts() {
		List<MongoDeptsDTO> list = mongoTemplate.findAll(MongoDeptsDTO.class, COLLECTION_NAME);
		return list;
	}
}
