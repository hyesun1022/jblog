<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<!-- jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
        <c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${bMap.blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${bMap.blogVo.id}/admin/cate">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${bMap.blogVo.id}/admin/write">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
					<tbody id="cateList" >
		      			<!-- 리스트 영역 -->

						<!-- 리스트 영역 -->
					</tbody>			
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<input type="hidden" name="id" value="${bMap.blogVo.id}">
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		
	
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
//카테고리 삭제 클릭
$("#cateList").on("click",".btnCateDel", function(){
	console.log("삭제버튼 클릭");
	
	var cateno = $(this).data("cateno");
	console.log(cateno);
	
	var categoryVo = {
			cateNo: cateno 
	}
	
	$.ajax({
		
		url : "${pageContext.request.contextPath }/${bMap.blogVo.id}/admin/remove",		
		type : "post",
		data : categoryVo,

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult);
			/*성공시 처리해야될 코드 작성*/
 			if(jsonResult.data > 0){
				//화면에서 지우기
			    $("#t-"+CategoryVo.cateNo).remove();
			}else{
				
			} 
		},
		error : function(XHR, status, error) { 
			console.error(status + " : " + error);
		}
    });
});


//카테고리 추가 버튼 클릭
$("#btnAddCate").on("click", function(){
	console.log("추가 버튼 클릭");
	
	var id = $("[name='id']").val();
	var cateName = $("[name='name']").val();
	var desc = $("[name='desc']").val();

	var categoryVo ={
		id: id,
		cateName: cateName,
		description: desc
	};
	
	$.ajax({
		
		url : "${pageContext.request.contextPath }/${bMap.blogVo.id}/admin/cateAdd",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(categoryVo),

		dataType : "json",
		success : function(jsonResult){
			/*성공시 처리해야될 코드 작성*/
			console.log(jsonResult);
			
 			if(jsonResult.result == "success"){
				
				render(jsonResult.data); 
				
				var id = $("[name='id']").val();
				var cateName = $("[name='name']").val();
				var desc = $("[name='desc']").val();
			}else{
				
			}

		},
		error : function(XHR, status, error) { 
			console.error(status + " : " + error);
		}
    });
	
});


//전체 리스트 가져오기 
$(document).ready(function(){
	fetchList();
	
});
	function fetchList(){
		$.ajax({
			url : "${pageContext.request.contextPath }/${bMap.blogVo.id}/admin/cateList",		
			type : "post",

			dataType : "json",
			success : function(jsonResult){
				console.log(jsonResult);
  				var cateList = jsonResult.data;
					
			    for(var i=0;i<cateList.length; i++){
		            render(cateList[i],"down");
			    }   
			},
			error : function(XHR, status, error) { 
				  console.error(status + " : " + error); 
			}
		});
	}
	
	function render(CategoryVo){
		
		var str="";

		str += '	   <tr>';
		str += '			<td>'+ CategoryVo.cateNo +'</td>';
		str += '			<td>'+ CategoryVo.cateName+'</td>';
		str += '			<td>7</td>';
		str += '			<td>'+ CategoryVo.description+'</td>';
		str += '			<td class="text-center">';
		str += '				<img class="btnCateDel" data-cateno="'+CategoryVo.cateNo+'" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '	  		</td>';
		str += '	  </tr>';
		

		$("#cateList").prepend(str);

	}

</script>



</html>