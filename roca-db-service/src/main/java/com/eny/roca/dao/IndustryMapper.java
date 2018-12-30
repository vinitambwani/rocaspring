package com.eny.roca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eny.roca.db.bean.Children;
import com.eny.roca.db.bean.MasterData;

public class IndustryMapper implements RowMapper<MasterData> {

	@Override
	public MasterData mapRow(ResultSet rs, int rowNum) throws SQLException {
		MasterData md =new MasterData();
		md.setId(rs.getInt("id"));
		md.setDescription(rs.getString("parentName"));
		if(null!=rs.getString("childName")) {
			Children children = new Children();
			children.setId(rs.getInt("childId"));
			children.setDescription(rs.getString("childName"));
			md.setChildren(children);
		}
		return md;
	}

	 

}
