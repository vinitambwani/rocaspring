package com.eny.roca.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.LoginDao;

@RestController
@RequestMapping("/rs/db")
public class DBServiceResource {
		
	@Autowired
	private LoginDao loginDao;
			 
	
		@GetMapping("/test")
		public String getDummyString() {
		String str=	loginDao.getLoginDetails();
			return "Hello:world";
		}
		
}
