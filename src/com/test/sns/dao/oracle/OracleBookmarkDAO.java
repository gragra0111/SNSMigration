package com.test.sns.dao.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleBookmarkDTO;

@Service
public class OracleBookmarkDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String setBkmkId(String create_dt) {
		return this.jdbcTemplate.queryForObject("SELECT"
				+ " CASE TO_NUMBER(TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYY')) - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYY'))"
				+ " WHEN 0 THEN 'B' || TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYY') || LPAD(TO_NUMBER(SUBSTR(MAX(bkmk_id),6))+1,9,'0')"
				+ " ELSE 'B' || TO_CHAR(TO_DATE(?,'YYMMDDHHMISS'),'YYYY') || '000000000' END AS bkmkId FROM temp_tb_bookmark"
				, new Object[]{create_dt, create_dt, create_dt}, String.class);
	}

	public void insert(OracleBookmarkDTO oracleBookmarkDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_bookmark (bkmk_id, bkmk_nm, create_id, create_dt, temp_bkmk_id) VALUES (?, ?, ?, TO_DATE(?,'YYMMDDHHMISS'), ?)",
				oracleBookmarkDTO.getBkmk_id(), oracleBookmarkDTO.getBkmk_nm(), oracleBookmarkDTO.getCreate_id(), oracleBookmarkDTO.getCreate_dt(), oracleBookmarkDTO.getTemp_bkmk_id());
	}

	public Map<String, String> getBookmarkIdAndByTempBookmarkId(String tempBookmarkId) {
		try {
			return this.jdbcTemplate.queryForObject("SELECT BKMK_ID, CREATE_ID FROM TEMP_TB_BOOKMARK WHERE TEMP_BKMK_ID = ?"
					, new Object[]{tempBookmarkId}
					, new RowMapper<Map<String, String>>() {
						public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
							Map<String, String>map = new HashMap<String, String>();
							map.put("bkmkId", rs.getString("BKMK_ID"));
							map.put("createId", rs.getString("CREATE_ID"));
							return map;
						}});
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
