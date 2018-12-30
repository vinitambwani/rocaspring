package com.eny.roca.bl.resource;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eny.roca.bean.MasterData;
import com.google.gson.Gson;

@RestController
@RequestMapping("/rs/bl")
public class MasterDataResourceService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Gson gson;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/registerroles")
	public List<MasterData> getRegistrationRoles() {
		ResponseEntity<List> registrationRoles = restTemplate.getForEntity("http://roca-db-service/rs/db/getRegristrationRoles", List.class);
		return (List<MasterData>)registrationRoles.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/country")
	public List<MasterData> getCountryData() {
		ResponseEntity<List> countries = restTemplate.getForEntity("http://roca-db-service/rs/db/getCountryData", List.class);
		return (List<MasterData>)countries.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/city")
	public List<MasterData> getCityDataByCountry(@QueryParam(value = "countryId") Integer countryId) {
		String json = gson.toJson(countryId);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<List> countries = restTemplate.postForEntity("http://roca-db-service/rs/db/getCityDataByCountry", httpEntity,List.class);
		return (List<MasterData>)countries.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/industry")
	public List<MasterData> getIndustry() {
		ResponseEntity<List> countries = restTemplate.getForEntity("http://roca-db-service/rs/db/getIndustry", List.class);
		return (List<MasterData>)countries.getBody();
	}
	
	
}
