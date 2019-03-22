package com.letrongtin.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.letrongtin.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;

	@RequestMapping(value="/show")
	public ModelAndView showCart(@RequestParam(name="result", required = false) String reslut) {
		ModelAndView mv = new ModelAndView("page");
		
		if (reslut != null) {
			switch (reslut) {
			case "added":
				mv.addObject("message", "CartLine has been added successfully!");
				break;
			case "updated":
				mv.addObject("message", "CartLine has been updated successfully!");
				break;
			case "error":
				mv.addObject("message", "Something went wrong!");
				break;
			case "deleted":
				mv.addObject("message", "CartLine has been removed successfully!");
				break;
			case "unavailable":
				mv.addObject("message", "Product quantity is not available!");
				break;
			case "maximum":
				mv.addObject("message", "CartLine has reached to maximum count!");
				break;
			default:
				break;
			}
		}
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());
		return mv;
	}
	
	@RequestMapping(value="/{cartLineId}/update")
	public String updateCart(@PathVariable(name="cartLineId") int cartLineId, @RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?" + response;
	}
	
	@RequestMapping(value="/{cartLineId}/delete")
	public String deleteCart(@PathVariable(name="cartLineId") int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?" + response;
	}
	
	@RequestMapping(value="/add/{productId}/product")
	public String addCart(@PathVariable(name="productId") int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?" + response;
	}
}
