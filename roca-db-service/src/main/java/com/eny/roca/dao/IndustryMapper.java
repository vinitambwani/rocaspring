package com.eny.roca.dao;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.eny.roca.db.bean.MasterData;

public class IndustryMapper implements RowMapper<MasterData> {

	@Override
	public MasterData mapRow(ResultSet rs, int rowNum) throws SQLException {
		MasterData md =new MasterData();
		if(rs.getString("Id") != null) {
		md.setId(rs.getInt("Id"));
		}
		md.setDescription(rs.getString("Name"));
		if (rs.getString("ParentId") != null) {
			md.setId(rs.getInt("ParentId"));
		}
		return md;
	}

	 

}
