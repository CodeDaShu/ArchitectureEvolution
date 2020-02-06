package com.archevolution.chapter5;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.archevolution.chapter5.dao.ProductDao;
import com.archevolution.chapter5.model.Product;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Chapter5Application.class)
public class ProductDaoTests {

	@Autowired
	ProductDao productDao ;
	
	@Test
	public void testFindById() {
		int productid = 1;
		Product product = productDao.findById(productid);
		
		assertEquals("Huawei Mate30 Pro", product.getProductname());
	}

	@Test
	public void testFindByType() {
		String type = "1";
		
		List<Product> productList = productDao.findByType(type);
		
		for(Product product : productList){
			if(1 == product.getProductid()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
		
		List<Product> productList2 = productDao.getByType(type);
		
		for(Product product : productList2){
			if(1 == product.getProductid()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
		
		List<Product> productList3 = productDao.queryByType(type);
		
		for(Product product : productList3){
			if(1 == product.getProductid()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
		
		List<Product> productList4 = productDao.readByType(type);
		
		for(Product product : productList4){
			if(1 == product.getProductid()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
	}
	
	@Test
	public void testFindByTypeAndId() {
		int productid = 1;

		String type = "1";
		
		List<Product> productList = productDao.findByTypeAndProductid(type, productid);
		
		assertEquals("Huawei Mate30 Pro", productList.get(0).getProductname());
	}
	
	@Test
	public void testFindByProductType() {
		String type = "1";
		
		List<Product> productList = productDao.findByProductType(type);
		
		for(Product product : productList){
			if(1 == product.getProductid()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
	}
	
	@Test
	public void testFindByProductType2() {
		String type = "1";
		
		List<Product> productList = productDao.findByProductType2(type);
		
		for(Product product : productList){
			if(1 == product.getProductid()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
	}
	
	//排序方法测试
	@Test
	public void testFindByTypeOrderByProductidDesc() {
		String type = "1";
		
		List<Product> productList0 = productDao.findByTypeOrderByProductidDesc(type);
		
		Product product0 = productList0.get(productList0.size() - 1);
		
		assertEquals("Huawei Mate30 Pro", product0.getProductname());
		
		//--------------------------------------------------------------

		List<Product> productList = productDao.findByTypeOrderByProductidDesc(type);
		
		Product product = productList.get(productList.size() - 1);
		
		assertEquals("Huawei Mate30 Pro", product.getProductname());
		
		//--------------------------------------------------------------
		
		List<Product> productList2 = productDao.findByTypeOrder(type, new Sort.Order(Sort.Direction.DESC,"productid"));
		
		Product product2 = productList.get(productList.size() - 1);
		
		assertEquals("Huawei Mate30 Pro", product2.getProductname());
	}
	
	//分页方法测试
	@Test
	public void testFindByTypePage() {
		String type = "1";
		
		int page = 0;
		
		int size = 10;
		
		PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "productid");
		
		List<Product> productList = productDao.findByTypePage(type, pageable);
		
		Product product = productList.get(productList.size() - 1);
		
		assertEquals("Huawei Mate30 Pro", product.getProductname());

		//--------------------------------------------------------------
		
		Product product2 = productDao.findFirstByTypeOrderByProductidDesc(type);
		
		assertEquals("Xiaomi CC9 Pro", product2.getProductname());
	}
	
	//新增方法
	@Test
	public void testSave(){
		Product product = new Product();
		
		product.setProductname("C++ Primer Plus");
		product.setType("2");
		product.setPrice(new BigDecimal("78.00"));
		
		productDao.save(product);
		
		Product product2 = productDao.findByProductname("C++ Primer Plus");
		assertEquals(new BigDecimal("78.00"), product2.getPrice());
	}

	//修改方法
	@Test
	public void testUpdate(){
		
		Product product = productDao.findByProductname("数学之美");
		
		if(product == null){
			product = new Product();
			
			product.setProductname("数学之美");
			product.setType("2");
			product.setPrice(new BigDecimal("36.50"));
			
			productDao.save(product);
		}else{
			productDao.updateProduct(new BigDecimal("36.50"), "数学之美");
		}
		
		Product product2 = productDao.findByProductname("数学之美");
		assertEquals(new BigDecimal("36.50"), product2.getPrice());
		
		productDao.updateProduct(new BigDecimal("30.50"), "数学之美");
		
		product2 = productDao.findByProductname("数学之美");
		assertEquals(new BigDecimal("30.50"), product2.getPrice());
	}
}
