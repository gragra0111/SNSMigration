package com.test.sns.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleUserCareerDTO;

public class PostgresqlUserCareerDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(OracleUserCareerDTO oracleUserCareerDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_user_career (USER_ID, SEQ, START_DD, END_DD, CTNT, CREATE_DT, UPDATE_DT) VALUES (?, ?, ?, ?, ?, TO_TIMESTAMP(?,'YYMMDDHH24MISS'), TO_TIMESTAMP(?,'YYMMDDHH24MISS'))");
			ps.setString(1, oracleUserCareerDTO.getUser_id());
			ps.setInt(2, Integer.parseInt(oracleUserCareerDTO.getSeq()));
			ps.setString(3, oracleUserCareerDTO.getStart_dd());
			ps.setString(4, oracleUserCareerDTO.getEnd_dd());
			ps.setString(5, oracleUserCareerDTO.getCtnt());
			ps.setString(6, oracleUserCareerDTO.getCreate_dt());
			ps.setString(7, oracleUserCareerDTO.getUpdate_dt());
			
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
