package com.eny.roca.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.eny.roca.db.bean.QueryBean;

@Repository
public class QueryDaoImpl implements QueryDao {
	
	private static final int IS_ACTIVE = 1;
	@Autowired
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer saveQueryUser(List<QueryBean> queryPojo, Boolean isSubmit) {
		int update2 = 0;
		for(QueryBean queryBean : queryPojo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String query =  "INSERT INTO  rocausers.Query "
				+ "				 (QueryCaption, QueryFact, Category, FinancialYear, Status, isAssigned, inScope, comments, UserId) "
				+ "				 VALUES (:queryCaption, :queryFact, :Category, :financialYear, :status, :isAssigned, :inScope, :comment, :userId)";
		
		if(isSubmit) {
			queryBean.setStatus("New");
			queryBean.setQueStatus("New");
		} else {
			queryBean.setStatus("Saved");
			queryBean.setQueStatus("Saved");
		}
			
		SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(queryBean);
		int update = namedParameterJdbcTemplate.update(query, fileParameters, keyHolder);
		int id = keyHolder.getKey().intValue();
		if(update == 1) {
		String query2 =  "INSERT INTO  rocausers.Question "
				+ "				 (QuestionDescription, QueryId, Status, ModifiedQuestionDescription, isQuestionModified, comments, Answer) "
				+ "				 VALUES (:questionDescription, :queryId, :status, :modifiedQuestionDescription, :isQuestionModified, :comments, :answer)";
		
		Map<String, Object> map = new HashMap<>(1);
		map.put("questionDescription", queryBean.getQuestionDescription());
		map.put("queryId", id);
		map.put("status", queryBean.getQueStatus());
		map.put("modifiedQuestionDescription", queryBean.getModifiedQuestionDescription());
		map.put("isQuestionModified", queryBean.getIsQuestionModified());
		map.put("comments", queryBean.getQueComment());
		map.put("answer", queryBean.getAnswer());
		update2 = namedParameterJdbcTemplate.update(query2,map);
		}
		}
		return update2;
	}

}
