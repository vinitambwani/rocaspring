package com.eny.roca.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.QueryDao;
import com.eny.roca.db.bean.QueryBean;
import com.eny.roca.db.bean.UserRegistration;

@RestController
@RequestMapping("/rs/db")
public class QueryDBResourceService {
	
	@Autowired
	private QueryDao queryDao;

	@PostMapping("/saveQuery")
	public Boolean query(@RequestBody List<QueryBean> queryBean) {
		return queryDao.saveQueryUser(queryBean) > 0 ? true : false;
	}

	@PostMapping("/getQuery")
	public List<QueryBean> getQuery(@RequestParam String status, @RequestParam Integer userId) {
		return queryDao.getQuery(status, userId);
	}

}
