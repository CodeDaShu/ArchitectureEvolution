package com.archevolution.chapter4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archevolution.chapter4.dao.UserDao;
import com.archevolution.chapter4.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public User queryUserById(String userId){
		return userDao.queryUserById(userId);
	}
	
	public User queryUserTelById(String userId){
		return userDao.queryUserTelById(userId);
	}
	
	public User queryUserTelById2(String userId){
		return userDao.queryUserTelById2(userId);
	}
	
	public void insertUser(User user){
		userDao.insertUser(user);
	}
	
	public void updateUserTel(User user){
		userDao.updateUserTel(user);
	}
	
	public void deleteUserById(int userId){
		userDao.deleteUserById(userId);
	}
}
