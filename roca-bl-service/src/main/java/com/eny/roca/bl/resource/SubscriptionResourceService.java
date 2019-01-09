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

import com.eny.roca.bean.StatusBean;
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
	public Boolean subscriptionAssignment(@RequestBody List<SubscriptionAssignment> subscriptionAssignment) {
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
		List<SubscriptionBean> body = postForEntity.getBody();
		
		return postForEntity.getBody();
	}
	
	@PostMapping("/updatePaceId")
	public Boolean updatePaceId(@RequestBody List<SubscriptionAssignment> subscriptionAssignment) {
		String json = gson.toJson(subscriptionAssignment);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/updatePaceId", httpEntity, Boolean.class);
		return postForEntity.getBody();
		
	}
	
	@PostMapping("/updateAdditionalDocRequired")
	public Boolean updateAdditionalDocRequired(@RequestBody StatusBean statusBean) {
		String json = gson.toJson(statusBean);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/updateAdditionalDocRequired", httpEntity, Boolean.class);
		return postForEntity.getBody();
	}
	
	@PostMapping("/updateStatus")
	public Boolean updateStatus(@RequestBody List<StatusBean> statusBean) {
		String json = gson.toJson(statusBean);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/updateStatus", httpEntity, Boolean.class);
		return postForEntity.getBody();
	}
}
