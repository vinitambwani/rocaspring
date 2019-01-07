package com.eny.roca.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eny.roca.dao.RegistrationDao;
import com.eny.roca.db.bean.UserRegistration;

@RestController
@RequestMapping("/rs/db")
public class RocaRegistrationDBServiceResource {

	@Autowired
	private RegistrationDao registrationDao;
	
	@GetMapping("/getRegister")
	public List<UserRegistration> registerNewUser() {
		return registrationDao.getRegistrationData();
	}

	@PostMapping("/setRegister")
	public Integer registerNewUser(@RequestBody UserRegistration userRegistration) {
		return registrationDao.setMasterData(userRegistration);
	}
	
	@GetMapping("/validateEmail")
	public Boolean validateEmailId(@RequestParam String email) {
		return registrationDao.validateEmailId(email);
	}
	
	@PostMapping("/verifyEmail")
	public Integer verifyEmailId(@RequestBody String email) {
		return registrationDao.verifyEmailId(email);
	}
	
}
