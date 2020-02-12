package com.archevolution.chapter6.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
	//缓存对象
	private static Map<String, Object> LRU_CACHE_MAP = new LinkedHashMap<String, Object>();

	//缓存最大的个数，为了测试方便，我们只设置成 10，实际应用中可以将此数据
	private static final Integer CACHE_MAX_NUMBER = 10;
	
	/**
	 * 查询缓存
	 * @param key
	 * @return
	 */
	public static Object getCache(String key){
		//如果缓存数据被命中，那么需要把该缓存节点移动到整个 Map 的尾部
		if(LRU_CACHE_MAP.get(key) != null){
			Object value = LRU_CACHE_MAP.get(key);
			//删除缓存
			LRU_CACHE_MAP.remove(key);
			//重新添加缓存，这样就放到 Map 的尾部了
			LRU_CACHE_MAP.put(key, value);
			
			return value ;
		}else{
			return null;
		}
	}
	
	/**
	 * 新增缓存
	 * @param key
	 * @param value
	 */
	public static void putCache(String key, Object value){
		if(LRU_CACHE_MAP.get(key) != null){
			//新增（或更新）缓存的时候，先查询  Map 是否存在
			//如果存在的话，依然需要移除再添加，保证缓存放在 Map 尾部
			LRU_CACHE_MAP.remove(key);
		}else if(LRU_CACHE_MAP.size() >= CACHE_MAX_NUMBER){
			//如果缓存满了，就移除第一个节点，也就是最近最后一次被使用的节点
			LRU_CACHE_MAP.remove(LRU_CACHE_MAP.entrySet().iterator().next().getKey());
		}
		LRU_CACHE_MAP.put(key, value);
	}
	
}
