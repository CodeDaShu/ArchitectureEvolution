package com.archevolution.chapter9.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.archevolution.chapter9.dao.ProductDao;
import com.archevolution.chapter9.model.Product;

//@CacheConfig(cacheNames="product")
@CacheConfig(cacheNames="productEhCache")
@Service
public class ProductCacheService {
	private static Logger logger = LoggerFactory.getLogger(ProductCacheService.class);
	
	@Autowired
	ProductDao productDao;
	
	@Cacheable
	//@Cacheable("product")
	//@Cacheable(value="product", key="#productid", condition="#productid.length() <= 10") //productid 长度超过 10 的不会被缓存，举例使用，没有什么实际用处
	//@Cacheable(value="product", key="#productid", unless="#result == null") //如果查询结果为空，就不缓存了
	public Product findByProductid(String productid){
		return productDao.findByProductid(productid);
	}
	
	@Caching(
	     cacheable = {
	         @Cacheable(value="product",key = "#productid")
	     },
	     put = {
	         @CachePut(value="product",key = "#result.id")
	    }
	)
	public Product findByProductid2(String productid){
		return productDao.findByProductid(productid);
	}
	
	@Cacheable(value="product", key="#id")
	public Product findById(int id){
		return productDao.findById(id);
	}
}
