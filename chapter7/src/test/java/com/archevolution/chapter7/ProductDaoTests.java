package com.archevolution.chapter7;

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

import com.archevolution.chapter7.Chapter7Application;
import com.archevolution.chapter7.dao.ProductDao;
import com.archevolution.chapter7.model.Product;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Chapter7Application.class)
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
			if(1 == product.getId()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
		
		List<Product> productList2 = productDao.getByType(type);
		
		for(Product product : productList2){
			if(1 == product.getId()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
		
		List<Product> productList3 = productDao.queryByType(type);
		
		for(Product product : productList3){
			if(1 == product.getId()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
		
		List<Product> productList4 = productDao.readByType(type);
		
		for(Product product : productList4){
			if(1 == product.getId()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
	}
	
	@Test
	public void testFindByTypeAndProductid() {
		String productid = "0001MB0001";

		String type = "1";
		
		List<Product> productList = productDao.findByTypeAndProductid(type, productid);
		
		assertEquals("Huawei Mate30 Pro", productList.get(0).getProductname());
	}
	
	@Test
	public void testFindByProductType() {
		String type = "1";
		
		List<Product> productList = productDao.findByProductType(type);
		
		for(Product product : productList){
			if(1 == product.getId()){
				assertEquals("Huawei Mate30 Pro", product.getProductname());
			}
		}
	}
	
	@Test
	public void testFindByProductType2() {
		String type = "1";
		
		List<Product> productList = productDao.findByProductType2(type);
		
		for(Product product : productList){
			if(1 == product.getId()){
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
	
	//新增方法 和 修改方法
	@Test
	public void testSave(){
		Product product = new Product();
		
		product.setProductid("0002BK0001");
		product.setProductname("C++ Primer Plus");
		product.setType("2");
		product.setPrice(new BigDecimal("78.00"));
		
		productDao.save(product);
		
		Product product2 = productDao.findOneByProductid("0002BK0001");
		assertEquals(new BigDecimal("78.00"), product2.getPrice());
		
		productDao.updateProduct(new BigDecimal("68.00"), "0002BK0001");
		
		product2 = productDao.findOneByProductid("0002BK0001");
		assertEquals(new BigDecimal("68.00"), product2.getPrice());
		
		productDao.delete(product2);
	}
}
