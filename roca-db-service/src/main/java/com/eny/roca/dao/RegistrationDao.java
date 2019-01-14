package com.eny.roca.dao;

import java.util.List;

import com.eny.roca.db.bean.UserRegistration;

public interface RegistrationDao {

	Integer setMasterData(UserRegistration userRegistration);

	List<UserRegistration> getRegistrationData();

	Boolean validateEmailId(String emailId);

	Integer verifyEmailId(String email);
	
	Integer validateMobileNo(Long mobileNo);

	Integer sendOtp(String mobileNo);

	Integer verifyOtp(String mobileNo, Integer otp);
}
