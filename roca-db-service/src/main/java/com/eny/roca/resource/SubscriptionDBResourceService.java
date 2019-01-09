package com.eny.roca.resource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.MasterDataDao;
import com.eny.roca.dao.SubscriptionDao;
import com.eny.roca.db.bean.StatusBean;
import com.eny.roca.db.bean.SubscriptionAssignment;
import com.eny.roca.db.bean.SubscriptionBean;
import com.eny.roca.db.bean.UserBean;

 

@RestController
@RequestMapping("/rs/db")
public class SubscriptionDBResourceService {
	
	@Autowired
	private SubscriptionDao subscriptionDao;
	
	@Autowired
	private MasterDataDao masterDataDao;
	
	@PostMapping("/subscribe")
	public Boolean subscribe(@RequestBody SubscriptionBean subscriptionBean) {
		return subscriptionDao.saveSubscriptionUser(subscriptionBean) > 0 ? true : false;
	}
	
	@PostMapping("/usersubscribed/{emailId}")
	public Integer userSubscribed(@PathVariable("emailId") String emailid) {
		return subscriptionDao.userSubscribed(emailid);
	}
	
	//fetchUserSubscription
	@PostMapping("/fetchUserSubscription")
	public SubscriptionBean fetchUserSubscription(@RequestBody UserBean userBean) {
		return subscriptionDao.fetchUserSubscription(userBean.getEmailId());
	}
	@Transactional(rollbackFor=Exception.class)
	@PostMapping("/subscriptionAssignment")
	public Boolean subscriptionAssignment(@RequestBody List<SubscriptionAssignment> subscriptionAssignment) {
		return subscriptionDao.saveSubscriptionAssignment(subscriptionAssignment) > 0 ? true : false;
	}
	

	//Status
	@PostMapping("/fetchUserSubscriptionStatus")
	public List<SubscriptionBean> fetchUserSubscriptionStatus(@RequestBody UserBean userBean) {
		List<SubscriptionBean>  subscriptionBeans = getSubscriptionStatus(userBean);
				subscriptionBeans.stream().forEach(s -> s.setIndustryName(getIndustryNames(s.getIndustryId())));
				return subscriptionBeans;
	}

	private String getIndustryNames(String industryId) {
		List<Object> industryName = masterDataDao.getIndustryName(industryId);
		List<String> collect = industryName.stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList()); 
		return collect.stream().collect(Collectors.joining(","));
		 
	}

	 

	private List<SubscriptionBean> getSubscriptionStatus(UserBean userBean) {
		return subscriptionDao.fetchUserSubscriptionStatus(userBean.getEmailId(),userBean.getStatus(),userBean.getSubscriptionId());
	}
	
	
	@Transactional(rollbackFor=Exception.class)
	@PostMapping("/updatePaceId")
	public Boolean updatePaceId(@RequestBody List<SubscriptionAssignment> subscriptionAssignment) {
		return subscriptionDao.updateSubscriptionPaceId(subscriptionAssignment) > 0 ? true : false;
	}
	
	@PostMapping("/updateAdditionalDocRequired")
	public Boolean updateAdditionalDocRequired(@RequestBody StatusBean statusBean) {
		return subscriptionDao.updateAdditionalDocRequired(statusBean) > 0 ? true : false;
	}
	
	@PostMapping("/updateStatus")
	public Boolean updateStatus(@RequestBody List<StatusBean> StatusBean) {
		return subscriptionDao.updateStatus(StatusBean) > 0 ? true : false;
	}
}
