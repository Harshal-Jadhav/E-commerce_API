package com.project.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.CurrentUserSession;
import com.project.Model.Customer;
import com.project.Model.LoginDTO;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerLoginServiceImpl implements LoginService {
	@Autowired
	private CustomerRepo cRepo;
	@Autowired
	private SessionRepo sRepo;

	@Override
	public String LoginToAccount(LoginDTO loginDTO) throws RecordsNotFoundException {
		Customer existingCustomer = cRepo.findByPhone(loginDTO.getMobile());
		if (existingCustomer == null) {
			throw new RecordsNotFoundException("please enter valid mobile number");

		}
		Optional<CurrentUserSession> validCustomerSessionOpt = sRepo.findById(existingCustomer.getPhone());
		if (validCustomerSessionOpt.isPresent()) {
			throw new RecordsNotFoundException("User already logged in with this number");
		}
		if (existingCustomer.getPassword().equals(loginDTO.getPassword())) {
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession();
			currentUserSession.setKey(key);
			currentUserSession.setMobile(existingCustomer.getPhone());
			sRepo.save(currentUserSession);
			return key;
		} else {
			throw new RecordsNotFoundException("please enter valid password");
		}
	}

	@Override
	public String LogOutFromAccount(String Key) throws RecordsNotFoundException {
		CurrentUserSession currentUserSession = sRepo.findByKey(Key);
		if (currentUserSession == null) {
			throw new RecordsNotFoundException("user not logged in with this number");
		} else {
			sRepo.delete(currentUserSession);
			return "logged out !";
		}
	}

}
