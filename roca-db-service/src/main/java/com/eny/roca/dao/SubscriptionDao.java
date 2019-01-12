package com.eny.roca.dao;

import java.io.IOException;
import java.util.List;

import com.eny.roca.db.bean.StatusBean;
import com.eny.roca.db.bean.SubscriptionAssignment;
import com.eny.roca.db.bean.SubscriptionAssignmentBean;
import com.eny.roca.db.bean.SubscriptionBean;
import com.eny.roca.db.bean.SubscriptionDocDetails;

public interface SubscriptionDao {

	Integer saveSubscriptionUser(SubscriptionBean subscriptionBean);

	Integer userSubscribed(String emailid);

	SubscriptionBean fetchUserSubscription(String emailId);

	Integer saveSubscriptionAssignment(List<SubscriptionAssignment> subscriptionBean);

 
	List<SubscriptionBean> fetchUserSubscriptionStatus(String emailId, String status, Integer subscriptionId);
 
	Integer updateSubscriptionPaceId(List<SubscriptionAssignment> subscriptionAssignment);

	Integer updateAdditionalDocRequired(StatusBean statusBean);

	Integer updateStatus(List<StatusBean> statusBean);
	
	SubscriptionDocDetails saveFile(SubscriptionBean subscriptionBean) throws IOException;
	
	List<SubscriptionDocDetails> getFile(String fileName) throws IOException;
	
	 SubscriptionBean  fetchSubscriptionDetailsById(Integer subscriptionId);
	 List<SubscriptionAssignmentBean> getSubscriptionAssignments(Integer subscriptionId);

}
