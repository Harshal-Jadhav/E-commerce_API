package com.project.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.Model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Query("FROM Product WHERE productSellingPrice>=?1 AND productSellingPrice<=?2")
	public List<Product> findAllByProductSellingPriceInRange(Double from, Double to);

	@Query("FROM Product WHERE productSellingPrice>=?1 AND productSellingPrice<=?2 AND category_Id=?3")
	public List<Product> findProductsByPrice(Double from, Double to, Integer categoryId);

	@Query("FROM Product WHERE rating>=?1")
	public List<Product> findAllByProductRating(Double rating);

	@Query("FROM Product WHERE rating>=?1 AND category_Id=?2")
	public List<Product> findAllByProductRatingWithCategory(Double rating, Integer categoryId);
}
