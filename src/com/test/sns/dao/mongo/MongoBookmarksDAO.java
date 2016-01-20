package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.test.sns.dto.mongo.MongoBookmarksDTO;

public class MongoBookmarksDAO {
	private static String COLLECTION_NAME = "bookmarks";
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<MongoBookmarksDTO> getBookmarks() {
		return mongoTemplate.findAll(MongoBookmarksDTO.class, COLLECTION_NAME);
	}
}
