package com.letrongtin.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.letrongtin.shoppingbackend.dao.CartLineDAO;
import com.letrongtin.shoppingbackend.dto.Cart;
import com.letrongtin.shoppingbackend.dto.CartLine;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {
	
	@Autowired
	private SessionFactory session;

	@Override
	public CartLine get(int id) {
		return session.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean add(CartLine cartLine) {
		try {
			session.getCurrentSession().persist(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try {
			session.getCurrentSession().update(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

	@Override
	public boolean delete(CartLine cartLine) {
		try {
			cartLine.setAvailable(false);
			this.update(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<CartLine> list(int cartId) {
		String sql = "FROM CartLine WHERE cartId = :cartId";
		return session.getCurrentSession().createQuery(sql, CartLine.class)
				.setParameter("cartId", cartId)
				.getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String sql = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		return session.getCurrentSession().createQuery(sql, CartLine.class)
				.setParameter("cartId", cartId)
				.setParameter("available", true)
				.getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String sql = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		try {
			return session.getCurrentSession().createQuery(sql, CartLine.class)
					.setParameter("cartId", cartId)
					.setParameter("productId", productId)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			session.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
