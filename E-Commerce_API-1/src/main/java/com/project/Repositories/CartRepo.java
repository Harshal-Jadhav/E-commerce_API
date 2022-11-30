package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
