package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.mongo.MongoMylinksDTO;

@Service
public class MongoMylinksDAO {
	private static String COLLECTION_NAME = "mylinks";
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoMylinksDTO> getMyLinks() {
		List<MongoMylinksDTO> list = mongoTemplate.findAll(MongoMylinksDTO.class, COLLECTION_NAME);
		return list;
	}
}
