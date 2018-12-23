package com.eny.roca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eny.roca.db.bean.MasterData;

@Repository
public class MasterDataDaoImpl implements MasterDataDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MasterData> getMasterData(String tableName) {
		return jdbcTemplate.query("select * from "+tableName+" where is_active=1", new MasterDataMapper()); 
	}
	
	

}
