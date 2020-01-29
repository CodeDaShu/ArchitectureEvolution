package com.archevolution.chapter1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping(value = "/queryAdmin")
    public String queryUser(){
        return "User : Admin";
    }
}
