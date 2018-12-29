package com.eny.roca.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.MasterDataDao;
import com.eny.roca.db.bean.MasterData;

@RestController
@RequestMapping("/rs/db")
public class IndustryDBResource {
//getIndustry
	@Autowired
	private MasterDataDao masterDataDao;
	
	
	@GetMapping("/getIndustry")
	public List<MasterData> getIndustry(){
		return masterDataDao.getIndustry();	
	}
}
