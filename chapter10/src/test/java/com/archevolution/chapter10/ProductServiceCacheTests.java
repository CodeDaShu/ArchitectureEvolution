package com.archevolution.chapter10;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.archevolution.chapter10.Chapter8Application;
import com.archevolution.chapter10.model.Product;
import com.archevolution.chapter10.service.ProductCacheService;
import com.archevolution.chapter10.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Chapter8Application.class)
public class ProductServiceCacheTests {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceCacheTests.class);

	@Autowired
	ProductCacheService productCacheService;
	
	@Test
	public void testFindProduct() {

	}
}
