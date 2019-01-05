package com.eny.roca.dao;

import com.eny.roca.db.bean.SubscriptionAssignment;
import com.eny.roca.db.bean.SubscriptionBean;

public interface SubscriptionDao {

	Integer saveSubscriptionUser(SubscriptionBean subscriptionBean);

	Integer userSubscribed(String emailid);

	SubscriptionBean fetchUserSubscription(String emailId);

	Integer saveSubscriptionAssignment(SubscriptionAssignment subscriptionBean);

	Integer updateSubscriptionPaceId(String paceId, Integer id, String email);

	Integer updateAdditionalDocRequired(Integer docRequired, Integer id, String email);

	Integer updateStatus(Integer id, String action, String condition);

}
