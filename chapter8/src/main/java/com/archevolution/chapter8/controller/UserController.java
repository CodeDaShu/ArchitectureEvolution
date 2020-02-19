package com.archevolution.chapter8.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archevolution.chapter8.dto.JsonResponse;
import com.archevolution.chapter8.dto.ResponseCode;
import com.archevolution.chapter8.dto.UserDto;
import com.archevolution.chapter8.model.User;
import com.archevolution.chapter8.service.UserService;

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
	@RequestMapping(value = "/queryUser/{id}")
	@ResponseBody
    public String queryUserById(@PathVariable("id") int id){
		System.out.println("queryUserById id = " + id);
		
		if(id < 1){
			return "参数不正确";
		}
		
		User user = userService.queryUserById(id);
		
        return user == null ? "User is not find" : user.toString() ;
    }
	
	/**
	 * 根据用户 ID 查询用户信息（带手机号，别名的方式获取）
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryUserTel/{id}")
	@ResponseBody
    public String queryUserTelById(@PathVariable("id") int id){
		System.out.println("queryUserById id = " + id);

		if(id < 1){
			return "参数不正确";
		}
		
		User user = userService.queryUserTelById(id);
		
        return user == null ? "User is not find" : user.toString() ;
    }

	/**
	 * 根据用户 ID 查询用户信息（带手机号，@Results 的方式获取）
	 * queryUserTelById2 这个方法名称不是一个好习惯，应该修改成有具体含义的方法名
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryUserTel2/{id}")
	@ResponseBody
    public String queryUserTelById2(@PathVariable("id") int id){
		System.out.println("queryUserById id = " + id);

		if(id < 1){
			return "参数不正确";
		}
		
		User user = userService.queryUserTelById2(id);
		
        return user == null ? "User is not find" : user.toString() ;
    }
	
	/**
	 * 新增操作
	 * http://127.0.0.1:8088/insertUser/zhangsan/张三/M/30
	 * 
	 * @param userName
	 * @param gender
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "/insertUser/{userId}/{userName}/{gender}/{age}")
	@ResponseBody
    public String insertUser(@PathVariable("userId") String userId ,@PathVariable("userName") String userName ,
    		@PathVariable("gender") String gender ,@PathVariable("age") int age){
		
		if("".equals(userId) || "".equals(userName) 
				|| "".equals(gender) || "".equals(age)){
			return "参数不能为空";
		}
		
		User user = new User();
		user.setUserId(userId);
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
	@RequestMapping(value = "/updateUserTel/{id}/{telephone}")
	@ResponseBody
    public String updateUserTel(@PathVariable("id") int id , @PathVariable("telephone") String telephone){

		if(id < 1){
			return "参数不正确";
		}
		
		if("".equals(telephone)){
			return "参数不能为空";
		}
		
		User user = new User();
		user.setId(id);
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
	@RequestMapping(value = "/deleteUser/{id}")
	@ResponseBody
	public String deleteUser(@PathVariable("id") int id ){
		
		if(id < 1){
			return "参数不正确";
		}

		userService.deleteUserById(id);
		
        return "Success" ;
    }
	
	/**
	 * 新增操作
	 * 使用了 Josn 作为参数，需要设置 headers = {"content-type=application/json"}
	 * @RequestBody UserDto userDto 可以让 JSON 串自动和 UserDto 绑定和转换
	 * 
	 * http://127.0.0.1:8088/insertUser2
	 * 
	 * { 
			"userId": "lisi", 
			"userName": "李四", 
			"gender": "F", 
			"age": "40", 
			"telephone": "18600000000"
		}
	 * 
	 * @param userName
	 * @param gender
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "/insertUser2",headers = {"content-type=application/json"})
	@ResponseBody
    public String insertUser2(@RequestBody UserDto userDto){
		
		if("".equals(userDto.getUserId()) || "".equals(userDto.getUserName()) 
				|| "".equals(userDto.getGender()) || "".equals(userDto.getAge())){
			return "参数不能为空";
		}
		
		//DTO 转成  Model
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setGender(userDto.getGender());
		user.setAge(userDto.getAge());
		
		userService.insertUser(user);
		
        return "Success" ;
    }
	
	/**
	 * 根据用户 ID 查询用户信息（不带手机号）
	 * 
	 * http://127.0.0.1:8088/queryUser2
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryUser2")
	@ResponseBody
    public JsonResponse queryUser2ById(@RequestBody UserDto userDto){
		JsonResponse res = new JsonResponse();
		
		if(userDto.getId() < 1){
			res.setCode(ResponseCode.PARAMETER_ERROR);
			res.setMessage("参数不能为空");
			return res;
		}
		
		User user = new User();
		
		try {
			user = userService.queryUserById(userDto.getId());
		} catch (Exception e) {
			res.setCode(ResponseCode.FAIL);
			res.setMessage("服务异常");
		}
		
		if(user != null){
			res.setCode(ResponseCode.SUCCESS);
			res.setData(user);;
		}else{
			res.setCode(ResponseCode.SUCCESS_NULL);
			res.setMessage("查询不到数据");
		}
		
        return res;
    }
	
	/**
	 * 批量增加 users
	 * http://127.0.0.1:8088/insertUser3
	 * 
	 *  [
		    {
		    	"userId": "Tom", 
		        "userName": "王汤姆", 
		        "gender": "M", 
		        "age": "22", 
		        "telephone": "18600000001"
		    }, 
		    {
		    	"userId": "Jerry", 
		        "userName": "李杰瑞", 
		        "gender": "M", 
		        "age": "33", 
		        "telephone": "18000000000"
		    }
		]
	 * @param userDtoList
	 * @return
	 */
	@RequestMapping(value = "/insertUser3",headers = {"content-type=application/json"})
	@ResponseBody
    public JsonResponse insertUser3(@RequestBody List<UserDto> userDtoList){
		
		JsonResponse res = new JsonResponse();
		
		if(userDtoList == null || userDtoList.size() < 1){
			res.setCode(ResponseCode.PARAMETER_ERROR);
			res.setMessage("参数不能为空");
			return res;
		}
		
		//DTO 转成  Model
		
		List<User> userList = new ArrayList<User>();
		
		for(UserDto userDto : userDtoList){
			if(userDto != null){
				User user = new User();
				user.setUserId(userDto.getUserId());
				user.setUserName(userDto.getUserName());
				user.setGender(userDto.getGender());
				user.setAge(userDto.getAge());
				
				userList.add(user);
			}
		}
		
		userService.insertUserList(userList);
		
		res.setCode(ResponseCode.SUCCESS);
		
        return res ;
    }
}
