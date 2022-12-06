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
import com.project.Model.LoginDTO;
import com.project.Services.AdminServices;
import com.project.Services.LoginService;

@RestController
@RequestMapping("/admin")
public class AdminAuthenticationController {

	@Autowired
	@Qualifier(value = "admin")
	private LoginService lService;

	@Autowired
	private AdminServices uService;

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

}
