package com.project.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.Model.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {

	public List<Orders> findByOrderStatus(String message);
	
	@Query("SELECT SUM(totalOrderValue) FROM com.project.Model.Orders where orderDate=?1 ")
	public Integer findTotalValueForToday(LocalDate date);
}
