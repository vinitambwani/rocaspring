package com.eny.roca.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.IndustryMasterData;
import com.eny.roca.dao.MasterDataDao;
import com.eny.roca.db.bean.Children;
import com.eny.roca.db.bean.MasterData;

@RestController
@RequestMapping("/rs/db")
public class IndustryDBResource {
	@Autowired
	private MasterDataDao masterDataDao;
	
	
	@GetMapping("/getIndustry")
	public List<MasterData> getIndustry(){
		List<IndustryMasterData> industry = masterDataDao.getIndustry();
	 
		List<MasterData> masterData = new ArrayList<MasterData>();
		for(IndustryMasterData p:industry) {
			String description = p.getDescription();
			MasterData master= new MasterData();
			master.setId(p.getId());
			master.setDescription(p.getDescription());
			List<Children> children = new ArrayList<>(1);
			List<IndustryMasterData> childData = industry.stream().filter(i->i.getParentIndustryId()==p.getId()).collect(Collectors.toList());
			for(IndustryMasterData	 idData:childData) {
				Children child = new Children();
				child.setId(idData.getId());
				child.setDescription(idData.getDescription());
				children.add(child);
			}
			master.setChildren(children);
			masterData.add(master);
		}
		return masterData;}
	
	@PostMapping("/getIndustryName")
	public List<Object> getIndustryName(@RequestBody String Id){
		return masterDataDao.getIndustryName(Id);	
	}
}
