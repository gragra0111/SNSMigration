package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleGroupMemberDTO;

public class OracleGroupMemberDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(OracleGroupMemberDTO tb_groupMemberDto) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_group_member (grp_id, mbr_id, mbr_type, create_id, create_dt) VALUES (?, ?, ?, ?, SYSTIMESTAMP)");
			ps.setString(1, tb_groupMemberDto.getGrp_id());
			ps.setString(2, tb_groupMemberDto.getMbr_id());
			ps.setString(3, tb_groupMemberDto.getMbr_type());
			ps.setString(4, tb_groupMemberDto.getCreate_id());
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
