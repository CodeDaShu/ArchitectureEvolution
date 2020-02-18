package com.archevolution.chapter7.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.archevolution.chapter7.cache.ExpireCache;
import com.archevolution.chapter7.dao.ProductDao;
import com.archevolution.chapter7.model.Product;

@Service
public class ProductCacheService {
	private static Logger logger = LoggerFactory.getLogger(ProductCacheService.class);
	
	@Autowired
	ProductDao productDao;
	
	@Cacheable("person")
	public Product findByProductid(String productid){
		return productDao.findByProductid(productid);
	}
	
}
