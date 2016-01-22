package com.test.sns.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleFileDTO;

@Service
public class PostgresqlFileDAO {
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String setFileId(String create_dt) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String file_id = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT"
				+ " CASE TO_NUMBER(TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYYMMDD'), '99999999') - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYYMMDD'), '99999999')"
				+ " WHEN 0 THEN 'F' || TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYYMMDD') || LPAD(TRIM(TO_CHAR(TO_NUMBER(SUBSTR(MAX(file_id),10), '999999999')+1, '999999999')),9,'0')"
				+ " ELSE 'F' || TO_CHAR(TO_DATE(?,'YYMMDDHH24MISS'),'YYYYMMDD') || '000000000' END AS fileId FROM temp_tb_file"
			);
			ps.setString(1, create_dt);
			ps.setString(2, create_dt);
			ps.setString(3, create_dt);
			rs = ps.executeQuery();
			rs.next();
			
			file_id = rs.getString("fileId");
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
		
		return file_id;
	}

	public void insert(OracleFileDTO tb_fileDTO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO temp_tb_file (FILE_ID, PATH, PHYSIC_FILE_NM, LOGIC_FILE_NM, EXT, VOLUME, CREATE_ID, CREATE_DT, TEMP_ARTICLE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, TO_TIMESTAMP(?,'YYMMDDHH24MISS'), ?)");
			ps.setString(1, tb_fileDTO.getFile_id());
			ps.setString(2, tb_fileDTO.getPath());
			ps.setString(3, tb_fileDTO.getPhysic_file_nm());
			ps.setString(4, tb_fileDTO.getLogic_file_nm());
			ps.setString(5, tb_fileDTO.getExt());
			ps.setInt(6, (int) Double.parseDouble(tb_fileDTO.getVolume()));
			//ps.setInt(6, (int) Integer.parseInt(tb_fileDTO.getVolume()));
			ps.setString(7, tb_fileDTO.getCreate_id());
			ps.setString(8, tb_fileDTO.getCreate_dt());
			ps.setString(9, tb_fileDTO.getTemp_article_id());
			
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
