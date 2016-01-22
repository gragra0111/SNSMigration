package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.mongo.MongoGrpMembersDTO;

@Service
public class MongoGrpMembersDAO {
	private static String COLLECTION_NAME = "grp_members";
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoGrpMembersDTO> getGroupMembers() {
		List<MongoGrpMembersDTO> list = mongoTemplate.findAll(MongoGrpMembersDTO.class, COLLECTION_NAME);
		return list;
	}
}
