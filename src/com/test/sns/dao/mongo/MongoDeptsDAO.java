package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.mongo.MongoDeptsDTO;

@Service
public class MongoDeptsDAO {
	private static String COLLECTION_NAME = "depts";
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoDeptsDTO> getDepts() {
		List<MongoDeptsDTO> list = mongoTemplate.findAll(MongoDeptsDTO.class, COLLECTION_NAME);
		return list;
	}
}
