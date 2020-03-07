package com.archevolution.chapter10.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.archevolution.chapter10.model.Dict;

public interface DictDao extends CrudRepository<Dict, Integer>{
	
	//根据字典类型，查询所有数据
	public List<Dict> findByType(String type);
	
	//根据字典类型和Code，查询唯一一条数据
	public Dict findByTypeAndCode(String type, String code);
}
