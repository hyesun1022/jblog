package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	//카테고리 별 포스트 갯수
	
	
	//블로그관리-베이직 업데이트(파일이 없는경고 타이틀만 있는경우)
	public void updateBasicByTitle(BlogVo blogVo) {
		System.out.println("BlogDao.updateBasicByTitle");
		System.out.println(blogVo);
		int count = sqlSession.update("blog.updateBasicByTitle", blogVo);
		System.out.println("업데이트 성공여부: "+count);
	}
	
	//블로그관리-베이직 업데이트(전체 업데이트)
	public void updateBasic(BlogVo blogVo) {
		System.out.println("BlogDao.updateBasic");
		System.out.println(blogVo);
		int count = sqlSession.update("blog.updateBasic", blogVo);
		System.out.println("업데이트 성공여부: "+count);
	}
	
	//메인 블로그 정보 가져오기
	public BlogVo selectBlog(String blogId) {
		System.out.println("BlogDao.selectBlog()");
		BlogVo blogVo = sqlSession.selectOne("blog.selectMainList", blogId);
		return blogVo;
	}
	
	//카테고리 리스트 가져오기
	public List<CategoryVo> selectCateList(String blogId) {
		System.out.println("BlogDao.selectCateList()");
		List<CategoryVo> cateList = sqlSession.selectList("blog.selectCateList", blogId);
		return cateList;
	}
	
	//메인 포스트 리스트 가져오기
	public List<PostVo> selectPostList(String blogId) {
		System.out.println("BlogDao.selectPostList()");
		List<PostVo> postList = sqlSession.selectList("blog.selectPostList", blogId);
		return postList;
	}
	

}
