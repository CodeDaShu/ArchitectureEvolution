package com.archevolution.chapter8.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpireCache {
	private static Logger logger = LoggerFactory.getLogger(ExpireCache.class);

	//缓存对象
	private static final Map<String, ExpireCacheEntity> EXPIRE_CACHE_MAP = new ConcurrentHashMap<>();
	
	//缓存最大的个数，为了测试方便，我们只设置成 10，实际应用中可以将此数据
	private static final Integer CACHE_MAX_NUMBER = 10;
	
	//缓存过期时间，也是为了测试方便，设置成 1 分钟
	public static final long EXPIRE_TIME = 1 * 60 * 1000L;
	
	//10 秒清除一次缓存
	public static final long THREAD_RUN_TIME = 10 * 1000L;
	
	//启动清理缓存的线程
    /*static {
        new Thread(new CleanExpireCacheThread()).start();
    }*/
	
	/**
	 * 清除过期缓存
	 */
	public static void removeExpireCache(){
		logger.info("remove expire cache run!");
		
		//先把过期了的缓存挑出来，后面统一删除
        List<String> deleteKeyList = new ArrayList<String>();
        
        for(Map.Entry<String, ExpireCacheEntity> entry : EXPIRE_CACHE_MAP.entrySet()) {
            if (entry.getValue().getExpireTime() < System.currentTimeMillis()) {
                deleteKeyList.add(entry.getKey());
            }
        }

        //统一删除
        for (String deleteKey : deleteKeyList) {
        	EXPIRE_CACHE_MAP.remove(deleteKey);
        }
        
        logger.info("remove expire cache count is :" + deleteKeyList.size());
	}
	
	/**
	 * 插入缓存
	 * @param key
	 * @param value
	 */
	public static void putCache(String key , Object value){
		logger.info("put cache ： key = {} and value = {}" , key , value.toString());
		
		//如果缓存没有满，放入缓存
		if(EXPIRE_CACHE_MAP.size() < CACHE_MAX_NUMBER){
			EXPIRE_CACHE_MAP.put(key, new ExpireCacheEntity(value , System.currentTimeMillis() + EXPIRE_TIME));
		}else{
			//如果缓存满了，理论上应该要做更多的工作，比如主动遍历一遍缓存，删除过期缓存；如果都没有过期，可以删除最快过期的缓存
			//这里我们偷个懒，如果缓存满了，则不再放入
			logger.info("cache is full , the EXPIRE_CACHE_MAP size is {}" , EXPIRE_CACHE_MAP.size());
		}
	}
	
	/**
	 * 查询缓存
	 * @param key
	 * @return
	 */
	public static Object getCache(String key){
		ExpireCacheEntity cache = EXPIRE_CACHE_MAP.get(key);
		
		if(cache != null && cache.getExpireTime() > System.currentTimeMillis()){
			return cache.getValue();
		}
		
		return null;
	}
	
	/**
	 * 清除缓存的线程
	 */
	static class CleanExpireCacheThread implements Runnable{

	    @Override
	    public void run() {
	        while (true) {
	            try {
	                Thread.sleep(ExpireCache.THREAD_RUN_TIME);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
	        	ExpireCache.removeExpireCache();
	        }
	    }
	}
}
