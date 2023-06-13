package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//카테고리 생성
	public int insertCate(CategoryVo cateVo) {
		System.out.println("UserDao.insertCate()");
		int catecnt = sqlSession.insert("user.insertCate", cateVo);
		System.out.println("카테고리생성: "+catecnt);
		return catecnt;
	}
	
	
	//블로그 생성
	public int insertBlog(BlogVo blogVo) {
		System.out.println("UserDao.insertBlog()");
		int blogcnt = sqlSession.insert("user.insertBlog", blogVo);
		System.out.println("블로그생성: "+blogcnt);
		return blogcnt;
		
	}
	
	
	//아이디 중복체크
	public UserVo selectUser(String id) {
		System.out.println("UserDao.selectUser");
		
		UserVo userVo = sqlSession.selectOne("user.selectUserById",id);
		return userVo;
		
	}
	
	
	//로그인
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser()");
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		return authUser;
	}
	
	
	//회원가입
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao.insertUser()");

        int usercnt = sqlSession.insert("user.insertUser", userVo);
		System.out.println("회원가입등록: "+usercnt);
		return usercnt;
	}

}
