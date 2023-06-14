package com.javaex.controller;

import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	//블로그 관리 - 
	
	
	//블로그 관리 - 카테고리폼
	@RequestMapping(value="{blogId}/admin/cate",method = {RequestMethod.GET,RequestMethod.POST})
	public String cateForm(@PathVariable("blogId") String blogId, Model model) {
		BlogVo blogVo = blogService.getBlog(blogId);
		
		System.out.println("BlogController.cateForm()");
		Map<String, Object> bMap = blogService.cateList(blogId);
		bMap.put("blogVo", blogVo);
		model.addAttribute("bMap",bMap);
		System.out.println(bMap);
		
		return "/blog/admin/blog-admin-cate";
	}
	
	
	//블로그 관리 - 베이직정보 저장
	@RequestMapping(value="{blogId}/admin/basicModify",method = {RequestMethod.GET,RequestMethod.POST})
	public String basic(HttpSession session, @PathVariable("blogId") String blogId,
			            @RequestParam("blogTitle") String blogTitle,Model model,
			            @RequestParam("file") MultipartFile file) {
		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blogVo",blogVo);
		
		System.out.println("BlogController.basic()");
        blogService.basic(blogId,blogTitle,file);
		
		
		return "redirect:/"+blogId+"/admin";
	}
	
	
	//블로그 관리 - 베이직폼
	@RequestMapping(value="/{blogId}/admin/basic",method = {RequestMethod.GET,RequestMethod.POST})
	public String basicForm(@PathVariable("blogId") String blogId, HttpSession session, Model model) {
		System.out.println("BlogController.basicForm()");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String id = authUser.getId();
		BlogVo blogVo = blogService.getBlog(blogId);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("blogVo", blogVo);
		model.addAttribute("bMap",bMap);
		System.out.println(bMap);
		
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
