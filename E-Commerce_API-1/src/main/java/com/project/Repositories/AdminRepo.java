package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

	public Admin findByPhone(String phone);
}
