package com.letrongtin.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.letrongtin.onlineshopping.util.FileUploadUtility;
import com.letrongtin.onlineshopping.validator.ProductValidator;
import com.letrongtin.shoppingbackend.dao.CategoryDAO;
import com.letrongtin.shoppingbackend.dao.OrderDAO;
import com.letrongtin.shoppingbackend.dao.ProductDAO;
import com.letrongtin.shoppingbackend.dto.Category;
import com.letrongtin.shoppingbackend.dto.OrderDetail;
import com.letrongtin.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	private static final Logger logger = Logger.getLogger(ManagementController.class);
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	OrderDAO orderDAO;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts", true);
		
		Product product = new Product();
		product.setSupplierId(1);
		product.setActive(true);
		mv.addObject("product", product);
		
		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product submitted successfully!");
			} else if (operation.equals("category")) {
				mv.addObject("message", "Category submitted successfully!");
			}
		}
		return mv;
	}
	
	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handlerProductActivation(@PathVariable("id") int id) {
		
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productDAO.update(product);
		
		return isActive? "You have successfully deactiveted the product with id: " + product.getId()
						: "You have successfully activeted the product with id: " + product.getId();
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handlerProductSubmit(@Valid @ModelAttribute("product") Product product,
			BindingResult result, Model model, HttpServletRequest request) {
		
		logger.debug("POSSSSSSSSSSSSSSSSSSSSSSSST");
		
		if (product.getId() == 0) {
			new ProductValidator().validate(product, result);
		}
		else {
			if (!product.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(product, result); 
			}
		}
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Manage Products");
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("mesage", "Validation failed for Product submission!");
			return "page";
		}
		
		if (product.getId() == 0) {
			productDAO.add(product);
		}
		else {
			productDAO.update(product);
		}
		
		if (!product.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, product.getFile(), product.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handlerCategorySubmit(@ModelAttribute Category category, BindingResult result) {
		
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts", true);
		
		Product product = productDAO.get(id);
		mv.addObject("product", product);
		
		return mv;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView showManageOrders(@RequestParam(name="result", required=false) String result) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Orders");
		mv.addObject("userClickManageOrders", true);
		
		if (result != null) {
			mv.addObject("message", "Order has been changed successfully!");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/order/{id}/activation")
	public String handlerOrderActivation(@PathVariable("id") int id) {
		
		OrderDetail orderDetail = orderDAO.get(id);
		orderDetail.setStatus(1);
		orderDAO.update(orderDetail);
		return "redirect:/manage/orders?result=activation";
	}
	
	@RequestMapping(value = "/order/{id}/cancel")
	public String handlerOrderCancel(@PathVariable("id") int id) {
		
		OrderDetail orderDetail = orderDAO.get(id);
		orderDetail.setStatus(-1);
		orderDAO.update(orderDetail);
		return "redirect:/manage/orders?result=cancel";
	}
}
