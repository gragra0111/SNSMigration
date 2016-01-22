package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.test.sns.dto.mongo.MongoRepliesDTO;

@Service
public class MongoRepliesDAO {
	private static String COLLECTION_NAME = "replies";
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoRepliesDTO> getArticleReply() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.ASC,"created"));

		return mongoTemplate.find(query, MongoRepliesDTO.class, COLLECTION_NAME);
	}
}
