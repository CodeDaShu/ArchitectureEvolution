package com.archevolution.chapter7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.archevolution.chapter7.Chapter7Application;
import com.archevolution.chapter7.service.DictService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Chapter7Application.class)
public class DictTests {

	@Autowired
	DictService dictService;
	
	@Test
	public void testFindProDescByCode(){
		String desc = dictService.findProDescByCode("1");
		
		assertEquals("手机/运营商/数码", desc);
	}
}
