package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	String saveDir = "C:\\javaStudy\\upload";
	
	//블로그 관리 - 카테고리 삭제
	public int deleteCate(CategoryVo categoryVo) {
		System.out.println("BlogService.deleteCate()");
		
		int count = blogDao.deleteCate(categoryVo);
		return count;
	}
	
	//블로그 관리 - 카테고리 추가 및 리스트 리스트 가져오기
	public CategoryVo cateAdd(CategoryVo categoryVo) {
		System.out.println("BlogService.cateAdd()");
		
		//카테고리 추가
		blogDao.insertCate(categoryVo);
		System.out.println(categoryVo.getCateNo());
		int cateNo = categoryVo.getCateNo();
		
		//카테고리 가져오기
		CategoryVo cateVo = blogDao.selectCate(cateNo);
		return cateVo;
	}
	
	
	//블로그 관리 - 카테고리리스트 불러오기
	public List<CategoryVo> cateList(String blogId) {
		System.out.println("BlogService.cateList()");
		
		List<CategoryVo> cateList = blogDao.selectCateList(blogId);
		int postCnt = blogDao.selectPostCnt(blogId);
		
		return cateList;
	}
	
	
	//블로그 관리 - 베이직
	public void basic(String blogId,String blogTitle,MultipartFile file) {
		System.out.println("BlogService.basic()");
		
		if(!file.isEmpty()) {
			String orgName = file.getOriginalFilename();
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
	        String filePath = saveDir + "\\" + saveName;
			long fileSize = file.getSize();
			
			try {
				byte[] fileData = file.getBytes();
				OutputStream out= new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				bout.write(fileData);
				bout.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BlogVo blogVo = new BlogVo(blogId,blogTitle,saveName);
			blogDao.updateBasic(blogVo);
		}else {
		    BlogVo blogVo = new BlogVo(blogId,blogTitle);
		    blogDao.updateBasicByTitle(blogVo);
		}
	}

	
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
