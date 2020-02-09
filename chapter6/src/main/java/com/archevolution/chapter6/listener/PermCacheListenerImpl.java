package com.archevolution.chapter6.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.archevolution.chapter6.cache.PermCache;
import com.archevolution.chapter6.constant.Constants;
import com.archevolution.chapter6.model.Dict;
import com.archevolution.chapter6.service.DictService;

@Order(value=1)
@Component
public class PermCacheListenerImpl implements CommandLineRunner {
 
	@Autowired
	DictService dictService;
	
    @Override
    public void run(String... args) throws Exception {
    	//查询数据库，商品类型字典表
    	List<Dict> dictList = dictService.findByType(Constants.PRODUCT_TYPE);
    	
    	//查询结果给 PermCache.PRODUCT_TYPE_CACHE
    	for(Dict dict : dictList){
    		if(dict != null && !StringUtils.isEmpty(dict.getCode())
    				&& !StringUtils.isEmpty(dict.getDesc())){
    			PermCache.PRODUCT_TYPE_CACHE.put(dict.getCode(),dict.getDesc());
    		}
    	}
    }
}