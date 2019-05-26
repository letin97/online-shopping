package com.letrongtin.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.letrongtin.onlineshopping.exception.ProductNotFoundException;
import com.letrongtin.shoppingbackend.dao.CategoryDAO;
import com.letrongtin.shoppingbackend.dao.ProductDAO;
import com.letrongtin.shoppingbackend.dto.Category;
import com.letrongtin.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping(value = {"/", "/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickHome", true);
		mv.addObject("categories", categoryDAO.list());
		return mv;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
//	@RequestMapping(value = "/show/all/products")
//	public ModelAndView showAllProducts() {
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("title", "All Products");
//		mv.addObject("userClickAllProducts", true);
//		mv.addObject("categories", categoryDAO.list());
//		return mv;
//	}
	
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		Category category = categoryDAO.get(id);
		mv.addObject("category", category);

		mv.addObject("title", category.getName());
		mv.addObject("userClickCategoryProducts", true);
		mv.addObject("categories", categoryDAO.list());
		return mv;
	}
	
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProducts(@PathVariable("id") int id) throws ProductNotFoundException{
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		if (product == null) throw new ProductNotFoundException();
		
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		
		mv.addObject("product", product);
		mv.addObject("title", product.getName());
		mv.addObject("userClickShowProduct", true);
		return mv;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name="error", required = false) String error,
			@RequestParam(name="logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");
		if (error != null) {
			mv.addObject("message", "Invalid Username and Password!");
		}
		if (logout != null) {
			mv.addObject("logout", "User has successfully logged out!");
		}
		mv.addObject("title", "Login");
		return mv;
	}
	
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("errorTitle", "Aha!");
		mv.addObject("errorDescription", "You are not authorized to view this page!");
		return mv;
	}
	
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView searchProducts(@RequestParam(name="key") String key) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Search");
		mv.addObject("userClickSearch", true);
		mv.addObject("key", key);
		return mv;
	}

}
