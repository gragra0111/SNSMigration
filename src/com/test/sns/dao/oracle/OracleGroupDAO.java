package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleGroupDTO;

public class OracleGroupDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<OracleGroupDTO> getGroup() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OracleGroupDTO> list = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("select * from temp_tb_group");
			rs = ps.executeQuery();
			list = new ArrayList<OracleGroupDTO>();
			
			while(rs.next()) {
				OracleGroupDTO tb_groupDto = new OracleGroupDTO();
				tb_groupDto.setTemp_grp_id(rs.getString("temp_grp_id"));
				tb_groupDto.setGrp_id(rs.getString("grp_id"));
				tb_groupDto.setGrp_nm(rs.getString("grp_nm"));
				tb_groupDto.setGrp_expn(rs.getString("grp_expn"));
				tb_groupDto.setCreate_id(rs.getString("create_id"));
				tb_groupDto.setCreate_dt(rs.getString("create_dt"));
				
				list.add(tb_groupDto);
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
		
		return list;
	}
	
	public String setGroupId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String group_id = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT CASE TO_NUMBER(TO_CHAR(SYSDATE,'YYYY')) - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYY')) WHEN 0 THEN 'G' || TO_CHAR(SYSDATE,'YYYY') || LPAD(TO_NUMBER(SUBSTR(MAX(grp_id),10))+1,9,'0') ELSE 'G' || TO_CHAR(SYSDATE,'YYYY') || '000000000' END AS grpId FROM temp_tb_group");
			rs = ps.executeQuery();
			
			rs.next();
			
			group_id = rs.getString("grpId");
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
		
		return group_id;
	}
	
	public void insert(OracleGroupDTO tb_groupDto) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_group (grp_id, grp_nm, grp_expn, create_id, create_dt, temp_grp_id) VALUES (?, ?, ?, ?, SYSTIMESTAMP, ?)");
			ps.setString(1, tb_groupDto.getGrp_id());
			ps.setString(2, tb_groupDto.getGrp_nm());
			ps.setString(3, tb_groupDto.getGrp_expn());
			ps.setString(4, tb_groupDto.getCreate_id());
			ps.setString(5, tb_groupDto.getTemp_grp_id());
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

	public List<Map<String, String>> getUserIdByTempGroupId(String key) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		try {
			conn = dataSource.getConnection();
			System.out.println("key : " + key);
			ps = conn.prepareStatement("SELECT GRP_ID, MBR_ID FROM TEMP_TB_GROUP_MEMBER WHERE GRP_ID = (SELECT GRP_ID FROM TEMP_TB_GROUP WHERE TEMP_GRP_ID=?)");
			ps.setString(1, key);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("shareScope", rs.getString("GRP_ID"));
				map.put("shareUserId", rs.getString("MBR_ID"));
				list.add(map);
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
		
		return list;
	}
	
}
