package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleBookmarkArticleDTO;

public class OracleBookmarkArticleDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(OracleBookmarkArticleDTO tb_bookmarkArticleDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_bookmark_article (bkmk_id, article_id, create_id, create_dt) VALUES (?, ?, ?, SYSTIMESTAMP)");
			ps.setString(1, tb_bookmarkArticleDTO.getBkmk_id());
			ps.setString(2, tb_bookmarkArticleDTO.getArticle_id());
			ps.setString(3, tb_bookmarkArticleDTO.getCreate_id());

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
