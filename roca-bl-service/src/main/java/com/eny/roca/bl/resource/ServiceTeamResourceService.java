package com.eny.roca.bl.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eny.roca.bean.MasterData;

@RestController
@RequestMapping("/rs/bl")
public class ServiceTeamResourceService {
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/rocajrmembers")
	public List<MasterData> getRocaJuniorTeamMembers() {
		 ResponseEntity<List> registrationRoles = restTemplate.getForEntity("http://roca-db-service/rs/db/rocajrmembers", List.class);
		 return (List<MasterData>)registrationRoles.getBody();
	}
}
