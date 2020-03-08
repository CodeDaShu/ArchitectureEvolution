package com.archevolution.chapter10;

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

import com.archevolution.chapter10.Chapter8Application;

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
    
    @Test
    public void SetTemplateTest() {
    	//添加元素
    	redisTemplate.opsForSet().add("set1", "A");
    	redisTemplate.opsForSet().add("set1", "B");
    	redisTemplate.opsForSet().add("set1", "C");
    	//添加重复元素，结果还是 A-B-C
    	redisTemplate.opsForSet().add("set1", "A");
    	
    	redisTemplate.opsForSet().add("set2", "A", "B", "D");
    	
    	//取交集，结果是：A-B
    	logger.info("set1 和 set2 的交集  = {}" , redisTemplate.opsForSet().intersect("set1", "set2"));
    	//取交集，保存到 intersectset 中
    	redisTemplate.opsForSet().intersectAndStore("set1", "set2", "intersectset");

    	//取并集，结果是：A-B-C-D
    	logger.info("set1 和 set2 的并集  = {}" , redisTemplate.opsForSet().union("set1", "set2"));

    	//取差集，结果是：C
    	logger.info("set1 和 set2 的差集  = {}" , redisTemplate.opsForSet().difference("set1", "set2"));
    	
    }
    
    @Test
    public void ZSetTemplateTest() {
    	redisTemplate.opsForZSet().add("zset", "A", 1);
    	redisTemplate.opsForZSet().add("zset", "B", 1.2);
    	redisTemplate.opsForZSet().add("zset", "C", 100);
    	
    	//B的分数 1.2 + 101.8 = 103
    	redisTemplate.opsForZSet().incrementScore("zset", "B", 101.8);
    	
    	//返回某个元素排序后的位置（从小到大）
    	logger.info("A 现在的排名（从小到大）  = {}" , redisTemplate.opsForZSet().rank("zset", "A"));
    	
    	//返回某个元素排序后的位置（从大到小）
    	logger.info("A 现在的排名（从大到小）  = {}" , redisTemplate.opsForZSet().reverseRank("zset", "A"));

    	redisTemplate.opsForZSet().add("zset", "D", 200);
    	redisTemplate.opsForZSet().add("zset", "E", 300);
    	
    	//分数从大到小排序后，获取指定范围内所有元素（排名的顺序）；0  表示第一个，-1 表示所有
    	logger.info("分数从大到小排序后，获取指定范围内所有元素（排名的顺序）  = {}" , redisTemplate.opsForZSet().reverseRange("zset", 1, 3));
    	
    	//分数从大到小排序后，获取分数在指定范围内的所有元素
    	logger.info("分数从大到小排序后，获取分数在指定范围内的所有元素  = {}" , redisTemplate.opsForZSet().reverseRangeByScore("zset", 100, 200));
    	
    	//从大到小排序后，分数在 100-200 之间的，获取指定范围内所有元素（排名的顺序）
    	logger.info("从大到小排序后，获取分数在 100-200 之间，获取指定范围内所有元素（排名的顺序）  = {}" , redisTemplate.opsForZSet().reverseRangeByScore("zset", 100, 200, 0, -1));
    	
    }
    
    @Test
    public void randomExpireTest(){
    	for(int i = 0 ; i < 100 ; i++){
    		redisTemplate.opsForValue().set("key"+i, "value"+i, 
    				60*60*24 + ((int) (System.currentTimeMillis() % 6000)), TimeUnit.SECONDS );
    	}
    }
}
