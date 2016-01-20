package com.test.sns.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleUserDTO;

public class PostgresqlUserDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	public String setUserId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String user_id = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT CASE TO_NUMBER(TO_CHAR(NOW(),'YYYY'), '9999') - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYY'), '9999') "
				+ "       WHEN 0 THEN 'U' || TO_CHAR(NOW(),'YYYY') || LPAD(TRIM(TO_CHAR(TO_NUMBER(SUBSTR(MAX(user_id),6), '999999999')+1, '999999999')),9,'0')"
				+ "       ELSE 'U' || TO_CHAR(NOW(),'YYYY') || '000000001' END AS userId FROM temp_tb_user"
			);
			rs = ps.executeQuery();
			
			rs.next();
			
			user_id = rs.getString("userId");
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
		
		return user_id;
	}
	
	public void insert(OracleUserDTO tb_userDto) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_user (user_id, user_nm, lgn_id, emp_no, pst, mail, cp_no, offc_phon_no, use_yn, create_dt, update_dt, temp_user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, LOCALTIMESTAMP, LOCALTIMESTAMP, ?)");
			ps.setString(1, tb_userDto.getUser_id());
			ps.setString(2, tb_userDto.getUser_nm());
			ps.setString(3, tb_userDto.getLgn_id());
			ps.setString(4, tb_userDto.getEmp_no());
			ps.setString(5, tb_userDto.getPst());
			ps.setString(6, tb_userDto.getMail());
			ps.setString(7, tb_userDto.getCp_no());
			ps.setString(8, tb_userDto.getOffc_phon_no());
			ps.setString(9, tb_userDto.getUse_yn());
			ps.setString(10, tb_userDto.getTemp_user_id());
			
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

	public List<HashMap<String, String>> getUserIdAndTempUserId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<HashMap<String, String>> list  = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT user_id, temp_user_id FROM temp_tb_user");
			rs = ps.executeQuery();
			list = new ArrayList<HashMap<String,String>>();
			
			while(rs.next()) {
				HashMap<String, String> data  = new HashMap<String, String>();
				data.put("user_id", rs.getString("user_id"));
				data.put("temp_user_id", rs.getString("temp_user_id"));
				list.add(data);
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
	
	public List<OracleUserDTO> getUsers() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OracleUserDTO> list  = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM temp_tb_user");
			rs = ps.executeQuery();
			list = new ArrayList<OracleUserDTO>();
			
			while(rs.next()) {
				OracleUserDTO tb_userDto = new OracleUserDTO();
				tb_userDto.setTemp_user_id(rs.getString("temp_user_id"));
				tb_userDto.setUser_id(rs.getString("user_id"));
				tb_userDto.setUser_nm(rs.getString("user_nm"));
				tb_userDto.setLgn_id(rs.getString("lgn_id"));
				tb_userDto.setEmp_no(rs.getString("emp_no"));
				tb_userDto.setPst(rs.getString("pst"));
				tb_userDto.setRnk(rs.getString("rnk"));
				tb_userDto.setMail(rs.getString("mail"));
				tb_userDto.setCp_no(rs.getString("cp_no"));
				tb_userDto.setOffc_phon_no(rs.getString("offc_phon_no"));
				tb_userDto.setProfile_img(rs.getString("profile_img"));
				tb_userDto.setUse_yn(rs.getString("use_yn"));
				tb_userDto.setCreate_dt(rs.getString("create_dt"));
				
				list.add(tb_userDto);
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

	public String getUserIdByEmpNo(String empNo) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String userId = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT user_id FROM temp_tb_user where emp_no = ? ");
			ps.setString(1, empNo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				userId = rs.getString("user_id");
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
		
		return userId;
	}

	public String getUserIdByLgnId(String user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String userId = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT user_id FROM temp_tb_user where lgn_id = ? ");
			ps.setString(1, user);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				userId = rs.getString("user_id");
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
		
		return userId;
	}

}
