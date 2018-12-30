package com.eny.roca.bl.resource;

import java.util.List;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eny.roca.bean.UserRegistration;
import com.eny.roca.bl.services.SmtpMailSender;
import com.eny.roca.bl.services.ValidateEmail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/rs/bl")
public class RocaRegistrationResourceService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SmtpMailSender smtpMailSender;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getRegistrationData")
	public List<UserRegistration> getUserRegistration() {
		ResponseEntity<List> userRegistration = restTemplate.getForEntity("http://roca-db-service/rs/db/getRegister", List.class);
		return (List<UserRegistration>)userRegistration.getBody();
	}
	
	@PostMapping("/register")
	public Integer registerNewUser(@RequestBody UserRegistration userRegistration) {
		final GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String json = gson.toJson(userRegistration);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<Integer> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/setRegister", httpEntity, Integer.class);
		
		
		return postForEntity.getBody();
	}
	
	@PostMapping("/validateEmailId")
	public String validateEmail(@RequestParam("email") String email) {

		String val = null;
		try {
			val = Boolean.toString(ValidateEmail.isEmailValid(email));
		} catch (Exception e) {
			val = "invalid Email";
		}
		return val;
	}
	
	@PostMapping("/send-mail")
	public Boolean sendMail(@RequestParam("email") String email) throws MessagingException {
		
		String l = "http://localhost:8302/api/roca-db-service/rs/db/verifyEmail?email="+email;
		smtpMailSender.send(email, "ROCA Account Activation", "Link : " + l);
		return true;
		
	}
	
	
	@PostMapping("/verifiedEmailId")
	public Integer VerifiedEmail(@RequestParam("email") String email) {
		
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "*/*");
		HttpEntity<String> httpEntity = new HttpEntity<>(email,httpHeaders);
		ResponseEntity<Integer> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/verifyEmail", httpEntity, Integer.class);
		return postForEntity.getBody();
	}

}
