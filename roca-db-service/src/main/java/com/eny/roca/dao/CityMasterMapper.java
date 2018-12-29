package com.eny.roca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.eny.roca.db.bean.MasterData;

public class CityMasterMapper implements RowMapper<MasterData> {

	@Override
	public MasterData mapRow(ResultSet rs, int rowNum) throws SQLException {
		MasterData masterData = new MasterData();
		masterData.setDescription(rs.getString("CityName"));
		masterData.setId(rs.getInt("id"));
		return masterData;
	}
}
