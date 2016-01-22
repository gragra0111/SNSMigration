package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.mongo.MongoTaglistsDTO;

@Service
public class MongoTaglistsDAO {
	private static String COLLECTION_NAME = "taglists";
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoTaglistsDTO> getTaglists() {
		return mongoTemplate.findAll(MongoTaglistsDTO.class, COLLECTION_NAME);
	}
}
