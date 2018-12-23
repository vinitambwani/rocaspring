package com.eny.roca.bl.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eny.roca.bean.MasterData;
import com.eny.roca.bean.RegistrationBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/rs/bl")
public class MasterDataResourceService {
	@Autowired
	private RestTemplate restTemplate;
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/registerroles")
	public List<MasterData> registerNewUser() {
		ResponseEntity<List> registrationRoles = restTemplate.getForEntity("http://roca-db-service/rs/db/getRegristrationRoles", List.class);
		return (List<MasterData>)registrationRoles.getBody();
	}
}
