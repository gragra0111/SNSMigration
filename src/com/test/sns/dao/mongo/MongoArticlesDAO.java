package com.test.sns.dao.mongo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.test.sns.dto.mongo.MongoArticlesDTO;


public class MongoArticlesDAO {
	private static String COLLECTION_NAME = "articles";
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<MongoArticlesDTO> getArticles() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.ASC,"created"));

		return mongoTemplate.find(query, MongoArticlesDTO.class, COLLECTION_NAME);
	}
	/*private static DB db;
	
	public void getArticle() {
		//MongoClient mongo = new MongoClient("192.168.100.48", 27017);
		//DB db = mongo.getDB("sns");
		DBConnection conn = new DBConnection();
		db = conn.makeConnection();
		DBCollection articlesCollection = db.getCollection("articles");
		JSONParser jsonParser = new JSONParser();
		
		BasicDBObject query = new BasicDBObject();
		query.put("articleType", "tabLink");
		
		DBCursor cursor = articlesCollection.find(query);
		while(cursor.hasNext()) {
			DBObject result = (DBObject) cursor.next();
			try {
				JSONObject json = (JSONObject) jsonParser.parse(result.toString());
				String attContent = ((JSONObject)(json.get("attContent"))).get("body").toString();
				System.out.println(attContent);
				int i = attContent.indexOf("href=\"");
				if(i != -1) {
					String link = attContent.substring(i+6);
					i = link.indexOf("\"");
					link = link.substring(0,i);
					System.out.println(link);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		DBObject myDoc = articlesCollection.findOne();
		try {
			JSONObject json = (JSONObject) jsonParser.parse(myDoc.toString());
			//System.out.println(((JSONObject)(json.get("_id"))).get("$oid"));
			String attContent = ((JSONObject)(json.get("attContent"))).get("body").toString();
			System.out.println(attContent);
			int i = attContent.indexOf("href=\"");
			String link = attContent.substring(i+6);
			i = link.indexOf("\"");
			link = link.substring(0,i);
			System.out.println(link);
			//System.out.println(json.get("articleType"));
			//System.out.println(json.get("content"));
			//System.out.println(json.get("isDeleted"));
			//System.out.println(json.get("loginId"));
			//System.out.println(((JSONObject)(json.get("created"))).get("$date"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}*/
}
