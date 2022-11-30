package com.project.Services;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.LoginDTO;

public interface LoginService {

	public String LoginToAccount(LoginDTO loginDTO) throws RecordsNotFoundException;

	public String LogOutFromAccount(String Key) throws RecordsNotFoundException;
	
}
