package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//카테고리 생성
	public int createCategory(UserVo userVo) {
		System.out.println("UserService.createCategory()");
		
		String id = userVo.getId();
		String cateName = "미분류";
		String description ="기본으로 만들어지는 카테고리입니다.";
		int postcnt = 0;
		
		System.out.println("카테고리id: "+id);
		System.out.println("카테고리이름: "+cateName);
		System.out.println("카테고리내용: "+description);
		
		CategoryVo cateVo = new CategoryVo(id,cateName,description,postcnt);
		
		int catecnt = userDao.insertCate(cateVo);
		return catecnt;
	}
	
	//블로그 생성
	public int createBlog(UserVo userVo) {
		System.out.println("UserService.createcreateBlog()");
		
		String id = userVo.getId();
		String blogTitle = userVo.getUserName() +"의 블로그입니다";
		String logoFile = "${pageContext.request.contextPath}/assets/images/spring-logo.jpg";
		
		System.out.println("블로그id: "+id);
		System.out.println("blogTitle: "+ blogTitle);
		System.out.println("logoFile: "+ logoFile);
		
		BlogVo blogVo = new BlogVo(id,blogTitle,logoFile);
		
		int blogcnt = userDao.insertBlog(blogVo);
		return blogcnt;
	}
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("UserService.join()");
		System.out.println("회원no: "+userVo.getUserNo());
		System.out.println("회원id: "+userVo.getId());
		System.out.println("회원name: "+userVo.getUserName());

        int usercnt = userDao.insertUser(userVo);
		return usercnt;
	}
	
	//아이디 중복체크
	public boolean idcheck(String id) {
		System.out.println("UserService.idCheck()");
		
		UserVo userVo = userDao.selectUser(id);
        if(userVo == null) {
        	return true;
        }else {
        	return false;
        }
	}
	
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("UserService.login()");

		UserVo authUser = userDao.selectUser(userVo);
		return authUser;
	}
	


}
