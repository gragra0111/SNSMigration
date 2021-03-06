package com.test.sns.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleUserMessageDTO;

@Service
public class PostgresqlUserMessageDAO {
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(OracleUserMessageDTO tb_userMessageDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_user_message (user_id, msg_id, create_id, create_dt) VALUES (?, ?, ?, LOCALTIMESTAMP)");
			ps.setString(1, tb_userMessageDTO.getUser_id());
			ps.setString(2, tb_userMessageDTO.getMsg_id());
			ps.setString(3, tb_userMessageDTO.getCreate_id());
			
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
