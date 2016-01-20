package com.test.sns.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleBookmarkDTO;

public class PostgresqlBookmarkDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public String setBkmkId(String create_dt) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bkmk_id = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT"
				+ " CASE TO_NUMBER(TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYY'), '9999') - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYY'), '9999')"
				+ " WHEN 0 THEN 'B' || TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYY') || LPAD(TRIM(TO_CHAR(TO_NUMBER(SUBSTR(MAX(bkmk_id),6), '999999999')+1, '999999999')),9,'0')"
				+ " ELSE 'B' || TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYY') || '000000000' END AS bkmkId FROM temp_tb_bookmark"
			);
			ps.setString(1, create_dt);
			ps.setString(2, create_dt);
			ps.setString(3, create_dt);
			rs = ps.executeQuery();
			rs.next();
			
			bkmk_id = rs.getString("bkmkId");
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
		
		return bkmk_id;
	}

	public void insert(OracleBookmarkDTO tb_bookmarkDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_bookmark (bkmk_id, bkmk_nm, create_id, create_dt, temp_bkmk_id) VALUES (?, ?, ?, TO_TIMESTAMP(?,'YYMMDDHH24MISS'), ?)");
			ps.setString(1, tb_bookmarkDTO.getBkmk_id());
			ps.setString(2, tb_bookmarkDTO.getBkmk_nm());
			ps.setString(3, tb_bookmarkDTO.getCreate_id());
			ps.setString(4, tb_bookmarkDTO.getCreate_dt());
			ps.setString(5, tb_bookmarkDTO.getTemp_bkmk_id());
			
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

	public Map<String, String> getBookmarkIdAndByTempBookmarkId(String tempBookmarkId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> map = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT BKMK_ID, CREATE_ID FROM TEMP_TB_BOOKMARK WHERE TEMP_BKMK_ID = ?");
			ps.setString(1, tempBookmarkId);
			rs = ps.executeQuery();

			if(rs.next()) {
				map = new HashMap<String, String>();
				map.put("bkmkId", rs.getString("BKMK_ID"));
				map.put("createId", rs.getString("CREATE_ID"));
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
		
		return map;
	}
	
}
