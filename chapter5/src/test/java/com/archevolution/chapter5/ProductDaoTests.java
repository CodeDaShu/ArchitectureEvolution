package com.archevolution.chapter5;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}
