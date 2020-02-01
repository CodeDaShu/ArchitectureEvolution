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
}
