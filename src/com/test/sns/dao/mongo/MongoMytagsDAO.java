package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.mongo.MongoMytagsDTO;

@Service
public class MongoMytagsDAO {
	private static String COLLECTION_NAME = "mytags";
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<MongoMytagsDTO> getMytags() {
		return mongoTemplate.findAll(MongoMytagsDTO.class, COLLECTION_NAME);
	}
}
