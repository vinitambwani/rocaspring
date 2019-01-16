package com.eny.roca.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.QueryDao;
import com.eny.roca.db.bean.QueryBean;

@RestController
@RequestMapping("/rs/db")
public class QueryDBResourceService {
	
	@Autowired
	private QueryDao queryDao;

	@PostMapping("/saveQuery")
	public Boolean query(@RequestBody List<QueryBean> queryBean, @RequestParam Boolean isSubmit) {
		return queryDao.saveQueryUser(queryBean, isSubmit) > 0 ? true : false;
	}

	
}
