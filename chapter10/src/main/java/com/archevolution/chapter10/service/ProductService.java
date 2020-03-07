package com.archevolution.chapter10.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archevolution.chapter10.cache.ExpireCache;
import com.archevolution.chapter10.dao.ProductDao;
import com.archevolution.chapter10.model.Product;

@Service
public class ProductService {
	private static Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	ProductDao productDao;
	
	public Product findOneById(int id){
		//return productDao.findOneById(id);
		return productDao.findById(id);
	}
	
	/**
	 * 查询所有商品
	 * @return
	 */
	public List<Product> findAll(){
		return productDao.findAll();
	}
	
	/**
	 * 根据商品编号查询
	 * @param productId
	 * @return
	 */
/*	public Product findProduct(String productid){
		//现在缓存中查询
		Product product = (Product)ExpireCache.getCache(productid);
		
		if(product == null){
			//如果在缓存中查不到，在数据库中查询一遍
			product = productDao.findByProductid(productid);
			
			if(product == null){
				//如果数据库中也没有，那就是真的没有，返回 null
				return null;
			}else{
				//如果数据库中能查询到，在返回之前，插入缓存
				//缓存的 key 设置成产品编号
				//缓存中的 value，则存放 product 对象
				ExpireCache.putCache(productid, product);
				
				return product;
			}
		}else{
			//如果缓存中能查询到，则直接返回
			return product;
		}
	}*/
	
	public Product findProduct(String productid){
		//现在缓存中查询
		Product product = (Product)ExpireCache.getCache(productid);
		
		if(product == null){
			//如果在缓存中查不到，在数据库中查询一遍
			product = productDao.findByProductid(productid);
			
			if(product != null){
				//如果数据库中能查询到，在返回之前，插入缓存
				//缓存的 key 设置成产品编号
				//缓存中的 value，则存放 product 对象
				logger.info("find Product from DataBase");
				ExpireCache.putCache(productid, product);
			}
		}else{
			logger.info("find Product from Cache");
		}
		
		return product;
	}
}
