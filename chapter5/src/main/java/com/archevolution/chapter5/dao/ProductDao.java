package com.archevolution.chapter5.dao;

import org.springframework.data.repository.CrudRepository;

import com.archevolution.chapter5.model.Product;

public interface ProductDao extends CrudRepository<Product, String>{
	public Product findOneByProductid(int productid);
}
