package com.project.Services;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Cart;
import com.project.Model.Customer;
import com.project.Model.Orders;

public interface CustomerServices {

	// Creating an account
	public Customer addNewCustomer(Customer customer) throws LoginException;

	// Method to add product into the cart;
	public Cart addProductToCart(String key, Integer productId, Integer quantity)
			throws RecordsNotFoundException;

	// Method to see Cart Details;
	public Cart seeCart(String key) throws RecordsNotFoundException;

	//Method to make Purchace;
	public List<Orders> makePurchace(String key, Integer paymentId) throws RecordsNotFoundException;

	// Method to see all the orders
	public List<Orders> seeAllOrders(String key) throws RecordsNotFoundException;

	// Method to see a specific orders
	public Orders getOrderById(String key, Integer orderId) throws RecordsNotFoundException;

}
