package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleMessageDTO;

public class OracleMessageDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Boolean msgCheck(String tagname) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean check = false;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM TEMP_TB_MESSAGE WHERE MSG=?");
			ps.setString(1, tagname);
			rs = ps.executeQuery();
			if(rs.next()) {
				check = false;
			} else {
				check = true;
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
		
		return check;
	}

	public String setMsgId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String msg_id = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT CASE TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(NVL(MAX(create_dt),SYSDATE-1),'YYYYMMDD')) WHEN 0 THEN 'M' || TO_CHAR(SYSDATE,'YYYYMMDD') || LPAD(TO_NUMBER(SUBSTR(MAX(msg_id),10))+1,9,'0') ELSE 'M' || TO_CHAR(SYSDATE,'YYYYMMDD') || '000000000' END AS msgId FROM temp_tb_message");
			rs = ps.executeQuery();
			rs.next();
			
			msg_id = rs.getString("msgId");
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
		
		return msg_id;
	}

	public void insert(OracleMessageDTO tb_messageDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_message (msg_id, msg, create_dt, temp_msg_id) VALUES (?, ?, SYSTIMESTAMP, ?)");
			ps.setString(1, tb_messageDTO.getMsg_id());
			ps.setString(2, tb_messageDTO.getMsg());
			ps.setString(3, tb_messageDTO.getTemp_msg_id());
			
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
	
	public String getMsgIdByMsg(String msg) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String msg_id = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT msg_id FROM TEMP_TB_MESSAGE WHERE msg = ?");
			ps.setString(1, msg);
			rs = ps.executeQuery();
			rs.next();
			
			msg_id = rs.getString("msg_id");
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
		
		return msg_id;
	}
	
}
