package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.JsonResult;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그아웃
	@RequestMapping(value="/logout", method= {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		
		System.out.println("UserController.logout()");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value="/idcheck", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult idcheck(@RequestParam("id") String id) {
		System.out.println("UserController.idcheck()");
		
		boolean data = userService.idcheck(id);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(data);
		
		System.out.println(jsonResult);
		
		return jsonResult;
	}
	
	
	//로그인폼
	@RequestMapping(value="/loginForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "user/loginForm";
	}
	
	
	//로그인
	@RequestMapping(value="/login", method = {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.login()");
        
		UserVo authUser = userService.login(userVo);
		System.out.println("로그인 정보: "+authUser);
		
		if(authUser != null) {
			System.out.println("로그인 성공");
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}else {
			System.out.println("로그인 실패");
			
			return "redirect:/user/loginForm?result=fail";
		}
		
	}
	
	
	//회원가입폼
	@RequestMapping(value="/joinForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
	}
	
	//회원가입하기(user등록 및 blog,category생성)
	@RequestMapping(value="/join", method = {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		int usercnt = userService.join(userVo);
		int blogcnt = userService.createBlog(userVo);
		int catecnt = userService.createCategory(userVo);
		
		
		if(usercnt>0) {
			return "user/joinSuccess";
		}else {
			return "redirect:/user/joinForm";
		}
		
	}

}
