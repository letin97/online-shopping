package com.letrongtin.shoppingbackend.dao;

import java.util.List;

import com.letrongtin.shoppingbackend.dto.Address;
import com.letrongtin.shoppingbackend.dto.Cart;
import com.letrongtin.shoppingbackend.dto.User;

public interface UserDAO {
	
	User getByEmail(String email);
	
	User get(int id);
	
	boolean add(User user);

	Address getAddress(int addressId);
	
	boolean addAddress(Address address);
	
	boolean updateAddress(Address address);
	
	Address getBillingAddress(int userId);
	
	List<Address> listShippingAddresses(int userId);

}
