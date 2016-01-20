package com.test.sns.controller;

import org.springframework.context.ApplicationContext;

import com.test.sns.dao.oracle.OracleArticleFileDAO;
import com.test.sns.dao.postgresql.PostgresqlArticleFileDAO;

public class ArticleFileController {
	private PostgresqlArticleFileDAO postgresqlArticleFileDAO;
	
	public ArticleFileController(ApplicationContext context) {
		this.postgresqlArticleFileDAO = context.getBean("postgresqlArticleFileDAO", PostgresqlArticleFileDAO.class);
		articleFileTableMigration();
	}

	private void articleFileTableMigration() {
		//인서트
		postgresqlArticleFileDAO.insert();
		System.out.println("INSERT END");
	}

}
