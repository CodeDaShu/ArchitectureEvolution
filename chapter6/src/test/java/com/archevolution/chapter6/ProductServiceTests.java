package com.archevolution.chapter6;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.archevolution.chapter6.model.Product;
import com.archevolution.chapter6.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Chapter6Application.class)
public class ProductServiceTests {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceTests.class);

	@Autowired
	ProductService productService;
	
	@Test
	public void testFindProduct() {
		List<Product> list = productService.findAll();
		
		for(int i = 0 ; i < 5 ; i++){
			for(Product pro : list){
				Product product = productService.findProduct(pro.getProductid());
				
				logger.info(product.toString());
				
				try {
	                Thread.sleep(2 * 1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}
			
		}
	}
}
