package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.test.sns.dto.mongo.MongoGroupsDTO;

public class MongoGroupsDAO {
	private static String COLLECTION_NAME = "groups";
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoGroupsDTO> getGroup() {
		List<MongoGroupsDTO> list = mongoTemplate.findAll(MongoGroupsDTO.class, COLLECTION_NAME);
		return list;
	}
	
}