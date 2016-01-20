package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleDeptDTO;

public class OracleDeptDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(OracleDeptDTO tb_deptDto) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_dept (dept_id, parent_dept_id, dept_nm, use_yn) VALUES (?, ?, ?, ?)");
			ps.setString(1, tb_deptDto.getDept_id());
			ps.setString(2, tb_deptDto.getParent_dept_id());
			ps.setString(3, tb_deptDto.getDept_nm());
			ps.setString(4, tb_deptDto.getUse_yn());
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
