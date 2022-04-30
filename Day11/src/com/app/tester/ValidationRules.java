package com.app.tester;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.app.customer.Customer;
import com.app.customer.ServicePlan;
import com.app.exception.CustomerException;

public class ValidationRules {

	public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	public static Date checkDate;

	static {
		try {
			checkDate = sdf.parse("01-01-1995");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static String validateEmailAddress(String testEmail) throws CustomerException{
		if(testEmail.contains("@")) {
			if((testEmail.endsWith(".com")) || (testEmail.endsWith(".org")) ) {
				return testEmail;
			}
		}
		throw new CustomerException("Email Invalid!");
	}

	public static String validatePassword(String testPassword) throws CustomerException{
		if((testPassword.length()>=4) && (testPassword.length()<=10)) {
			if(testPassword.matches(".*[#@$%*]+.*")) {
				return testPassword;
			}
		}
		throw new CustomerException("Password Invalid");
	}

	public static Date validateDateOfBirth(String testDob) throws CustomerException, ParseException{
		Date testDOB = sdf.parse(testDob);
		if(testDOB.before(checkDate)) {
			return testDOB;
		}
		throw new CustomerException("Invalid Date Of Birth");
	}

	public static ServicePlan validateServicePlan(String testPlan) throws CustomerException{
		if(ServicePlan.valueOf(testPlan).name().equals(testPlan)) {
			return ServicePlan.valueOf(testPlan);
		}
		throw new CustomerException("Invalid Service Plan");
	}

	public static void checkForDuplicateCustomer(Date checkDOB, String checkEmail, 
			ArrayList<Customer> customerList) throws CustomerException{

		if(customerList.size() == 0) {
			return;
		}else {
			for(int i=0; i<customerList.size(); i++) {
				if((customerList.get(i).getEmail().equals(checkEmail)) && 
						(customerList.get(i).getDob().compareTo(checkDOB)==0)) {
					
						throw new CustomerException("Duplicate Customer Entry!");	
				}
			}
		}
	}
}