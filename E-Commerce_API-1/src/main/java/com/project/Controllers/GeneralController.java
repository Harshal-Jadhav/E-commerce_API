package com.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Product;
import com.project.Services.GeneralServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("products")
public class GeneralController {

	@Autowired
	GeneralServices gservice;

	// Handler to get all the products:
	@GetMapping("/all")
	@Operation(summary = "Get a list of all Products.", description = "This handler will provide a List of all available Products. Login is not required to use this method. If no products are found in the database the handler will return a BadRequest response with a message as \"Product not found\".")
	public ResponseEntity<List<Product>> getProductsListHandler() throws RecordsNotFoundException {
		List<Product> productList = gservice.getAllProducts();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get a Single Product Based on Id.
	@GetMapping
	@Operation(summary = "Get Product based on product Id", description = "This handler will take an Integer (product Id) as an input and will return the product with that Id from the DataBase. If the Product is not found with the given Id the handler will throw an Bad Request response with message as \"Product not found\".")
	public ResponseEntity<Product> getProductHandler(@RequestParam Integer Id) throws RecordsNotFoundException {
		Product product = gservice.getProduct(Id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	// Handler to get a List of Product based on category.
	@GetMapping("/category")
	@Operation(summary = "Get a list of products based on category.", description = "This handler will take a String (product category) as an input and will return the list of products. If no products are found with the category provided the handler will return a BadRequest with a message as \"Products not found\".")
	public ResponseEntity<List<Product>> getProductsByCategoryHandler(@RequestParam Integer categoryId)
			throws RecordsNotFoundException {
		List<Product> productList = gservice.getProductsByCategory(categoryId);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get a Sorted List of products based on the selling price (Low to
	// High).
	@GetMapping("/price/asc")
	@Operation(summary = "Get a sorted list of products by selling price  in low to high range ", description = "This Handler will return you a sorted list of products based on the price and in range low to high. If there are no any products added in the database then the handler will return  a BadRequest with a message as \"Products not found\".")
	public ResponseEntity<List<Product>> getSordetProductsByPriceAscHandler() throws RecordsNotFoundException {
		List<Product> productList = gservice.getSortedProductsByPriceAsc();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get Sorted List of products based on the selling price (High to
	// Low)
	@GetMapping("/price/desc")
	@Operation(summary = "Get a sorted list of products by selling price in high to low range ", description = "This Handler will return you a sorted list of products based on the price and in range high to low. If there are no any products added in the database then the handler will return  a BadRequest with a message as \"Products not found\".")
	public ResponseEntity<List<Product>> getSordetProductsByPriceDescHandler() throws RecordsNotFoundException {
		List<Product> productList = gservice.getSortedProductsByPriceDesc();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get Sorted List of a Category based on selling price (Low to
	// High);
	@GetMapping("/price/category/asc")
	@Operation(summary = "Get a sorted list of products by selling price  in low to high range ", description = "This Handler will return you a sorted list of products based on the price and in range low to high. If there are no any products added in the database then the handler will return  a BadRequest with a message as \"Products not found\".")
	public ResponseEntity<List<Product>> getSordetProductsOfCategoryByPriceAscHandler(@RequestParam Integer CategoryId)
			throws RecordsNotFoundException {
		List<Product> productList = gservice.getSortedProductsOfCategoryByPriceAsc(CategoryId);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get Sorted List of a Category based on selling price (High to
	// Low);
	@GetMapping("/price/category/desc")
	@Operation(summary = "Get a sorted list of products by selling price  in low to high range ", description = "This Handler will return you a sorted list of products based on the price and in range low to high. If there are no any products added in the database then the handler will return  a BadRequest with a message as \"Products not found\".")
	public ResponseEntity<List<Product>> getSordetProductsOfCategoryByPriceDescHandler(@RequestParam Integer CategoryId)
			throws RecordsNotFoundException {
		List<Product> productList = gservice.getSortedProductsOfCategoryByPriceDesc(CategoryId);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get sorted List of Products based on rating (low to high)
	@GetMapping("/rating/asc")
	public ResponseEntity<List<Product>> getSortedProductsByRatingAsc() throws RecordsNotFoundException {
		List<Product> productList = gservice.getSortedProductsByRatingAsc();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get sorted List of Products based on rating (High to Low)
	@GetMapping("/rating/desc")
	public ResponseEntity<List<Product>> getSortedProductsByRatingDesc() throws RecordsNotFoundException {
		List<Product> productList = gservice.getSortedProductsByRatingDesc();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get sorted List of Products based on rating (low to high) for
	// Category
	@GetMapping("/rating/category/asc")
	public ResponseEntity<List<Product>> getSortedProductsOfCategoryByRatingAsc(@RequestParam Integer categoryId)
			throws RecordsNotFoundException {
		List<Product> productList = gservice.getSortedProductsOfCategoryByRatingAsc(categoryId);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get sorted List of Products based on rating (High to low) for
	// Category
	@GetMapping("/rating/category/desc")
	public ResponseEntity<List<Product>> getSortedProductsOfCategoryByRatingDesc(@RequestParam Integer categoryId)
			throws RecordsNotFoundException {
		List<Product> productList = gservice.getSortedProductsOfCategoryByRatingDesc(categoryId);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get filtered list between price Range.
	@GetMapping("/filter/price")
	public ResponseEntity<List<Product>> getFilteredProductsByPrice(@RequestParam Double from, @RequestParam Double to)
			throws RecordsNotFoundException {
		List<Product> productList = gservice.getFilteredProductsByPrice(from, to);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get filtered list between price Range. for Category.
	@GetMapping("/filter/price/category")
	public ResponseEntity<List<Product>> getFilteredProductsOfCategoryByPrice(@RequestParam Double from,
			@RequestParam Double to, @RequestParam Integer categoryId) throws RecordsNotFoundException {
		List<Product> productList = gservice.getFilteredProductsOfCategoryByPrice(categoryId, from, to);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get filtered list by rating.
	@GetMapping("/filter/rating")
	public ResponseEntity<List<Product>> getFilteredProductsByRating(@RequestParam Double rating)
			throws RecordsNotFoundException {
		List<Product> productList = gservice.getFilteredProductsByRating(rating);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	// Handler to get filtered list by rating for Category.
	@GetMapping("/filter/rating/category")
	public ResponseEntity<List<Product>> getFilteredProductsOfCategoryByRating(@RequestParam Double rating,
			@RequestParam Integer categoryId) throws RecordsNotFoundException {
		List<Product> productList = gservice.getFilteredProductsOfCategoryByRating(categoryId, rating);
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

}
