package com.eny.roca.dao;

import java.util.List;

import com.eny.roca.db.bean.QueryAssignment;
import com.eny.roca.db.bean.QueryBean;
import com.eny.roca.db.bean.StatusBean;
import com.eny.roca.db.bean.SubscriptionBean;

public interface QueryDao {

	Integer saveQueryUser(List<QueryBean> queryBean);

	List<QueryBean> getQuery(String status, Integer userId);

	Integer saveQueryAssignment(List<QueryAssignment> queryAssignment);

	List<QueryAssignment> getQueryAssignment(Integer queryId);

	QueryBean fetchQueryById(Integer queryId);

	Integer updateStatus(List<StatusBean> statusBean);

	List<QueryBean> fetchQueryStatus(String status);

}
