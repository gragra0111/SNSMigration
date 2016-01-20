package com.test.sns.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.sns.dto.oracle.OracleHashtagDTO;

public class OracleHashtagDAO {
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String setHashtagId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String hashtag_id = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT"
					+ " CASE TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(NVL(MAX(create_dt),SYSDATE-1),'YYYYMMDD'))"
					+ " WHEN 0 THEN 'T' || TO_CHAR(SYSDATE,'YYYYMMDD') || LPAD(TO_NUMBER(SUBSTR(MAX(hashtag_id),10))+1,9,'0')"
					+ " ELSE 'T' || TO_CHAR(SYSDATE,'YYYYMMDD') || '000000000' END AS hashtagId FROM temp_tb_hashtag");
			rs = ps.executeQuery();
			rs.next();
			
			hashtag_id = rs.getString("hashtagId");
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
		
		return hashtag_id;
	}

	public void insert(OracleHashtagDTO hashtagDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_hashtag (HASHTAG_ID, HASHTAG, USE_CNT, CREATE_DT, UPDATE_DT) VALUES (?, ?, ?, SYSTIMESTAMP, SYSTIMESTAMP)");
			ps.setString(1, hashtagDTO.getHashtag_id());
			ps.setString(2, hashtagDTO.getHashtag());
			ps.setString(3, hashtagDTO.getUse_cnt());
			
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

	public String getTagIdByTagNm(String tag) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String hashtagId = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT hashtag_id FROM temp_tb_hashtag WHERE HASHTAG = ?");
			ps.setString(1, tag);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				hashtagId = rs.getString("hashtag_id");
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
		return hashtagId;
	}

	public void insert(String articleId, String hashtagId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_article_hashtag (ARTICLE_ID, HASHTAG_ID) VALUES (?, ?)");
			ps.setString(1, articleId);
			ps.setString(2, hashtagId);
			
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
