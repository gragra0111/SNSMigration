package com.test.sns.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleArticleScopeDTO;

public class PostgresqlArticleScopeDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(OracleArticleScopeDTO tb_articleScopeDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_article_scope (ARTICLE_ID, SHARE_SCOPE, SHARE_USER_ID, SHARE_TYPE) VALUES (?, ?, ?, ?)");
			ps.setString(1, tb_articleScopeDTO.getArticle_id());
			ps.setString(2, tb_articleScopeDTO.getShare_scope());
			ps.setString(3, tb_articleScopeDTO.getShare_user_id());
			ps.setString(4, tb_articleScopeDTO.getShare_type());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
