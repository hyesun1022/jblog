<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
		<h1><a href="${pageContext.request.contextPath}/blog/main">${bMap.blogVo.blogTitle}</a></h1>
		<c:choose>
			<c:when test="${sessionScope.authUser==null}">
				<ul class="clearfix">
					<li><a class="btn_s"href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<if test="${sessionScope.authUser.id == bMap.blogVo.id}}">
				<ul>
					<li><a class="btn_s" href="${pageContext.request.contextPath}/${bMap.blogVo.id}/admin/basic">내블로그 관리</a></li>
					</if>
				    <li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				</ul>
			</c:otherwise>
		 </c:choose>
				
				
		</div>
		<!-- //header -->
		