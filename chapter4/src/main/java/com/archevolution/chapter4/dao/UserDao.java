package com.archevolution.chapter4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	public User queryUserById(@Param("userId") int userId);
	
	/**
	 * 增加了电话号码字段，通过别名的方式，查询方法
	 * @param userId
	 * @return
	 */
	@Select("SELECT userId, userName, gender, age, mobilephone as telephone FROM USER WHERE userId = #{userId}")
	public User queryUserTelById(@Param("userId") int userId);
	
	/**
	 * 增加了电话号码字段，通过 @Results 的方式，查询方法
	 * @param userId
	 * @return
	 */
	@Select("SELECT userId, userName, gender, age, mobilephone FROM USER WHERE userId = #{userId}")
	@Results({
		@Result(property = "telephone" , column = "mobilephone")	
	})
	public User queryUserTelById2(@Param("userId") int userId);
	
	/**
	 * 新增操作
	 * @param user
	 */
	@Insert("INSERT INTO USER(userName, gender, age) values"
			+ " (#{userName}, #{gender}, #{age})")
	public void insertUser(User user);
	
	/**
	 * 修改客户手机号操作
	 * @param user
	 */
	@Update("UPDATE USER SET mobilephone = #{telephone} WHERE userid = #{userId}")
	public void updateUserTel(User user);
	
	/**
	 * 删除操作
	 * @param userId
	 */
	@Delete("DELETE FROM USER WHERE userid = #{userId}")
	public void deleteUserById(@Param("userId") int userId);
	
	/**
	 * 新增操作
	 * @param user
	 */
	@Insert({
		"<script>"
		+ "INSERT INTO USER(userName, gender, age) values"
		+ "<foreach collection='userList' item='item' index='index' separator=','>"
		+ " (#{item.userName}, #{item.gender}, #{item.age})"
		+ "</foreach>"
		+ "</script>"
		})
	public void insertUserList(@Param(value="userList") List<User> userList);
	
}
