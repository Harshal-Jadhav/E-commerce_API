package com.project.Services;

import java.util.List;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Customer;
import com.project.Model.Orders;
import com.project.Model.Product;

public interface AdminServices {

	// Method to see All the products
	public List<Product> getAllProducts(String key) throws RecordsNotFoundException;

	// Method to add a New Product.
	public List<Product> addNewProduct(String key, Product product) throws RecordsNotFoundException;

	// Method to update stocks of products;
	public Product updateProduct(String key, Integer productId, String name, String des, String brand, Double rating,
			Double mPrice,
			Double sPrice, Integer stock) throws RecordsNotFoundException;

	// Method to see all the orders and their details;
	public List<Orders> SeeAllOrders(String key) throws RecordsNotFoundException;

	// Method to see a particular Order;
	public Orders getOrderByOrderId(String key, Integer orderId) throws RecordsNotFoundException;

	// Method to update status of an order;
	public Orders updateStatusOfOrders(String key, Integer orderId, String message) throws RecordsNotFoundException;

	// Method to get Orders by Customer id;
	public List<Orders> getOrdersByCustomerId(String key, Integer customerId) throws RecordsNotFoundException;

	// Method to get Orders by Customer phone;
	public List<Orders> getOrdersByCustomerPhone(String key, String phone) throws RecordsNotFoundException;

	// Method to get Orders by Customer email
	public List<Orders> getOrdersByCustomerEmail(String key, String email) throws RecordsNotFoundException;

	// GetOrders by Status;
	public List<Orders> getOrdersByStatus(String key, String message) throws RecordsNotFoundException;
	
	//Method to get all Customers;
	public List<Customer> getAllCustomers(String key) throws RecordsNotFoundException;

	// TODO DashBoard Implementation.

	// Method to return Sales made today;
	public Integer salesMadeToday(String key) throws RecordsNotFoundException;

//	// Method to return Sales made Last weel;
//	public Integer salesMadeLastWeek();
//
//	// Method to return Sales made Last month;
//	public Integer salesMageLastMonth();
}
