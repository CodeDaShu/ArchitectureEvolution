package com.archevolution.chapter10;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {
	public static void main(String[] args){
		int size = 1000000;
		//布隆过滤器
		BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.001);

		for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

		List<Integer> list = new ArrayList<Integer>(1000);
        for (int i = size + 1; i < size + 10000; i++) {
            if (bloomFilter.mightContain(i)) {
            	System.out.println("误判：" + i);
                list.add(i);
            }
        }
        System.out.println("误判数量：" + list.size());
	}

}
