package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
