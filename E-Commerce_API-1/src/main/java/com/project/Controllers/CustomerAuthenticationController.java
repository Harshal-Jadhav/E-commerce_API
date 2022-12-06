package com.project.Controllers;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Customer;
import com.project.Model.LoginDTO;
import com.project.Services.CustomerServices;
import com.project.Services.LoginService;

@RestController
@RequestMapping("/customer")
public class CustomerAuthenticationController {
	@Autowired
	@Qualifier(value = "customer")
	private LoginService lService;

	@Autowired
	private CustomerServices uService;

	@PostMapping("/login")
	public ResponseEntity<String> loginIntoAccountHandler(@RequestBody LoginDTO login)
			throws LoginException, RecordsNotFoundException {
		String str = lService.LoginToAccount(login);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logoutFromAccountHandler(@RequestParam String key)
			throws LoginException, RecordsNotFoundException {
		String str = lService.LogOutFromAccount(key);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<Customer> registerAccountHandler(@RequestBody Customer customer) throws LoginException {
		Customer cus = uService.addNewCustomer(customer);
		return new ResponseEntity<Customer>(cus, HttpStatus.OK);
	}
}
