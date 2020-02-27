package com.archevolution.chapter9.dto;

public class UserDto {
	private int id;
	
	private String userId;
	
	private String userName;
	
	private String gender;
	
	private int age;
	
	private String telephone;

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
		return "UserDto [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", gender=" + gender + ", age=" + age
				+ ", telephone=" + telephone + "]";
	}
}
