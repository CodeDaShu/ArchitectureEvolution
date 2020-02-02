package com.archevolution.chapter4.controller;

import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archevolution.chapter4.model.User;
import com.archevolution.chapter4.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService ;
	
	@RequestMapping(value = "/queryAdmin")
    public String queryUser(){
		System.out.println("queryAdmin");
		
        return "User : Admin";
    }
	
	/**
	 * 根据用户 ID 查询用户信息（不带手机号）
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryUser/{userId}")
	@ResponseBody
    public String queryUserById(@PathVariable("userId") String userId){
		System.out.println("queryUserById userId = " + userId);
		
		if("".equals(userId)){
			return "参数不能为空";
		}
		
		User user = userService.queryUserById(userId);
		
        return user == null ? "User is not find" : user.toString() ;
    }
	
	/**
	 * 根据用户 ID 查询用户信息（带手机号，别名的方式获取）
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryUserTel/{userId}")
	@ResponseBody
    public String queryUserTelById(@PathVariable("userId") String userId){
		System.out.println("queryUserById userId = " + userId);
		
		if("".equals(userId)){
			return "参数不能为空";
		}
		
		User user = userService.queryUserTelById(userId);
		
        return user == null ? "User is not find" : user.toString() ;
    }

	/**
	 * 根据用户 ID 查询用户信息（带手机号，@Results 的方式获取）
	 * queryUserTelById2 这个方法名称不是一个好习惯，应该修改成有具体含义的方法名
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryUserTel2/{userId}")
	@ResponseBody
    public String queryUserTelById2(@PathVariable("userId") String userId){
		System.out.println("queryUserById userId = " + userId);
		
		if("".equals(userId)){
			return "参数不能为空";
		}
		
		User user = userService.queryUserTelById2(userId);
		
        return user == null ? "User is not find" : user.toString() ;
    }
	
	/**
	 * 新增操作
	 * http://127.0.0.1:8088/insertUser/zhangsan/F/20
	 * 
	 * @param userName
	 * @param gender
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "/insertUser/{userName}/{gender}/{age}")
	@ResponseBody
    public String insertUser(@PathVariable("userName") String userName ,
    		@PathVariable("gender") String gender ,@PathVariable("age") int age){
		
		if("".equals(userName) || "".equals(gender) || "".equals(age)){
			return "参数不能为空";
		}
		
		User user = new User();
		user.setUserName(userName);
		user.setGender(gender);
		user.setAge(age);
		userService.insertUser(user);
		
        return "Success" ;
    }
	
	/**
	 * 根据用户 ID 修改客户手机号
	 * http://127.0.0.1:8088/updateUserTel/2/18000000000
	 * 
	 * @param userId
	 * @param telephone
	 * @return
	 */
	@RequestMapping(value = "/updateUserTel/{userId}/{telephone}")
	@ResponseBody
    public String updateUserTel(@PathVariable("userId") int userId , @PathVariable("telephone") String telephone){
		
		if("".equals(userId) || "".equals(telephone)){
			return "参数不能为空";
		}
		
		User user = new User();
		user.setUserId(userId);
		user.setTelephone(telephone);
		userService.updateUserTel(user);
		
        return "Success" ;
    }

	/**
	 * 根据用户 ID 删除数据
	 * http://127.0.0.1:8088/deleteUser/1
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/deleteUser/{userId}")
	@ResponseBody
	public String deleteUser(@PathVariable("userId") int userId ){
		
		if("".equals(userId)){
			return "参数不能为空";
		}

		userService.deleteUserById(userId);
		
        return "Success" ;
    }
}
