<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
	    <c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		
	</div>

</body>
<script type="text/javascript">
    //회원 가입 버튼을 눌렀을 때
    $("#joinForm").on("submit", function(){
    	console.log("전송버튼 클릭");
    	
    	var id = $("#txtId").val();
    	if(id.length<1){
    		alert("아이디를 입력해주세요");
    		return false;
    	}
    	
    	var password = $("#txtPassword").val();
    	if(password.length<1){
    		alert("비밀번호를 입력해주세요");
    		return false;
    	}
    	
    	var name = $("#txtUserName").val();
    	if(name.length<1){
    		alert("이름을 입력해주세요");
    		return false;
    	}
    	
    	var agree = $("#chkAgree").is(":checked");
    	if(agree == false){
    		alert("약관에 동의해 주세요");
    		return false;
    	}
    	return true;
    });


    //아이디 중복체크
    $("#btnIdCheck").on("click",function(){
    	console.log("아이디 체크 버튼 클릭");
    	
    	var id = $("[name=id]").val();
    	console.log(id);
    	
    	$.ajax({
    		
			url : "${pageContext.request.contextPath }/user/idcheck",		
			type : "post",
			//contentType : "application/json",
			data : {id: id},
	
			dataType : "json",
			success : function(jsonResult){
				
				console.log(jsonResult);
				
				/*성공시 처리해야될 코드 작성*/
 	 			if(jsonResult.result == "success"){ //처리성공()
	 				//사용가능한지 불가능한지 표현한다.
					if(jsonResult.data==true){
						//데이타가 null 경우 true 이므로 아이디가 사용가능하다고 출력(아이디 사용가능)
						$("#tdMsg").html("사용할 수 있는 아이디입니다.");
					}else{
						//데이타가 있으므로 false 이므로 아이디가 사용 불가능
						$("#tdMsg").html("다른 아이디로 가입해주세요.");
					}
				}else{
					//혹시라도 result가 fail 나올 경우 메세지 출력
					alert("서버요청실패");
				} 
			},
			error : function(XHR, status, error) { 
				console.error(status + " : " + error);
				//alert("서버요청실패");
			}
	    });
    });


</script>

</html>