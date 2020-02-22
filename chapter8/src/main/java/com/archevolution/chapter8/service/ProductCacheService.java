package com.archevolution.chapter8.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.archevolution.chapter8.cache.ExpireCache;
import com.archevolution.chapter8.dao.ProductDao;
import com.archevolution.chapter8.model.Product;

@Service
public class ProductCacheService {
	private static Logger logger = LoggerFactory.getLogger(ProductCacheService.class);
	
	@Autowired
	ProductDao productDao;
	
	//@Cacheable("product")
	//@Cacheable(value="product", key="#productid", condition="#productid.length() <= 10") //productid 长度超过 10 的不会被缓存，举例使用，没有什么实际用处
	@Cacheable(value="product", key="#productid", unless="#result == null") //如果查询结果为空，就不缓存了
	public Product findByProductid(String productid){
		return productDao.findByProductid(productid);
	}
	
}
