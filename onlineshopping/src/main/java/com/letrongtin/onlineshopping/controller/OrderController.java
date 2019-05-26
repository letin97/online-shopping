package com.letrongtin.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.letrongtin.onlineshopping.service.OrderService;
import com.letrongtin.shoppingbackend.dto.OrderDetail;
import com.letrongtin.shoppingbackend.dto.OrderItem;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/show")
	public ModelAndView showOrders() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "User Orders");
		mv.addObject("userClickShowOrder", true);
		mv.addObject("orders", orderService.listByUser());
		return mv;
	}
	
	@RequestMapping("/show/{orderId}/order")
	public ModelAndView showOrderItem(@PathVariable(name="orderId") int orderId) {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "User Order");
		mv.addObject("userClickShowOrderItem", true);
		mv.addObject("orderDetail", orderService.get(orderId));
		
		return mv;
	}
}
