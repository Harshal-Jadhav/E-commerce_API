package com.project.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Admin;
import com.project.Model.CurrentUserSession;
import com.project.Model.Customer;
import com.project.Model.Orders;
import com.project.Model.Product;
import com.project.Repositories.AdminRepo;
import com.project.Repositories.CartRepo;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.OrderDetailsRepo;
import com.project.Repositories.OrdersRepo;
import com.project.Repositories.PaymentRepo;
import com.project.Repositories.ProductRepo;
import com.project.Repositories.SessionRepo;

public class AdminServiceImpl implements AdminServices {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private OrdersRepo orderRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderDetailsRepo odRepo;
	@Autowired
	private PaymentRepo payRepo;
	@Autowired
	private SessionRepo sRepo;
	@Autowired
	private AdminRepo aRepo;

	@Override
	public List<Product> getAllProducts(String key) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");

		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		
		List<Product> productList = productRepo.findAll();

		if (productList.size() == 0)
			throw new RecordsNotFoundException("Products Not Found");

		return productList;
	}

	@Override
	public List<Product> addNewProduct(String key, Product product) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		Product savedProduct = productRepo.save(product);

		List<Product> productList = getAllProducts(key);
		return productList;
	}

	@Override
	public Product updateProduct(String key, Integer productId, String name, String des, String brand, Double rating,
			Double mPrice,
			Double sPrice, Integer stock) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		// Getting the product to Update.
		Optional<Product> product = productRepo.findById(productId);

		if (!product.isPresent())
			throw new RecordsNotFoundException("Invalid Product Id");

		Product p = product.get();

		if (name != null)
			p.setProductName(name);
		if (des != null)
			p.setProductDescription(des);
		if (brand != null)
			p.setProductBrand(brand);
		if (rating != null)
			p.setRating(rating);
		if (mPrice != null)
			p.setProductMarketPrice(mPrice);
		if (sPrice != null)
			p.setProductSellingPrice(sPrice);
		if (stock != null)
			p.setStock(stock);

		Product updated = productRepo.save(p);

		return updated;
	}

	@Override
	public List<Orders> SeeAllOrders(String key) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		List<Orders> orderList = orderRepo.findAll(Sort.by(Sort.Direction.DESC, "Id"));

		if (orderList.size() == 0)
			throw new RecordsNotFoundException("No Orders found");

		return orderList;
	}

	@Override
	public Orders getOrderByOrderId(String key, Integer orderId) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		Optional<Orders> order = orderRepo.findById(orderId);

		if (!order.isPresent())
			throw new RecordsNotFoundException("Invalid Order id");

		return order.get();
	}

	@Override
	public Orders updateStatusOfOrders(String key, Integer orderId, String message) throws RecordsNotFoundException {
		
		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		Optional<Orders> order = orderRepo.findById(orderId);
		
		if (!order.isPresent())
			throw new RecordsNotFoundException("Invali Order Id");
		
		order.get().setOrderStatus(message);

		Orders updated = orderRepo.save(order.get());
		return updated;
	}


	@Override
	public List<Orders> getOrdersByCustomerId(String key, Integer customerId) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		Optional<Customer> customer = customerRepo.findById(customerId);
		
		if (!customer.isPresent())
			throw new RecordsNotFoundException("Invalid Customer Id.");

		List<Orders> ordersList = new ArrayList<>(customer.get().getOrderList());
		Collections.reverse(ordersList);

		return ordersList;

	}


	@Override
	public List<Orders> getOrdersByCustomerPhone(String key, String phone) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");

		Customer customer = customerRepo.findByPhone(phone);
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		if (customer == null)
			throw new RecordsNotFoundException("Customer not found with given Id");
		
		List<Orders> ordersList = new ArrayList<>(customer.getOrderList());
		Collections.reverse(ordersList);

		return ordersList;
	}



	@Override
	public List<Orders> getOrdersByCustomerEmail(String key, String email) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		Customer customer = customerRepo.findByEmail(email);
		
		if (customer == null)
			throw new RecordsNotFoundException("Customer not found with given Id");

		List<Orders> ordersList = new ArrayList<>(customer.getOrderList());
		Collections.reverse(ordersList);

		return ordersList;
	}


	@Override
	public List<Orders> getOrdersByStatus(String key, String message) throws RecordsNotFoundException {
		
		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		List<Orders> orderList = orderRepo.findByOrderStatus(message);
		
		if (orderList.size() == 0)
			throw new RecordsNotFoundException("No Orders Found");
		
		return orderList;

	}


	@Override
	public List<Customer> getAllCustomers(String key) throws RecordsNotFoundException {

		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		List<Customer> customerList = customerRepo.findAll();

		if (customerList.size() == 0)
			throw new RecordsNotFoundException("No Customers Found");

		return customerList;
	}


	@Override
	public Integer salesMadeToday(String key) throws RecordsNotFoundException {
		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
		Admin admin = aRepo.findByPhone(activeSession.getMobile());

		if (admin == null)
			throw new RecordsNotFoundException("Access restricted.");
		Integer val = orderRepo.findTotalValueForToday(LocalDate.now());
		
		return null;
	}

//	@Override
//	public Integer salesMadeLastWeek() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer salesMageLastMonth() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
