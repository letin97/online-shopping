package com.letrongtin.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letrongtin.onlineshopping.model.UserModel;
import com.letrongtin.shoppingbackend.dao.OrderDAO;
import com.letrongtin.shoppingbackend.dto.OrderDetail;

@Service
public class OrderService {
	
	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private HttpSession session;
	
	private int getUserId() {
		return ((UserModel)session.getAttribute("userModel")).getId();
	}
	
	public List<OrderDetail> listByUser() {
		return orderDAO.listByUser(getUserId());
	}
	
	public OrderDetail get(int id) {
		return orderDAO.get(id);
	}
}
