package com.test.sns.dao.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.test.sns.dto.oracle.OracleGroupDTO;

@Service
public class OracleGroupDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String setGroupId() {
		return this.jdbcTemplate.queryForObject("SELECT CASE TO_NUMBER(TO_CHAR(SYSDATE,'YYYY')) - TO_NUMBER(TO_CHAR(MAX(create_dt),'YYYY')) WHEN 0 THEN 'G' || TO_CHAR(SYSDATE,'YYYY') || LPAD(TO_NUMBER(SUBSTR(MAX(grp_id),10))+1,9,'0') ELSE 'G' || TO_CHAR(SYSDATE,'YYYY') || '000000000' END AS grpId FROM temp_tb_group", String.class);
	}
	
	public void insert(OracleGroupDTO oracleGroupDTO) {
		this.jdbcTemplate.update("INSERT INTO temp_tb_group (grp_id, grp_nm, grp_expn, create_id, create_dt, temp_grp_id) VALUES (?, ?, ?, ?, SYSTIMESTAMP, ?)",
				oracleGroupDTO.getGrp_id(), oracleGroupDTO.getGrp_nm(), oracleGroupDTO.getGrp_expn(), oracleGroupDTO.getCreate_id(), oracleGroupDTO.getTemp_grp_id());
	}
	
	public List<OracleGroupDTO> getGroup() {
		return this.jdbcTemplate.query("SELECT * FROM temp_tb_group"
				, new RowMapper<OracleGroupDTO>() {
					public OracleGroupDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						OracleGroupDTO oracleGroupDTO = new OracleGroupDTO();
						oracleGroupDTO.setTemp_grp_id(rs.getString("temp_grp_id"));
						oracleGroupDTO.setGrp_id(rs.getString("grp_id"));
						oracleGroupDTO.setGrp_nm(rs.getString("grp_nm"));
						oracleGroupDTO.setGrp_expn(rs.getString("grp_expn"));
						oracleGroupDTO.setCreate_id(rs.getString("create_id"));
						oracleGroupDTO.setCreate_dt(rs.getString("create_dt"));
						return oracleGroupDTO;
					}});
	}
	
	public List<Map<String, String>> getUserIdByTempGroupId(String tempGrpId) {
		return this.jdbcTemplate.query("SELECT GRP_ID, MBR_ID FROM TEMP_TB_GROUP_MEMBER WHERE GRP_ID = (SELECT GRP_ID FROM TEMP_TB_GROUP WHERE TEMP_GRP_ID=?)", new Object[]{tempGrpId}
				, new RowMapper<Map<String, String>>() {
					public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("shareScope", rs.getString("GRP_ID"));
						map.put("shareUserId", rs.getString("MBR_ID"));
						return map;
					}});
	}
	
}
