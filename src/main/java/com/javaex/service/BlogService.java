package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	
	//블로그 관리 - 베이직폼
	
	
	//메인 블로그 정보 가져오기
	public BlogVo getBlog(String blogId) {
		System.out.println("BlogService.getBlog()");
		BlogVo blogVo = blogDao.selectBlog(blogId);
		return blogVo;
	}
	
	//메인 카테고리&포스트리스트 가져오기
	public Map<String, Object> getMainList(String blogId) {
		System.out.println("BlogService.getCategory()");
		List<CategoryVo> cateList = blogDao.selectCateList(blogId);
		List<PostVo> postList = blogDao.selectPostList(blogId);
		BlogVo blogVo = blogDao.selectBlog(blogId);
        
        Map<String, Object> bMap = new HashMap<String, Object>();
        bMap.put("cateList", cateList);
        bMap.put("postList", postList);
        bMap.put("blogId", blogId);
        
        return bMap;
	}
	
}
