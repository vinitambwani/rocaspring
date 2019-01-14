package com.eny.roca.bl.resource;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.eny.roca.bean.RegisteredUserResponse;
import com.eny.roca.bean.UserRegistration;
import com.eny.roca.bl.services.SmtpMailSender;
import com.eny.roca.bl.services.ValidateEmail;
import com.eny.roca.helper.AzureAuthenticationHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/rs/bl")
public class RocaRegistrationResourceService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SmtpMailSender smtpMailSender;
	
	
	@Value("${roca.registration.ad.tenantid}")
	private String tenantId; 
	
	@Value("${roca.registration.ad.clientid}")
	private String clientid; 
	
	
	@Value("${roca.registration.ad.responseType}")
	private String responseType; 
	
	
	@Value("${roca.registration.ad.responseMode}")
	private String responseMode; 
	
	
	@Value("${roca.registration.ad.scope}")
	private String userScope; 
	
	
	@Value("${roca.registration.ad.clientSecret}")
	private String clientSecret; 
	
	@Autowired
	private AzureAuthenticationHelper azureAuthenticationHelper;
	
	
	
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
		Integer body = postForEntity.getBody();
		// once Email is verified lets proceed for AD Registration.
		String url = null;
		if(body>0) {
			//get the registation id of user to send it into the URL
			  Optional<UserRegistration> userRegistrationDetails = getUserRegistration().stream().filter(u->u.getEmailId().equalsIgnoreCase(email)).findFirst();
			  Integer registrationId = userRegistrationDetails.isPresent() ? userRegistrationDetails.get().getRegisrationId() : null;
			//Need to prepare URL to hit on the Browser..
			if(null!=registrationId) {
				StringBuffer sbf = new StringBuffer();
				sbf.append("https://login.microsoftonline.com/")
				.append(tenantId).append("/oauth2/v2.0/authorize?client_id=")
				.append(clientid)
				.append("&response_type=")
				.append(responseType)
				.append("&response_mode=")
				.append(responseMode)
				.append("&scope=")
				.append(userScope)
				.append("&state=")
				.append(registrationId);
				url = sbf.toString();
			}else {
				return 0;
			}
			
		}
		
		
		return postForEntity.getBody();
	}
	
	@GetMapping("/user/regisration/ad")
	public String registerUserWithAzuer(@QueryParam("code") String code,@QueryParam("state") String state) {
		
		UserRegistration userRegistration = new UserRegistration();
		userRegistration.setEmailId("m3raj10111@gmail.com");
		userRegistration.setLegalEntityName("ABC11");
 		
	  //  Optional<UserRegistration> userRegistrationDetails = getUserRegistration().stream().filter(u-> u.getRegisrationId() == (Integer.valueOf(state))).findFirst();
	  //  UserRegistration userRegistration = userRegistrationDetails.isPresent() ? userRegistrationDetails.get() : null;
	//	String token = azureAuthenticationHelper.getTokenforRegistration(code,state);
		RegisteredUserResponse registeredUserResponse = azureAuthenticationHelper.getUserPrincipalName("",userRegistration);
		return null;
	}
	

}
