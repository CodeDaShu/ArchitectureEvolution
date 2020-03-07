package com.archevolution.chapter10;

public class DirTest {

	public static void main(String[] args) {		
		String userHome = System.getProperty("user.home");
		String userDir = System.getProperty("user.dir");
		String tmpDir = System.getProperty("java.io.tmpdir");
			
		System.out.println("userHome = "+userHome);
		System.out.println("userDir = "+userDir);
		System.out.println("tmpDir = "+tmpDir);
	}
}
