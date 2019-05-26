package com.letrongtin.shoppingbackend.dao;

import java.util.List;

import com.letrongtin.shoppingbackend.dto.OrderDetail;

public interface OrderDAO {
	public List<OrderDetail> listByUser(int userId);
	public OrderDetail get(int id);
	public List<OrderDetail> list();
	boolean update(OrderDetail orderDetail);
	boolean delete(OrderDetail orderDetail);
}
