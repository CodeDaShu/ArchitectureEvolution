package com.archevolution.chapter9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDCBTests {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
	 	//初始化驱动类com.mysql.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/arch?characterEncoding=UTF-8&serverTimezone=UTC","root", "root");
		} catch (ClassNotFoundException e) { 				
			e.printStackTrace();
		}catch (SQLException e) {							
			e.printStackTrace();
		}
	}
}
