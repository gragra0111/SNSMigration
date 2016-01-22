package com.test.sns.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleArticleReplyDTO;

@Service
public class PostgresqlArticleReplyDAO {
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Integer setSeq(String articleId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int seq = 1;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT max(seq) as seq FROM temp_tb_article_reply where article_id=?");
			ps.setString(1, articleId);
			rs = ps.executeQuery();
			
			rs.next();
			
			if(rs.getString("seq") != null) {
				seq = Integer.parseInt(rs.getString("seq")) + 1;
			}
			//System.out.println(seq);
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
		
		return seq;
	}

	public void insert(OracleArticleReplyDTO tb_articleReplyDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_article_reply (article_id, seq, ctnt, del_stat, create_id, create_dt) VALUES (?, ?, ?, ?, ?, TO_TIMESTAMP(?, 'YYMMDDHH24MISS'))");
			ps.setString(1, tb_articleReplyDTO.getArticle_id());
			ps.setInt(2, Integer.parseInt(tb_articleReplyDTO.getSeq()));
			ps.setString(3, tb_articleReplyDTO.getCtnt());
			ps.setString(4, tb_articleReplyDTO.getDel_stat());
			ps.setString(5, tb_articleReplyDTO.getCreate_id());
			ps.setString(6, tb_articleReplyDTO.getCreate_dt());

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
