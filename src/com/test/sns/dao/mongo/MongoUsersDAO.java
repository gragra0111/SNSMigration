package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.mongo.MongoUsersDTO;

@Service
public class MongoUsersDAO {
	private static String COLLECTION_NAME = "users";
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoUsersDTO> getUser() {
		List<MongoUsersDTO> list = mongoTemplate.findAll(MongoUsersDTO.class, COLLECTION_NAME);
		return list;
	}
}
