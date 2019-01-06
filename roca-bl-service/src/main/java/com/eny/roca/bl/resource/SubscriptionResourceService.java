package com.eny.roca.bl.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eny.roca.bean.SubscriptionAssignment;
import com.eny.roca.bean.SubscriptionBean;
import com.eny.roca.bean.UserBean;
import com.google.gson.Gson;

@RestController
@RequestMapping("/rs/bl")
public class SubscriptionResourceService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Gson gson;
	
	@PostMapping("/subscribe")
	public Boolean subscribe(@RequestBody SubscriptionBean subscriptionBean) {
		String json = gson.toJson(subscriptionBean);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/subscribe", httpEntity, Boolean.class);
		return postForEntity.getBody();
		
	}
	
	@PostMapping("/usersubscribed/{emailId}")
	public @ResponseBody Integer userSubscrioed(@PathVariable("emailId") String emailId) {
		
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>("",httpHeaders);
		String url = "http://roca-db-service/rs/db/usersubscribed/"+emailId;
		ResponseEntity<Integer> postForEntity = restTemplate.postForEntity(url,httpEntity, Integer.class);
		return postForEntity.getBody();
	}
	
	@PostMapping("/fetchUserSubscription")
	public SubscriptionBean fetchUserSubscription(@RequestBody UserBean userBean) {
		String json = gson.toJson(userBean);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<SubscriptionBean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/fetchUserSubscription",httpEntity, SubscriptionBean.class);
		return postForEntity.getBody();
	}
	
	@PostMapping("/setSubscriptionAssignment")
	public Boolean subscriptionAssignment(@RequestBody SubscriptionAssignment subscriptionAssignment) {
		String json = gson.toJson(subscriptionAssignment);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/subscriptionAssignment", httpEntity, Boolean.class);
		return postForEntity.getBody();
		
	}
	
	@PostMapping("/fetchSubscriptionStatus")
	public List<SubscriptionBean> fetchUserSubscriptionStatus(@RequestBody UserBean userBean) {
		String json = gson.toJson(userBean);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<List> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/fetchUserSubscriptionStatus",httpEntity, List.class);
		return postForEntity.getBody();
	}
	
	@PostMapping("/updatePaceId")
	public Boolean updatePaceId(@RequestParam String paceId, @RequestParam Integer id, @RequestParam String email) {
		String json = gson.toJson(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("paceId" ,paceId); 
		map.add("id", json);
		map.add("email", email);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/updatePaceId", request, Boolean.class);
		return postForEntity.getBody();
		
	}
	
	@PostMapping("/updateAdditionalDocRequired")
	public Boolean updateAdditionalDocRequired(@RequestParam Integer docRequired, @RequestParam Integer id, @RequestParam String email) {
		String docrequired = gson.toJson(docRequired);
		String json = gson.toJson(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("docRequired" ,docrequired); 
		map.add("id", json);
		map.add("email", email);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/updateAdditionalDocRequired", request, Boolean.class);
		return postForEntity.getBody();
		
	}
	
	@PostMapping("/updateStatus")
	public Boolean updateStatus(@RequestParam Integer id, @RequestParam String action, @RequestParam String condition) {
		String json = gson.toJson(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("id" ,json);
		map.add("action" ,action);
		map.add("condition" ,condition);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/updateStatus", request, Boolean.class);
		return postForEntity.getBody();
	}
}
