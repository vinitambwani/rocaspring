package com.eny.roca.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	 @Transactional(readOnly=true)
     public String getLoginDetails() {
		jdbcTemplate.query("select * from Login ", new LoginMapper());
		return null;
	}
	
	

}
