package com.eny.roca.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.SubscriptionDao;
import com.eny.roca.db.bean.SubscriptionAssignment;
import com.eny.roca.db.bean.SubscriptionBean;
import com.eny.roca.db.bean.UserBean;

 

@RestController
@RequestMapping("/rs/db")
public class SubscriptionDBResourceService {
	
	@Autowired
	private SubscriptionDao subscriptionDao;
	
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
	
	@PostMapping("/subscriptionAssignment")
	public Boolean subscriptionAssignment(@RequestBody SubscriptionAssignment subscriptionAssignment) {
		return subscriptionDao.saveSubscriptionAssignment(subscriptionAssignment) > 0 ? true : false;
	}
	
	@PostMapping("/updatePaceId")
	public Boolean updatePaceId(@RequestParam String paceId, @RequestParam Integer id, @RequestParam String email) {
		return subscriptionDao.updateSubscriptionPaceId(paceId, id, email) > 0 ? true : false;
	}
	
	@PostMapping("/updateAdditionalDocRequired")
	public Boolean updateAdditionalDocRequired(@RequestParam Integer docRequired, @RequestParam Integer id, @RequestParam String email) {
		return subscriptionDao.updateAdditionalDocRequired(docRequired, id, email) > 0 ? true : false;
	}
	
	@PostMapping("/updateStatus")
	public Boolean updateStatus(@RequestParam Integer id, @RequestParam String action, @RequestParam String condition) {
		return subscriptionDao.updateStatus(id, action, condition) > 0 ? true : false;
	}
}
