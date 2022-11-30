package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

}
