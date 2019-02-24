package com.letrongtin.shoppingbackend.dao;

import java.util.List;

import com.letrongtin.shoppingbackend.dto.Category;

public interface CategoryDAO {
	List<Category> list();
	Category get(int i);
}
