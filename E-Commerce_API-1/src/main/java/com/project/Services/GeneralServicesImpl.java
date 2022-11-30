package com.project.Services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.Exceptions.RecordsNotFoundException;
import com.project.Model.Category;
import com.project.Model.Product;
import com.project.Repositories.CategoryRepo;
import com.project.Repositories.ProductRepo;

@Service
public class GeneralServicesImpl implements GeneralServices {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public List<Product> getAllProducts() throws RecordsNotFoundException {
		List<Product> productList = productRepo.findAll();

		if (productList.size() == 0)
			throw new RecordsNotFoundException("ProductsNotFound");

		return productList;
	}

	@Override
	public Product getProduct(Integer productId) throws RecordsNotFoundException {
		Optional<Product> product = productRepo.findById(productId);

		if (!product.isPresent())
			throw new RecordsNotFoundException("ProductNotFound");

		return product.get();
	}

	@Override
	public List<Product> getProductsByCategory(Integer categoryId) throws RecordsNotFoundException {
		Optional<Category> category = categoryRepo.findById(categoryId);

		if (!category.isPresent())
			throw new RecordsNotFoundException("InvalidCategoryID");

		return category.get().getProductList();
	}

	@Override
	public List<Product> getSortedProductsByPriceAsc() throws RecordsNotFoundException {
		List<Product> productList = productRepo.findAll(Sort.by(Sort.Direction.ASC, "productSellingPrice"));

		if (productList.size() == 0)
			throw new RecordsNotFoundException("NoProductsFound");

		return productList;
	}

	@Override
	public List<Product> getSortedProductsOfCategoryByPriceAsc(Integer CategoryId) throws RecordsNotFoundException {
		Optional<Category> category = categoryRepo.findById(CategoryId);

		if (!category.isPresent())
			throw new RecordsNotFoundException("Invalid Category Id.");

		List<Product> producList = category.get().getProductList();

		Collections.sort(producList, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				if (p1.getProductSellingPrice() < p2.getProductSellingPrice())
					return -1;
				else if (p1.getProductSellingPrice() > p2.getProductSellingPrice())
					return 1;
				else
					return 0;
			}
		});

		return producList;

	}

	@Override
	public List<Product> getSortedProductsByPriceDesc() throws RecordsNotFoundException {
		List<Product> productList = productRepo.findAll(Sort.by(Sort.Direction.DESC, "productSellingPrice"));

		if (productList.size() == 0)
			throw new RecordsNotFoundException("NoProductsFound");

		return productList;
	}

	@Override
	public List<Product> getSortedProductsOfCategoryByPriceDesc(Integer CategoryId) throws RecordsNotFoundException {
		Optional<Category> category = categoryRepo.findById(CategoryId);

		if (!category.isPresent())
			throw new RecordsNotFoundException("Invalid Category Id.");

		List<Product> producList = category.get().getProductList();

		Collections.sort(producList, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				if (p1.getProductSellingPrice() < p2.getProductSellingPrice())
					return 1;
				else if (p1.getProductSellingPrice() > p2.getProductSellingPrice())
					return -1;
				else
					return 0;
			}
		});

		return producList;
	}

	@Override
	public List<Product> getSortedProductsByRatingAsc() throws RecordsNotFoundException {
		List<Product> productList = productRepo.findAll(Sort.by(Sort.Direction.ASC, "rating"));

		if (productList.size() == 0)
			throw new RecordsNotFoundException("NoProductsFound");

		return productList;
	}

	@Override
	public List<Product> getSortedProductsOfCategoryByRatingAsc(Integer CategoryId) throws RecordsNotFoundException {
		Optional<Category> category = categoryRepo.findById(CategoryId);

		if (!category.isPresent())
			throw new RecordsNotFoundException("Invalid Category Id.");

		List<Product> producList = category.get().getProductList();

		Collections.sort(producList, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				if (p1.getRating() < p2.getRating())
					return -1;
				else if (p1.getRating() > p2.getRating())
					return 1;
				else
					return 0;
			}
		});

		return producList;
	}

	@Override
	public List<Product> getSortedProductsByRatingDesc() throws RecordsNotFoundException {
		List<Product> productList = productRepo.findAll(Sort.by(Sort.Direction.ASC, "rating"));

		if (productList.size() == 0)
			throw new RecordsNotFoundException("NoProductsFound");

		return productList;
	}

	@Override
	public List<Product> getSortedProductsOfCategoryByRatingDesc(Integer CategoryId) throws RecordsNotFoundException {
		Optional<Category> category = categoryRepo.findById(CategoryId);

		if (!category.isPresent())
			throw new RecordsNotFoundException("Invalid Category Id.");

		List<Product> producList = category.get().getProductList();

		Collections.sort(producList, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				if (p1.getRating() < p2.getRating())
					return 1;
				else if (p1.getRating() > p2.getRating())
					return -1;
				else
					return 0;
			}
		});

		return producList;
	}

	@Override
	public List<Product> getFilteredProductsByPrice(Double from, Double to) throws RecordsNotFoundException {
		List<Product> productList = productRepo.findAllByProductSellingPriceInRange(from, to);

		if (productList.size() == 0)
			throw new RecordsNotFoundException("NoProductsFound");

		return productList;
	}

	@Override
	public List<Product> getFilteredProductsOfCategoryByPrice(Integer CategoryId, Double from, Double to)
			throws RecordsNotFoundException {
		Optional<Category> category = categoryRepo.findById(CategoryId);

		if (!category.isPresent())
			throw new RecordsNotFoundException("ProductNotFound");

		List<Product> productList = productRepo.findProductsByPrice(from, to, CategoryId);

		return productList;

	}

	@Override
	public List<Product> getFilteredProductsByRating(Double rating) throws RecordsNotFoundException {
		List<Product> productList = productRepo.findAllByProductRating(rating);

		if (productList.size() == 0)
			throw new RecordsNotFoundException("NoProductsFound");

		return productList;
	}

	@Override
	public List<Product> getFilteredProductsOfCategoryByRating(Integer CategoryId, Double rating)
			throws RecordsNotFoundException {
		Optional<Category> category = categoryRepo.findById(CategoryId);

		if (!category.isPresent())
			throw new RecordsNotFoundException("ProductNotFound");

		List<Product> productList = productRepo.findAllByProductRatingWithCategory(rating, CategoryId);

		return productList;
	}

}
