package com.archevolution.chapter9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.archevolution.chapter8.Chapter8Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Chapter8Application.class)
public class RedisTemplateTests {
	private static Logger logger = LoggerFactory.getLogger(RedisTemplateTests.class);

	
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
 
    @Autowired
    private StringRedisTemplate strRedisTemplate;

    @Test
    public void RedisTemplateTest(){
    	redisTemplate.opsForValue().set("key01", "value0001");	
    	logger.info("key01's value = {}" , redisTemplate.opsForValue().get("key01"));
    	
    	redisTemplate.opsForValue().set("key03", "value0003", 60, TimeUnit.SECONDS);	
    	logger.info("key03's value = {}" , redisTemplate.opsForValue().get("key03"));
    	
    	redisTemplate.opsForValue().set("key04", 10);	
    	logger.info("key04's value = {}" , redisTemplate.opsForValue().get("key04")); 	
    	
    	redisTemplate.opsForValue().increment("key04", 20);
    	logger.info("key04's value = {}" , redisTemplate.opsForValue().get("key04")); 	
    }
    
    @Test
    public void StringRedisTemplateTest() {
    	strRedisTemplate.opsForValue().set("key02", "value0002");	
    	logger.info("key02's value = {}" , strRedisTemplate.opsForValue().get("key02"));
    }
    
    @Test
    public void HashTemplateTest() {
    	Map<String, Integer> maps = new HashMap<String, Integer>();
    	maps.put("product_001", 4);
    	maps.put("product_002", 1);
    	
    	redisTemplate.opsForHash().putAll("user0001", maps);
    	
    	//添加
    	redisTemplate.opsForHash().put("user0001", "product_003", 2);
    	redisTemplate.opsForHash().put("user0001", "product_004", 1);
    	
    	//删除
    	redisTemplate.opsForHash().delete("user0001", "product_003");	
    	
    	//查询 HashMap 中所有的键 keys，结果为 product_001/2/4
    	Set<Object> sets = redisTemplate.opsForHash().keys("user0001");
    	
    	//查询 HashMap 中的是否存在 key=field 的键值
    	//查询 user001 中的 key = product_001 对应的值，结果为 4
    	redisTemplate.opsForHash().get("user0001", "product_001"); 
    	
    }
    
    @Test
    public void ListTemplateTest() {
    	//左插入：A
    	redisTemplate.opsForList().leftPush("list","A");
    	//左插入：B-A
    	redisTemplate.opsForList().leftPush("list","B");
    	//左插入：C-B-A
    	redisTemplate.opsForList().leftPush("list","C");
    	//右插入：C-B-A-D
    	redisTemplate.opsForList().rightPush("list","D");
    	//把 E 放在第一个 A 的左边：C-B-E-A-D
    	redisTemplate.opsForList().leftPush("list","A","E");  
    	//把 F 放在第一个 A 的左边：C-B-E-A-F-D
    	redisTemplate.opsForList().rightPush("list","A","F");  
    	
    	//查询所有的元素：C-B-E-A-F-D
    	List<Object> list =  redisTemplate.opsForList().range("list",0,-1);
    	
    	//移除最左边的：B-E-A-F-D
    	redisTemplate.opsForList().leftPop("list");
    	//移除最右边的：B-E-A-F
    	redisTemplate.opsForList().rightPop("list");
    	//移除集合中右边的元素，放入另一个指定队列的最左边：B-E-A  F
    	redisTemplate.opsForList().rightPopAndLeftPush("list", "list2");
    }
}
