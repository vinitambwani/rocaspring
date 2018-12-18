package com.eny.roca.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.db.bean.RegistrationBean;

@RestController
@RequestMapping("/rs/db")
public class RocaRegistrationDBServiceResource {

	@PostMapping("/register")
	public int registerNewUser(@RequestBody RegistrationBean registrationBean) {
		System.out.println(registrationBean.toString());
		return 123;
	}
	
}
