package com.eny.roca.bl.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rs/bl")
public class LoginResourceService {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/login")
	public String getDummyString() {
		 ResponseEntity<String> response =  restTemplate.getForEntity("http://roca-db-service/rs/db/test", String.class);
		 return response.getBody();
	}
}
