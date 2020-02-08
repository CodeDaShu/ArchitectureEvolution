package com.archevolution.chapter5.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.archevolution.chapter5.model.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Integer>  /*CrudRepository<Product, Integer>*/{
	public Product findOneByProductid(String productid);
	
	//按照ID查询
	public Product findById(int id);
	
	//按照姓名查询
	public Product findByProductname(String productname);
	
	//查询所有
	public List<Product> findAll();
	
	//按照类型查询，可能返回多条
	public List<Product> findByType(String type);
	
	public List<Product> getByType(String type);
	
	public List<Product> queryByType(String type);
	
	public List<Product> readByType(String type);
	
	public List<Product> findByTypeAndProductid(String type , String productid);
	
	//绑定 SQL 查询语句
	//@Query(value = "SELECT * FROM PRODUCT WHERE type = ?1", nativeQuery = true)
	@Query("select u from Product u where u.type = ?1")
	public List<Product> findByProductType(String type);
	
	@Query("select u from Product u where u.type = :type")
	public List<Product> findByProductType2(@Param("type") String type);
	
	//排序
	@Query("select u from Product u where u.type = ?1 order by productid desc")
	public List<Product> findByTypeOrder(String type);
	
	public List<Product> findByTypeOrderByProductidDesc(String type);
	
	@Query("select u from Product u where u.type = ?1")
	public List<Product> findByTypeOrder(String type, Order order);
	
	//分页
	@Query("select u from Product u where u.type = ?1")
	public List<Product> findByTypePage(String type, Pageable pageable);
	
	public Product findFirstByTypeOrderByProductidDesc(String type);
	
	//修改
	@Transactional
	@Modifying
	@Query("update Product u set u.price = ?1 where u.productid = ?2")
	int updateProduct(BigDecimal price, String productid);
	
	void deleteByProductname(String productname);
	
	void deleteByProductid(String productid);
	
	@Modifying
	@Query("delete Product u where u.productid = ?1")
	void deleteByProductidQuerty(String productid);
}
