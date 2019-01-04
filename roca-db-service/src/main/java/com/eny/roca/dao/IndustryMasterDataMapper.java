package com.eny.roca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class IndustryMasterDataMapper implements RowMapper<IndustryMasterData> {

	@Override
	public IndustryMasterData mapRow(ResultSet rs, int rowNum) throws SQLException {
		IndustryMasterData industryData =new IndustryMasterData();
		industryData.setId(rs.getInt("id"));
		industryData.setDescription(rs.getString("name"));
		industryData.setParentIndustryId(rs.getInt("parentId")); // NULL will be converted to 0.... so make sure dont add 0 in any of the id...
		return industryData;
	}

	 

}
