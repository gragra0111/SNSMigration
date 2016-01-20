package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleUserLinkDTO;

public class OracleUserLinkDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Integer setSeq(String userId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int seq = 1;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT max(seq) as seq FROM temp_tb_user_link where user_id=?");
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			rs.next();
			
			if(rs.getString("seq") != null) {
				seq = Integer.parseInt(rs.getString("seq")) + 1;
			}
			System.out.println(seq);
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

	public void insert(OracleUserLinkDTO tb_userLinkDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_user_link (user_id, seq, link_nm, link_url, create_dt) VALUES (?, ?, ?, ?, SYSTIMESTAMP)");
			ps.setString(1, tb_userLinkDTO.getUser_id());
			ps.setInt(2, tb_userLinkDTO.getSeq());
			ps.setString(3, tb_userLinkDTO.getLink_nm());
			ps.setString(4, tb_userLinkDTO.getLink_url());

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
