package com.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Admin;
import com.project.Model.Customer;
import com.project.Model.Orders;
import com.project.Model.Product;
import com.project.Services.AdminServices;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminServices adminService;

	@PostMapping("/add-admin")
	public ResponseEntity<Admin> add_new_admin(@RequestParam String key, @RequestBody Admin ad) {
		Admin admin = adminService.addNewAdmin(ad);
		return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
	}

	@GetMapping("/all-products/")
	public ResponseEntity<List<Product>> get_all_products(@RequestParam String key) throws RecordsNotFoundException{
		List<Product> productList = adminService.getAllProducts(key);
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}

	@PostMapping("/add-product")
	public ResponseEntity<List<Product>> add_new_Product(@RequestBody Product product, @RequestParam String key)
			throws RecordsNotFoundException {
		List<Product> productList = adminService.addNewProduct(key, product);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@PutMapping("/update-product")
	public ResponseEntity<Product> update_product(@RequestParam String key, @RequestParam Integer productId,
			@RequestParam String name, @RequestParam String des, @RequestParam String brand,
			@RequestParam Double rating, @RequestParam Double mPrice, @RequestParam Double sPrice,
			@RequestParam Integer stock) throws RecordsNotFoundException {

		Product product = adminService.updateProduct(key, productId, name, des, brand, rating, mPrice, sPrice, stock);

		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
	}

	@GetMapping("all-orders")
	public ResponseEntity<List<Orders>> get_all_Orders(@RequestParam String key) throws RecordsNotFoundException {
		List<Orders> ordersList = adminService.SeeAllOrders(key);
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}

	@GetMapping("order")
	public ResponseEntity<Orders> get_order_by_orderId(@RequestParam String key, @RequestParam Integer orderId)
			throws RecordsNotFoundException {
		Orders order = adminService.getOrderByOrderId(key, orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}

	@PutMapping("/update-status-order")
	public ResponseEntity<Orders> update_status_of_order(@RequestParam String key, @RequestParam Integer orderId, @RequestParam String msg) throws RecordsNotFoundException{
		Orders order = adminService.updateStatusOfOrders(key, orderId, msg);
		return new ResponseEntity<Orders>(order,HttpStatus.OK);
	}

	@GetMapping("ordersby-customer-id")
	public ResponseEntity<List<Orders>> get_orders_by_customer_id(@RequestParam String key,
			@RequestParam Integer customerId) throws RecordsNotFoundException {
		List<Orders> ordersList = adminService.getOrdersByCustomerId(key, customerId);
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}

	@GetMapping("ordersby-customer-phone")
	public ResponseEntity<List<Orders>> get_orders_by_customer_phone(@RequestParam String key,
			@RequestParam String phone) throws RecordsNotFoundException {
		List<Orders> ordersList = adminService.getOrdersByCustomerPhone(key, phone);
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}

	@GetMapping("ordersby-customer-email")
	public ResponseEntity<List<Orders>> get_orders_by_customer_email(@RequestParam String key,
			@RequestParam String email) throws RecordsNotFoundException {
		List<Orders> ordersList = adminService.getOrdersByCustomerEmail(key, email);
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}

	@GetMapping("orders-by-status")
	public ResponseEntity<List<Orders>> get_orders_by_status(@RequestParam String key, @RequestParam String msg)
			throws RecordsNotFoundException {
		List<Orders> ordersList = adminService.getOrdersByStatus(key, msg);
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}

	@GetMapping("all-customers")
	public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam String key) throws RecordsNotFoundException {
		List<Customer> customerList = adminService.getAllCustomers(key);
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}

	@GetMapping("sales-today")
	public ResponseEntity<Integer> get_sales_made_today(@RequestParam String key) throws RecordsNotFoundException {
		Integer sales = adminService.salesMadeToday(key);
		return new ResponseEntity<Integer>(sales, HttpStatus.OK);
	}
}
