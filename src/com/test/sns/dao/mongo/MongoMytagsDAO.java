package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.test.sns.dto.mongo.MongoMytagsDTO;

public class MongoMytagsDAO {
	private static String COLLECTION_NAME = "mytags";
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<MongoMytagsDTO> getMytags() {
		return mongoTemplate.findAll(MongoMytagsDTO.class, COLLECTION_NAME);
	}
}
