package com.project.Services;

import java.util.List;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Product;

public interface GeneralServices {

	// Method to get all Products
	public List<Product> getAllProducts() throws RecordsNotFoundException;

	// method to get a single product
	public Product getProduct(Integer Id) throws RecordsNotFoundException;

	// method to get Product list by category
	public List<Product> getProductsByCategory(Integer Id) throws RecordsNotFoundException;

	// method to sort the Product List by price Low to High
	public List<Product> getSortedProductsByPriceAsc() throws RecordsNotFoundException;
	
	// method to sort the Product List by price Low to High for Category
	public List<Product> getSortedProductsOfCategoryByPriceAsc(Integer CategoryId) throws RecordsNotFoundException;

	// method to sort the Product List by price High to low.
	public List<Product> getSortedProductsByPriceDesc() throws RecordsNotFoundException;

	// method to sort the Product List by price High to Low for Category
	public List<Product> getSortedProductsOfCategoryByPriceDesc(Integer CategoryId) throws RecordsNotFoundException;

	// method to sort the Product List by Rating Low to High
	public List<Product> getSortedProductsByRatingAsc() throws RecordsNotFoundException;

	// method to sort the Product List by Rating Low to High for Category
	public List<Product> getSortedProductsOfCategoryByRatingAsc(Integer CategoryId) throws RecordsNotFoundException;

	// method to sort the Product List by Rating High to Low
	public List<Product> getSortedProductsByRatingDesc() throws RecordsNotFoundException;

	// method to sort the Product List by Rating High to Low for Category
	public List<Product> getSortedProductsOfCategoryByRatingDesc(Integer CategoryId) throws RecordsNotFoundException;

	// method to get Filtered List by price (from to )
	public List<Product> getFilteredProductsByPrice(Double from, Double to) throws RecordsNotFoundException;
	
	// method to get Filtered List by price (from to ) for Category
	public List<Product> getFilteredProductsOfCategoryByPrice(Integer CategoryId, Double from, Double to)
			throws RecordsNotFoundException;

	// method to get Filtered List by price (from to )
	public List<Product> getFilteredProductsByRating(Double rating) throws RecordsNotFoundException;

	// method to get Filtered List by price (from to ) for Category
	public List<Product> getFilteredProductsOfCategoryByRating(Integer CategoryId, Double rating)
			throws RecordsNotFoundException;

	

}
