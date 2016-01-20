package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleUserDeptDTO;

public class OracleUserDeptDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(OracleUserDeptDTO tb_userDeptDto) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_user_dept (user_id, dept_id) VALUES (?, ?)");
			ps.setString(1, tb_userDeptDto.getUser_id());
			ps.setString(2, tb_userDeptDto.getDept_id());
			
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
