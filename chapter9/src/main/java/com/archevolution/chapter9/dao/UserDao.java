package com.archevolution.chapter9.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.archevolution.chapter9.model.User;

//@Mapper 
@Mapper
//注意这里是接口不是类
public interface UserDao {
	
	/**
	 * 查询方法
	 * @param id
	 * @return
	 */
	@Select("SELECT id, userId, userName, gender, age FROM USER WHERE id = #{id}")
	public User queryUserById(@Param("id") int id);
	
	/**
	 * 增加了电话号码字段，通过别名的方式，查询方法
	 * @param id
	 * @return
	 */
	@Select("SELECT id, userId, userName, gender, age, mobilephone as telephone FROM USER WHERE id = #{id}")
	public User queryUserTelById(@Param("id") int id);
	
	/**
	 * 增加了电话号码字段，通过 @Results 的方式，查询方法
	 * @param userId
	 * @return
	 */
	@Select("SELECT id, userId, userName, gender, age, mobilephone FROM USER WHERE id = #{id}")
	@Results({
		@Result(property = "telephone" , column = "mobilephone")	
	})
	public User queryUserTelById2(@Param("id") int id);
	
	/**
	 * 新增操作
	 * @param user
	 */
	@Insert("INSERT INTO USER(userId, userName, gender, age) values"
			+ " (#{userId}, #{userName}, #{gender}, #{age})")
	public void insertUser(User user);
	
	/**
	 * 修改客户手机号操作
	 * @param user
	 */
	@Update("UPDATE USER SET mobilephone = #{telephone} WHERE id = #{id}")
	public void updateUserTel(User user);
	
	/**
	 * 删除操作
	 * @param userId
	 */
	@Delete("DELETE FROM USER WHERE id = #{id}")
	public void deleteUserById(@Param("id") int id);
	
	/**
	 * 新增操作
	 * @param user
	 */
	@Insert({
		"<script>"
		+ "INSERT INTO USER(userId, userName, gender, age) values"
		+ "<foreach collection='userList' item='item' index='index' separator=','>"
		+ " (#{item.userId}, #{item.userName}, #{item.gender}, #{item.age})"
		+ "</foreach>"
		+ "</script>"
		})
	public void insertUserList(@Param(value="userList") List<User> userList);
	
}
