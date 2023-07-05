<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSY Food</title>
<%@ include file="/WEB-INF/inc/asset.jsp"%>
<style>
main {
	padding-top: 50px;
	padding-bottom: 50px;
}

#mpmenu {
	float: left;
	width: 80px;
}

#mpmenu>ul {
	width: 80px;
	margin: 0;
	padding: 0;
}

#mpmenu>ul>li {
	width: 80px;
	height: 100px;
	border: 1px solid #ccc;
	list-style-type: none;
	border-bottom: none;
	display: flex;
	align-items: center;
}

#mpmenu>ul>li>span {
	margin: 0 auto;
	text-align: center;
}

#mpmenu>ul>li:nth-last-child(1) {
	border-bottom: 1px solid #ccc;
}

#my {
	float: left;
	top: 10px;
	width: auto;
	margin-left: 230px;
	top: 10px;
}

#my>table {
	width: 800px;
}
#my > table > tbody > tr > td{
	text-align:center;
}

#ul-seller > li:nth-child(3) {
    background-color: tomato;
    color: white;
    font-size: 1.3rem;
    font-weight: bold;
    box-sizing: border-box;
 }
</style>
</head>
<body>
	<%@include file="/WEB-INF/inc/header.jsp"%>
	<main>


	<%@include file="/WEB-INF/inc/membernav.jsp"%>		


		<!-- mpmenu -->
		<form method="POST" action="/ssy/member/memberpageok.do">
		<div id="my">
			<h1>개인정보수정</h1>
			<table class="tbl1">
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name = "pw" id = "pw"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="pwCheck" id="pwCheck"></td>
				</tr>
				<tr>
					<th>배송지</th>
					<td><input type="text" value="${dto.maddress}" name="address" id="address"></td>
				</tr>
				
			</table>
			<input type="button" onclick="memberOX();" value="계정 비활성화">
			<button type="submit">
				<i class="fa-solid fa-pen-to-square"></i>
				수정하기
			</button>
		
		</div>
		<input type="hidden" name="aiseq" value="${dto.aiseq}">
		</form>
		<div style="clear: both;"></div>
		<br>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</main>
	<script>
	
	function memberOX()  {
		  var pw = prompt("비밀번호를 입력해주세요");
		  if(pw=="${dto.pw}"){
			  location.href="http://localhost:8090/ssy/member/memberpage4ok.do";
		  }else{
			  alert("비밀번호가 일치하지 않습니다.");
		  }
	
		}
	</script>
</body>
</html>