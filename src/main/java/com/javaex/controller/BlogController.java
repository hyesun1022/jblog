package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.JsonResult;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	//블로그 관리 - 카테고리 삭제
	@ResponseBody
	@RequestMapping(value="{blogId}/admin/remove", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult remove(@ModelAttribute CategoryVo categoryVo) {
		System.out.println("BlogController.remove()");

        int count = blogService.deleteCate(categoryVo);
        
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(count);
		
		return jsonResult;
	}
	
	
	//블로그 관리 - 카테고리 추가
	@ResponseBody
	@RequestMapping(value="{blogId}/admin/cateAdd", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult cateAdd(@PathVariable("blogId") String blogId,@RequestBody CategoryVo categoryVo) {
		System.out.println("BlogController.cateAdd()");
		CategoryVo cateVo = blogService.cateAdd(categoryVo);
		
		//jsonResult에 담기
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(cateVo);
		
		return jsonResult;
	}
	
	//블로그 관리 - 카테고리폼
	@RequestMapping(value="{blogId}/admin/cate",method = {RequestMethod.GET,RequestMethod.POST})
	public String cate(@PathVariable("blogId") String blogId, Model model) {
		System.out.println("BlogController.cate()");
		
		BlogVo blogVo = blogService.getBlog(blogId);
		List<CategoryVo> cateList = blogService.cateList(blogId);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("blogVo", blogVo);
		bMap.put("cateList",cateList);
		model.addAttribute("bMap",bMap);
		System.out.println(bMap);
		
		return "/blog/admin/blog-admin-cate";
	}
	
	//블로그 관리 - 카테고리리스트(ajax사용)
	@ResponseBody
	@RequestMapping(value="{blogId}/admin/cateList",method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult cateList(@PathVariable("blogId") String blogId, Model model) {
		System.out.println("BlogController.cateForm()");
		
		BlogVo blogVo = blogService.getBlog(blogId);
		List<CategoryVo> cateList = blogService.cateList(blogId);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("blogVo", blogVo);
		model.addAttribute("bMap",bMap);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(cateList);
		
		return jsonResult;
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
