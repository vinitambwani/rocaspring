package com.eny.roca.dao;

import java.util.List;

import com.eny.roca.db.bean.QueryBean;

public interface QueryDao {

	Integer saveQueryUser(List<QueryBean> queryBean, Boolean isSubmit);
}
