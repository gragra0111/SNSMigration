package com.test.sns.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleArticleDTO;

public class PostgresqlArticleDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String setArticleId(String create_dt) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String article_id = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT"
				+ " CASE TO_NUMBER(TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYYMMDD'), '99999999') - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYYMMDD'), '99999999')"
				+ " WHEN 0 THEN 'A' || TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYYMMDD') || LPAD(TRIM(TO_CHAR(TO_NUMBER(SUBSTR(MAX(article_id),10), '999999999')+1, '999999999')),9,'0')"
				+ " ELSE 'A' || TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYYMMDD') || '000000000' END AS articleId FROM temp_tb_article"
			);
			ps.setString(1, create_dt);
			ps.setString(2, create_dt);
			ps.setString(3, create_dt);
			rs = ps.executeQuery();
			rs.next();
			
			article_id = rs.getString("articleId");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		
		return article_id;
	}

	public void insert(OracleArticleDTO tb_articleDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("INSERT INTO temp_tb_article (ARTICLE_ID, ARTICLE_TYPE, CTNT, CTNT_RAW, DEL_STAT, CREATE_ID, CREATE_DT, UPDATE_ID, UPDATE_DT, TEMP_ARTICLE_ID) VALUES (?, ?, ?, ?, ?, ?, TO_TIMESTAMP(?,'YYMMDDHH24MISS'), ?, TO_TIMESTAMP(?,'YYMMDDHH24MISS'), ?)");
			ps.setString(1, tb_articleDTO.getArticle_id());
			ps.setString(2, tb_articleDTO.getArticle_type());
			ps.setString(3, tb_articleDTO.getCtnt());
			ps.setString(4, tb_articleDTO.getCtnt_raw());
			ps.setString(5, tb_articleDTO.getDel_stat());
			ps.setString(6, tb_articleDTO.getCreate_id());
			ps.setString(7, tb_articleDTO.getCreate_dt());
			ps.setString(8, tb_articleDTO.getUpdate_id());
			ps.setString(9, tb_articleDTO.getUpdate_dt());
			ps.setString(10, tb_articleDTO.getTemp_article_id());
			
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

	public String getArticleIdByTempArticleId(String temp_article_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String article_id = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT ARTICLE_ID FROM TEMP_TB_ARTICLE WHERE TEMP_ARTICLE_ID = ?");
			ps.setString(1, temp_article_id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				article_id = rs.getString("article_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		
		return article_id;
	}
}
