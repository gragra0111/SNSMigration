package com.test.sns.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dao.oracle.OracleArticleFileDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleFileDAO;

@Service
public class ArticleFileController {
	private final Logger logger = Logger.getLogger(ArticleFileController.class);
	@Autowired
	private OracleArticleFileDAO oracleArticleFileDAO;
	//private PostgresqlArticleFileDAO postgresqlArticleFileDAO;
	
	public void setOracleArticleFileDAO(OracleArticleFileDAO oracleArticleFileDAO) {
		this.oracleArticleFileDAO = oracleArticleFileDAO;
	}
	
	public void articleFileTableMigration() {
		//인서트
		oracleArticleFileDAO.insert();
		logger.info("INSERT END");
	}

}
