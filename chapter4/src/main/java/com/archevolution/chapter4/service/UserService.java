package com.archevolution.chapter4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archevolution.chapter4.dao.UserDao;
import com.archevolution.chapter4.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public User queryUserById(int id) /*throws Exception*/{
		return userDao.queryUserById(id);
	}
	
	public User queryUserTelById(int id){
		return userDao.queryUserTelById(id);
	}
	
	public User queryUserTelById2(int id){
		return userDao.queryUserTelById2(id);
	}
	
	public void insertUser(User user){
		userDao.insertUser(user);
	}
	
	public void updateUserTel(User user){
		userDao.updateUserTel(user);
	}
	
	public void deleteUserById(int id){
		userDao.deleteUserById(id);
	}
	
	public void insertUserList(List<User> userList){
		userDao.insertUserList(userList);
	}
}
