package com.archevolution.chapter5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archevolution.chapter5.dao.ProductDao;
import com.archevolution.chapter5.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	public Product findOneById(int id){
		//return productDao.findOneById(id);
		return productDao.findById(id);
	}
}
