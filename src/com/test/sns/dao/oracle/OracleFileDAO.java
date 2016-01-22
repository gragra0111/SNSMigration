package com.test.sns.dao.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleFileDTO;

@Service
public class OracleFileDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String setFileId(String create_dt) {
		return this.jdbcTemplate.queryForObject("SELECT"
				+ " CASE TO_NUMBER(TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYYMMDD')) - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYYMMDD'))"
				+ " WHEN 0 THEN 'F' || TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYYMMDD') || LPAD(TO_NUMBER(SUBSTR(MAX(file_id),10))+1,9,'0')"
				+ " ELSE 'F' || TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYYMMDD') || '000000000' END AS fileId FROM temp_tb_file"
				, new Object[] {create_dt, create_dt, create_dt}
				, String.class);
	}

	public void insert(OracleFileDTO oracleFileDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_file (FILE_ID, PATH, PHYSIC_FILE_NM, LOGIC_FILE_NM, EXT, VOLUME, CREATE_ID, CREATE_DT, TEMP_ARTICLE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, TO_DATE(?,'YYMMDDHHMISS'), ?)",
				oracleFileDTO.getFile_id(), oracleFileDTO.getPath(), oracleFileDTO.getPhysic_file_nm(), oracleFileDTO.getLogic_file_nm(), oracleFileDTO.getExt(), oracleFileDTO.getVolume(), oracleFileDTO.getCreate_id(), oracleFileDTO.getCreate_dt(), oracleFileDTO.getTemp_article_id());
	}
}
