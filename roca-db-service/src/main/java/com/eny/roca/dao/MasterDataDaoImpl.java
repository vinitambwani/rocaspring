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
	public List<MasterData> getRoles(String tableName) {
		return jdbcTemplate.query("select * from RocaMaster.RoleMaster where IsActive=1", new MasterDataMapper()); 
	}

	@Override
	public List<MasterData> getCountryData() {
		return jdbcTemplate.query("select * from RocaMaster.Country where IsActive=1", new CountryMasterMapper());
	}

	@Override
	public List<MasterData> getCityDataByCountry(Integer countryId) {
		return jdbcTemplate.query("select * from RocaMaster.CityMaster where isActive=1 and CountryId=?", new Object[] { countryId } , new CityMasterMapper());
		 
	}

	@Override
	public List<MasterData> getIndustry() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
