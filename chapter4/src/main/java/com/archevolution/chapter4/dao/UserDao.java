package com.archevolution.chapter4.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.archevolution.chapter4.model.User;

//@Mapper 
@Mapper
//注意这里是接口不是类
public interface UserDao {
	
	/**
	 * 查询方法
	 * @param userId
	 * @return
	 */
	@Select("SELECT userId, userName, gender, age FROM USER WHERE userId = #{userId}")
	public User queryUserById(@Param("userId") String userId);
	
	/**
	 * 增加了电话号码字段，通过别名的方式，查询方法
	 * @param userId
	 * @return
	 */
	@Select("SELECT userId, userName, gender, age, mobilephone as telephone FROM USER WHERE userId = #{userId}")
	public User queryUserTelById(@Param("userId") String userId);
	
	/**
	 * 增加了电话号码字段，通过 @Results 的方式，查询方法
	 * @param userId
	 * @return
	 */
	@Select("SELECT userId, userName, gender, age, mobilephone FROM USER WHERE userId = #{userId}")
	@Results({
		@Result(property = "telephone" , column = "mobilephone")	
	})
	public User queryUserTelById2(@Param("userId") String userId);
}
