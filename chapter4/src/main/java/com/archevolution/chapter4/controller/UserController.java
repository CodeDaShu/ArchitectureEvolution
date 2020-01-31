package com.archevolution.chapter4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping(value = "/queryAdmin")
    public String queryUser(){
		System.out.println("queryAdmin");
		
        return "User : Admin";
    }
}
