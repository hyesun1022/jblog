package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//블로그 생성
	public int createBlog(UserVo userVo) {
		System.out.println("UserService.create()");
		
		String id = userVo.getId();
		String blogTitle = userVo.getUserName() +"의 블로그입니다";
		String logoFile = "${pageContext.request.contextPath}/assets/images/spring-logo.jpg";
		
		System.out.println("id: "+id);
		System.out.println("blogTitle: "+ blogTitle);
		System.out.println("logoFile: "+ logoFile);
		
		BlogVo blogVo = new BlogVo(id,blogTitle,logoFile);
		
		int blogcnt = userDao.insertBlog(blogVo);
		return blogcnt;
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
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("UserService.join()");

        int usercnt = userDao.insertUser(userVo);
		return usercnt;
	}

}
