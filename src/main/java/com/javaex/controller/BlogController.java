package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {
	
	//블로그 관리 - 베이직
	@RequestMapping(value="/admin",method = {RequestMethod.GET,RequestMethod.POST})
	public String basic() {
		System.out.println("BlogController.basic()");
		
		return "/blog/admin/blog-admin-basic";
	}
	
	
	//블로그 메인
	@RequestMapping(value="/main",method = {RequestMethod.GET,RequestMethod.POST})
	public String main(HttpSession session) {
		System.out.println("BlogController.main()");
		
		
		return "/blog/blog-main";
	}

}
