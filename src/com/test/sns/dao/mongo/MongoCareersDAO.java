package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.test.sns.dto.mongo.MongoCareersDTO;


public class MongoCareersDAO {
	private static String COLLECTION_NAME = "careers";
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoCareersDTO> getCareers() {
		return mongoTemplate.findAll(MongoCareersDTO.class, COLLECTION_NAME);
	}
}
