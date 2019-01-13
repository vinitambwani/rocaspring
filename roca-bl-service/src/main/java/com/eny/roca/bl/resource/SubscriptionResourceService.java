package com.eny.roca.bl.resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.eny.roca.bean.StatusBean;
import com.eny.roca.bean.SubscriptionAssignment;
import com.eny.roca.bean.SubscriptionBean;
import com.eny.roca.bean.SubscriptionRequestBean;
import com.eny.roca.bean.UserBean;
import com.eny.roca.rocablservice.config.RocaBlDbServiceConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/rs/bl")
public class SubscriptionResourceService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Gson gson;
	
	@Autowired
	private RocaBlDbServiceConfig rocaBlDbServiceConfig;
	
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
		List<SubscriptionBean> subscriptionBeans = new ArrayList<>();
		if(userBean.getMultipleStatus() != null && userBean.getMultipleStatus().size() > 0) {
			for(String stuatus : userBean.getMultipleStatus()) {
				userBean.setStatus(stuatus);
				String json = gson.toJson(userBean);
				HttpHeaders httpHeaders = new  HttpHeaders();
				httpHeaders.set("content-type", "application/json");
				HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
				ResponseEntity<List> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/fetchUserSubscriptionStatus",httpEntity, List.class);
				subscriptionBeans.addAll(postForEntity.getBody());
			}
		} else {
			String json = gson.toJson(userBean);
			HttpHeaders httpHeaders = new  HttpHeaders();
			httpHeaders.set("content-type", "application/json");
			HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
			ResponseEntity<List> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/fetchUserSubscriptionStatus",httpEntity, List.class);
			subscriptionBeans = postForEntity.getBody();			
		}
		
		return subscriptionBeans;
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
	
	@SuppressWarnings("unchecked")
	@PostMapping(path="/uploadFile")
	public ResponseEntity<Object> uploadFile(@ModelAttribute SubscriptionBean requestBean){
		final GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED,MediaType.MULTIPART_FORM_DATA,MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8));
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		SubscriptionRequestBean subscriptionRequestBean = new SubscriptionRequestBean();
		subscriptionRequestBean.setEmail(requestBean.getEmail());
		 MultipartFile multiFile = requestBean.getDocData();
		 File tempFile = null;
		    try {
		        String extension = "." + multiFile.getOriginalFilename();
		        tempFile = File.createTempFile("temp", extension);
		        
		        multiFile.transferTo(tempFile);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    FileSystemResource fileSystemResource = new FileSystemResource(tempFile);
		    map.add("file", fileSystemResource);
		    
		    
		    subscriptionRequestBean.setDocExtention(requestBean.getDocExtention());
		    subscriptionRequestBean.setDocName(requestBean.getDocName());
		    subscriptionRequestBean.setDocType(requestBean.getDocType());
		    subscriptionRequestBean.setIs_valid_doc(requestBean.getIs_valid_doc());
		    String jsonBean = gson.toJson(subscriptionRequestBean);
		    map.add("bean", jsonBean);
		
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map,httpHeaders);
		
		ResponseEntity<Object> postForEntity = restTemplate.exchange("http://roca-db-service/rs/db/uploadFile",HttpMethod.POST,httpEntity, Object.class);
		return new ResponseEntity<Object>(postForEntity.getBody(),HttpStatus.OK);
	}   			
	
	@GetMapping(path="/downloadFile/{fileName:.+}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName){
		
		String url = rocaBlDbServiceConfig.getUrl()+"downloadFile";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("fileName", fileName);
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED,MediaType.MULTIPART_FORM_DATA,MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8));
		
		HttpEntity<String> httpEntity = new HttpEntity<>(fileName,httpHeaders);
		ResponseEntity<byte[]> getForEntity = restTemplate.exchange(builder.toUriString(),HttpMethod.GET,httpEntity, byte[].class);
		return new ResponseEntity<byte[]>(getForEntity.getBody(),HttpStatus.OK);
	}
	
	@PostMapping("/fetchSubscriptionById")
	public SubscriptionBean fetchSubscriptionById(@RequestBody UserBean userBean) {
		String json = gson.toJson(userBean);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<SubscriptionBean> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/fetchSubscriptionById", httpEntity, SubscriptionBean.class);
		return postForEntity.getBody();
	}
	
	@PostMapping("/fetchSubscriptionAssignmentById")
	public List<SubscriptionAssignment> fetchSubscriptionAssignmentById(@RequestBody UserBean userBean) {
		String json = gson.toJson(userBean);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<List> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/fetchSubscriptionAssignmentById", httpEntity, List.class);
		return postForEntity.getBody();
	}
	
	@GetMapping("/validateSubMobileNo")
	public Integer validateMobileNo(@RequestParam(value = "mobileNo") Long mobileno) {
		String json = gson.toJson(mobileno);
		HttpHeaders httpHeaders = new  HttpHeaders();
		httpHeaders.set("content-type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
		ResponseEntity<Integer> postForEntity = restTemplate.postForEntity("http://roca-db-service/rs/db/validateSubscriptionMobileNo", httpEntity, Integer.class);
		return postForEntity.getBody();
	}
}
