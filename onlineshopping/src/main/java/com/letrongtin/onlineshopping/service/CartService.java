package com.letrongtin.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letrongtin.onlineshopping.model.UserModel;
import com.letrongtin.shoppingbackend.dao.CartLineDAO;
import com.letrongtin.shoppingbackend.dao.ProductDAO;
import com.letrongtin.shoppingbackend.dto.Cart;
import com.letrongtin.shoppingbackend.dto.CartLine;
import com.letrongtin.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	public List<CartLine> getCartLines() {
		return cartLineDAO.listAvailable(this.getCart().getId());
	}

	public String manageCartLine(int cartLineId, int count) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null) {
			return "result=error";
		} 
		else {
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			if (product.getQuantity() < count) {
				return "result=unavailable";
			}
			cartLine.setProductCount(count);
			cartLine.setTotal(count * product.getUnitPrice());
			cartLine.setAvailable(true);
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			return "result=updated";
		}
	}

	public String deleteCartLine(int cartLineId) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null) {
			return "result=error";
		} else {
			Cart cart = this.getCart();
			cart.setCartLines(cart.getCartLines() - 1);
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			cartLine.setProductCount(0);
			cartLineDAO.delete(cartLine);
			
			return "result=deleted";		
		}
	}

	public String addCartLine(int productId) {
		String response = null;
		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if (cartLine == null) {
			cartLine = new CartLine();
			Product product = productDAO.get(productId);
			cartLine.setProduct(product);
			cartLine.setCartId(cart.getId());
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			response = "result=added";
		}
		else {
			if (cartLine.getProductCount() < 3) {
				response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
			}
			else {
				response = "result=maximum"; 
			}
		}
		return response;
	}
}
