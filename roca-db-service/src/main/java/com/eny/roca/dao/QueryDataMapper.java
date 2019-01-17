package com.eny.roca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eny.roca.db.bean.QueryBean;

public class QueryDataMapper implements RowMapper<QueryBean> {
	 
		@Override
		public QueryBean mapRow(ResultSet rs, int arg1) throws SQLException {
			QueryBean queryBean = new QueryBean();
			queryBean.setQueryCaption(rs.getString("QueryCaption"));
			queryBean.setQueryFact(rs.getString("QueryFact"));
			queryBean.setCategory(rs.getString("Category"));
			queryBean.setFinancialYear(rs.getString("FinancialYear"));
			queryBean.setStatus(rs.getString("Status"));
			queryBean.setIsAssigned(Integer.parseInt(rs.getString("IsAssigned")));
			queryBean.setInScope(Integer.parseInt(rs.getString("InScope")));
			queryBean.setComment(rs.getString("Comments"));
			queryBean.setUserId(Integer.parseInt(rs.getString("UserId")));
			
			queryBean.setQuestionDescription(rs.getString("QuestionDescription"));
			queryBean.setQueryId(Integer.parseInt(rs.getString("QueryId")));
			queryBean.setQueStatus(rs.getString("Status"));
			queryBean.setModifiedQuestionDescription(rs.getString("ModifiedQuestionDescription"));
			queryBean.setIsQuestionModified(Integer.parseInt(rs.getString("IsQuestionModified")));
			queryBean.setQueComment(rs.getString("Comments"));
			queryBean.setAnswer(rs.getString("Answer"));
			
			return queryBean;
		}
}
