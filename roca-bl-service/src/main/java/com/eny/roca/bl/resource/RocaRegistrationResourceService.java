package com.eny.roca.bl.resource;

import java.util.List;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
	
	@Autowired
	private Gson gson;
	
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
	
	@GetMapping("/validateEmailId")
	public String validateEmail(@RequestParam String email) {

		String val = null;
		try {
			val = Boolean.toString(ValidateEmail.isEmailValid(email));
		} catch (Exception e) {
			val = "invalid Email";
		}
		return val;
	}
	
	@GetMapping("/send-mail")
	public Boolean sendMail(@RequestParam String email) throws MessagingException {
		
		String l = "http://localhost:8302/api/roca-bl-service/rs/bl/verifiedEmailId?email="+email;
		smtpMailSender.send(email, "ROCA Account Activation", "Link : " + l);
		return true;
		
	}
	
	
	@GetMapping("/verifiedEmailId")
	public Integer VerifiedEmail(@RequestParam(value = "email") String email) {
		
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(email,httpHeaders);
		ResponseEntity<Integer> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/verifyEmail", httpEntity, Integer.class);
		return postForEntity.getBody();
	}
	
	@GetMapping("/validateMobileNo")
	public Integer validateMobileNo(@RequestParam(value = "mobileNo") Long mobileno) {
		String json = gson.toJson(mobileno);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<Integer> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/validateMobileNo", httpEntity, Integer.class);
		return postForEntity.getBody();
	}
	
	@GetMapping("/sendOtp")
	public Integer updatePaceId(@RequestParam String mobileNo) {
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(mobileNo,httpHeaders);
		ResponseEntity<Integer> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/sendOtp", httpEntity, Integer.class);
		return postForEntity.getBody();
		
	}
	
	@GetMapping("/verifyOtp")
	public Integer verifyOtp(@RequestParam String mobileNo, @RequestParam Integer otp) {
		String json = gson.toJson(otp);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("mobileNo" ,mobileNo); 
		map.add("otp", json);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<Integer> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/verifyOtp", request, Integer.class);
		return postForEntity.getBody();
		
	}

}
