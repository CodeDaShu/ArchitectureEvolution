package com.archevolution.chapter4.model;

public class User {
	private int id;//主键ID，自增长
	
	private String userId;//用户ID，或看做登录名
	
	private String userName;//用户姓名
	
	private String gender;//性别
	
	private int age;//年龄
	
	private String telephone;//手机号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", gender=" + gender + ", age=" + age
				+ ", telephone=" + telephone + "]";
	}
	
}
