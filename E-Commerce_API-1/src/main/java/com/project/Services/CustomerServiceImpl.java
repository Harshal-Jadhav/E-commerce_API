package com.project.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Cart;
import com.project.Model.CurrentUserSession;
import com.project.Model.Customer;
import com.project.Model.OrderDetails;
import com.project.Model.Orders;
import com.project.Model.Payment;
import com.project.Model.Product;
import com.project.Repositories.CartRepo;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.OrderDetailsRepo;
import com.project.Repositories.OrdersRepo;
import com.project.Repositories.PaymentRepo;
import com.project.Repositories.ProductRepo;
import com.project.Repositories.SessionRepo;

@Service
public class CustomerServiceImpl implements CustomerServices {

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

	@Override
	public Cart addProductToCart(String key, Integer productId, Integer quantity)
			throws RecordsNotFoundException {
		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
			
		//Getting the customer.
		Customer customer = customerRepo.findByPhone(activeSession.getMobile());

		// Getting the product
		Optional<Product> product = productRepo.findById(productId);
		
		if (customer == null)
			throw new RecordsNotFoundException("Invalid customer id.");

		if (!product.isPresent())
			throw new RecordsNotFoundException("Invalid product Id.");
		
		if (product.get().getStock() == 0)
			throw new RecordsNotFoundException("Product Out Of Stock");

		if (product.get().getStock() < quantity)
			throw new RecordsNotFoundException("product Stock is less than ordered quantity.");

		// Creating orderDetails and adding to the list;
		OrderDetails od = new OrderDetails();

		od.setCart(customer.getCart());
		od.setProduct(product.get());
		od.setQuantity(quantity);
		od.setTotalValue((int) (quantity * product.get().getProductSellingPrice()));
		
		OrderDetails savedOrderDetails = odRepo.save(od);

		customer.getCart().getCartList().add(savedOrderDetails);
		Cart savedCart = cartRepo.save(customer.getCart());

		return savedCart;
	}

	@Override
	public Cart seeCart(String key) throws RecordsNotFoundException {
		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");

		// Getting the customer.
		Customer customer = customerRepo.findByPhone(activeSession.getMobile());

		return customer.getCart();
	}

	@Override
	public Stack<Orders> makePurchace(String key, Integer paymentId) throws RecordsNotFoundException {
		
		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");

		// Getting the customer.
		Customer customer = customerRepo.findByPhone(activeSession.getMobile());
		
		//Getting the payment Object;
		Optional<Payment> payment = payRepo.findById(paymentId);

		if (!payment.isPresent())
			throw new RecordsNotFoundException("Invalid payment Id");

		// Getting the cart;
		List<OrderDetails> cartList = customer.getCart().getCartList();

		// Getting the Orders Stack;
		Stack<Orders> orders = customer.getOrderList();
		
		Orders order = new Orders();

		// Setting up the order variable;
		Integer totalValue = 0;
		for (OrderDetails od : cartList) {
			Product p = od.getProduct();
			p.setStock(p.getStock() - od.getQuantity());
			productRepo.save(p);
			od.setCart(null);
			od.setOrder(order);
			order.getOrderDetails().add(od);
			totalValue += od.getTotalValue();
		}
		
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus("Order Confirmed, Yet to be Shipped.");
		order.setPayment(payment.get());
		order.setTotalOrderValue(totalValue);
		order.setCustomer(customer);

		customer.getOrderList().push(order);
		customer.getCart().setCartList(new ArrayList<>());
		payment.get().getOrder().add(order);
		
		orderRepo.save(order);
		Customer savedCustomer = customerRepo.save(customer);
		cartRepo.save(customer.getCart());
		payRepo.save(payment.get());
		
		return savedCustomer.getOrderList();
	}

	@Override
	public Stack<Orders> seeAllOrders(String key) throws RecordsNotFoundException {
		// Getting the customer.
		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");
			
		//Getting the customer.
		Customer customer = customerRepo.findByPhone(activeSession.getMobile());

		return customer.getOrderList();
	}

	@Override
	public Orders getOrderById(String key, Integer orderId) throws RecordsNotFoundException {
		CurrentUserSession activeSession = sRepo.findByKey(key);

		if (activeSession == null)
			throw new RecordsNotFoundException("No active session found.");

		// Getting the customer.
		Customer customer = customerRepo.findByPhone(activeSession.getMobile());

		Optional<Orders> order = orderRepo.findById(orderId);

		if (customer == null)
			throw new RecordsNotFoundException("Invalid Customer Id");
		
		if (!order.isPresent() || order.get().getCustomer().getId() != customer.getId())
			throw new RecordsNotFoundException("Invalid Order Id");

		return order.get();

	}

}
