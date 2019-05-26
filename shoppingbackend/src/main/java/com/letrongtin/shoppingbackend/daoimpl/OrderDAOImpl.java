package com.letrongtin.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.letrongtin.shoppingbackend.dao.OrderDAO;
import com.letrongtin.shoppingbackend.dto.OrderDetail;
import com.letrongtin.shoppingbackend.dto.OrderItem;

@Repository("orderDAO")
@Transactional
public class OrderDAOImpl implements OrderDAO{
	
	@Autowired
	private SessionFactory session;

	@Override
	public List<OrderDetail> listByUser(int userId) {
		String select = "FROM OrderDetail WHERE user.id = :userId";
		return session.getCurrentSession()
				.createQuery(select, OrderDetail.class)
				.setParameter("userId", userId)
				.getResultList();
	}

	@Override
	public OrderDetail get(int id) {
		return session.getCurrentSession().get(OrderDetail.class, id);
	}

	@Override
	public List<OrderDetail> list() {
		String select = "FROM OrderDetail";
		return session.getCurrentSession()
				.createQuery(select, OrderDetail.class)
				.getResultList();
	}

	@Override
	public boolean update(OrderDetail orderDetail) {
		try {
			session.getCurrentSession().update(orderDetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean delete(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return false;
	}

}
