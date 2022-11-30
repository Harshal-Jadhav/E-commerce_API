package com.project.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Admin;
import com.project.Model.CurrentUserSession;
import com.project.Model.LoginDTO;
import com.project.Repositories.AdminRepo;
import com.project.Repositories.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImpl implements LoginService {
	@Autowired
	private AdminRepo aRepo;
	@Autowired
	private SessionRepo sRepo;

	@Override
	public String LoginToAccount(LoginDTO loginDTO) throws RecordsNotFoundException {
		Admin existingAdmin = aRepo.findByPhone(loginDTO.getMobile());
		if (existingAdmin == null) {
			throw new RecordsNotFoundException("please enter valid mobile number");

		}
		Optional<CurrentUserSession> validAdminSessionOpt = sRepo.findById(existingAdmin.getPhone());
		if (validAdminSessionOpt.isPresent()) {
			throw new RecordsNotFoundException("User already logged in with this number");
		}
		if (existingAdmin.getPassword().equals(loginDTO.getPassword())) {
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession();
			currentUserSession.setKey(key);
			currentUserSession.setMobile(existingAdmin.getPhone());
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
