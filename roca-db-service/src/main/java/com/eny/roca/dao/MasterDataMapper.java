package com.eny.roca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eny.roca.db.bean.MasterData;

public class MasterDataMapper implements RowMapper<MasterData> {
 
	@Override
	public MasterData mapRow(ResultSet rs, int arg1) throws SQLException {
		MasterData masterData = new MasterData();
		masterData.setDescription(rs.getString("Name"));
		masterData.setId(rs.getInt("Id"));
		return masterData;
	}

}
