package com.eny.roca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.eny.roca.db.bean.MasterData;
import com.eny.roca.db.bean.SubscriptionAssignmentBean;

public class SubscriptionAssignmentMapper implements RowMapper<SubscriptionAssignmentBean> {

	@Override
	public SubscriptionAssignmentBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		//select subscriptionid,fromassignment,toassignment,comments from rocaserviceteam.SubscriptionAssignment where subscriptionid = 15 AND IsActive = 1 order by createdon
		SubscriptionAssignmentBean subscriptionAssignmentBean = new SubscriptionAssignmentBean();
		subscriptionAssignmentBean.setComments(rs.getString("comments"));
		subscriptionAssignmentBean.setFromAssignment(rs.getString("fromassignment"));
		subscriptionAssignmentBean.setToAssignment(rs.getString("toassignment"));
		subscriptionAssignmentBean.setSubscriptionId(rs.getInt("subscriptionid"));
		return subscriptionAssignmentBean;
	}
}
