package com.eny.roca.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.MasterDataDao;
import com.eny.roca.db.bean.MasterData;

@RestController
@RequestMapping("/rs/db")
public class CountryDBResource {
	@Autowired
	private MasterDataDao masterDataDao;
	
	@GetMapping("/getCountryData")
	public List<MasterData> getCountryData(){
		return masterDataDao.getCountryData();	
	}
	
	//getCityDataByCountry
	@PostMapping("/getCityDataByCountry")
	public List<MasterData> getCityDataByCountry(@RequestBody Integer countryId){
		return masterDataDao.getCityDataByCountry(countryId);	
	}
}
