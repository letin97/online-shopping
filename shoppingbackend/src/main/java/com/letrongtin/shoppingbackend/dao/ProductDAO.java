package com.letrongtin.shoppingbackend.dao;

import java.util.List;

import com.letrongtin.shoppingbackend.dto.Product;

public interface ProductDAO {
	Product get(int id);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	
	List<Product> getTopProductsByCategory(int category, int count);
	List<Product> searchProducts(String key);
	
}
