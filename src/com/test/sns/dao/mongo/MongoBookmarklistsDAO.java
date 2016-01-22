package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.mongo.MongoBookmarklistsDTO;

@Service
public class MongoBookmarklistsDAO {
	private static String COLLECTION_NAME = "bookmarklists";
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoBookmarklistsDTO> getBookmarkLists() {
		List<MongoBookmarklistsDTO> list = mongoTemplate.findAll(MongoBookmarklistsDTO.class, COLLECTION_NAME);
		return list;
	}
}
