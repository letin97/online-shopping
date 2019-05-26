package com.letrongtin.shoppingbackend.dao;

import java.util.List;

import com.letrongtin.shoppingbackend.dto.Cart;
import com.letrongtin.shoppingbackend.dto.CartLine;
import com.letrongtin.shoppingbackend.dto.OrderDetail;

public interface CartLineDAO {
	
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	public List<CartLine> list(int cartId);
	
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId, int productId); 
	
	public boolean updateCart(Cart cart);
	
	boolean addOrderDetail(OrderDetail orderDetail);
}
