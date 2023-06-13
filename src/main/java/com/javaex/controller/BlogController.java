package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 관리 - 베이직정보 저장
	@RequestMapping(value="{blogId}/admin/basic",method = {RequestMethod.GET,RequestMethod.POST})
	public String basic(HttpSession session,@ModelAttribute BlogVo blogVo,
			            @RequestParam("blogTitle") String blogTitle) {
		System.out.println(blogVo);
		System.out.println(blogTitle);
//		System.out.println(file);
		
		
		return "";
	}
	
	
	//블로그 관리 - 베이직폼
	@RequestMapping(value="/{blogId}/admin",method = {RequestMethod.GET,RequestMethod.POST})
	public String basicForm(@PathVariable("blogId") String blogId, HttpSession session, Model model) {
		System.out.println("BlogController.basicForm()");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String id = authUser.getId();
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo",blogVo);
		
		return "/blog/admin/blog-admin-basic";
	}
	
	
	//블로그 메인폼
	@RequestMapping(value="/{blogId}", method = {RequestMethod.GET,RequestMethod.POST})
	public String main(@PathVariable("blogId") String blogId ,Model model,
			           @RequestParam( value = "cateNo", required=false, defaultValue="0") int cateNo) {
		System.out.println("BlogController.main()");
		BlogVo blogVo = blogService.getBlog(blogId);
		Map<String, Object> bMap = blogService.getMainList(blogId);
		bMap.put("blogVo", blogVo);
		model.addAttribute("bMap", bMap);
		System.out.println(bMap);
		
		return "/blog/blog-main";
	}
	
	

}
