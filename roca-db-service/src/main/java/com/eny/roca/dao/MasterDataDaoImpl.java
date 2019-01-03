package com.eny.roca.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.eny.roca.db.bean.MasterData;

@Repository
public class MasterDataDaoImpl implements MasterDataDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedTemplate;
	
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
		String query = ""
				+ "SELECT ipm.id, "
				+ "       ipm.NAME AS parentName, "
				+ "       icm.id   AS childId, "
				+ "       icm.NAME AS childName "
				+ "FROM   rocamaster.industryparentmaster AS ipm "
				+ "       LEFT JOIN rocamaster.industrychildmaster AS icm "
				+ "              ON ipm.id = icm.industryparentid "
				+ "                 AND icm.isactive = 1 ";
		return jdbcTemplate.query(query , new IndustryMapper());
	}
	
	@Override
	public List<Object> getIndustryName(String Id) {

		String getNameofIndustries = "select Name from rocamaster.industry where rocamaster.industry.Id in (:Id)";
		List<Map<String, Object>> objects = new ArrayList<Map<String, Object>>();
		List<Object> list=new ArrayList<Object>();
		for(String i : Id.split(",")){
		SqlParameterSource namedParameters = new MapSqlParameterSource("Id", Integer.parseInt(i));
		objects = namedTemplate.queryForList(getNameofIndustries, namedParameters);
		System.out.println(objects);
		 for(Map<String,Object> j:objects){
	    	  list.addAll(j.values());
	      }
		}
		return list;
	}

	@Override
	public List<MasterData> getRocaJuniorTeamMembers() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from rocaserviceteam.ServiceTeamUsers where isActive = 1 ;", new JuniourServiceTeamMemberMapper());

	}
	
	

}
