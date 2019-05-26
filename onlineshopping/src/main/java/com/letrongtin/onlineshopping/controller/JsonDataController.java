package com.letrongtin.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letrongtin.shoppingbackend.dao.CategoryDAO;
import com.letrongtin.shoppingbackend.dao.OrderDAO;
import com.letrongtin.shoppingbackend.dao.ProductDAO;
import com.letrongtin.shoppingbackend.dto.Category;
import com.letrongtin.shoppingbackend.dto.OrderDetail;
import com.letrongtin.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping("/all/categorys")
	@ResponseBody
	public List<Category> getAllCategorys() {
		return categoryDAO.list();
	}
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts() {
		return productDAO.listActiveProducts();
	}
	
	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin() {
		return productDAO.list();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable("id") int id) {
		return productDAO.listActiveProductsByCategory(id);
	}
	
	@RequestMapping("/admin/all/orders")
	@ResponseBody
	public List<OrderDetail> getAllOrders() {
		return orderDAO.list();
	}
	
	@RequestMapping("/top/categoty/{id}")
	@ResponseBody
	public List<Product> getMostViewedProducts(@PathVariable("id") int id) {		
		return productDAO.getTopProductsByCategory(id, 4);				
	}
	
	@RequestMapping("/search/{key}/products")
	@ResponseBody
	public List<Product> searchProducts(@PathVariable("key") String key) {		
		return productDAO.searchProducts(key);				
	}

}
