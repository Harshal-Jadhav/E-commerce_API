package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	public Customer findByPhone(String phone);

	public Customer findByEmail(String email);
}
