package com.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Cart;
import com.project.Model.Orders;
import com.project.Services.CustomerServices;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServices customerService;

	@PostMapping("/add-to-cart")
	public ResponseEntity<Cart> add_product_to_cart(@RequestParam String key, @RequestParam Integer prodId,
			@RequestParam Integer qty) throws RecordsNotFoundException {
		Cart cart = customerService.addProductToCart(key, prodId, qty);
		return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get-cart")
	public ResponseEntity<Cart> get_cart(@RequestParam String key) throws RecordsNotFoundException{
		Cart cart = customerService.seeCart(key);
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}

	@GetMapping("payment")
	public ResponseEntity<List<Orders>> make_purchase(@RequestParam String key, @RequestParam Integer paymentId)
			throws RecordsNotFoundException {
		List<Orders> orders = customerService.makePurchace(key, paymentId);
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}

	@GetMapping("all-orders")
	public ResponseEntity<List<Orders>> getAllOrders(@RequestParam String key) throws RecordsNotFoundException {
		List<Orders> orders = customerService.seeAllOrders(key);
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}

	@GetMapping("orderId")
	public ResponseEntity<Orders> getOrderBYOrderId(@RequestParam String key, @RequestParam Integer orderId)
			throws RecordsNotFoundException {
		Orders order = customerService.getOrderById(key, orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
}
