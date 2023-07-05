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
.head {
	font-weight: 1000;
	font-size: 20px;
	text-align: center;
	margin-top: 100px;
	margin-bottom: 20px;
}

.body {
	width: 500px;
	height: 100px;
	text-align: center;
	margin: auto;
	margin-bottom : 20px;
	border: 1px solid #999;
	padding-top: 50px;
	background-color: #EEE;
	border-radius: 30px;
}

.btn1 {
	display: inline;
	width: 230px;
	height: 40px;
	margin-bottom: 10px;
	
}

.btncontrol {
	margin-left: 30%;
	margin-right: 30%;
}

span {
	margin: 8px;
}
</style>
</head>
<body>

	<main>
	<%@ include file="/WEB-INF/inc/header.jsp"%>

		<div class="head">아이디 찾기</div>

		<div class="body">
			${name} 님의 아이디는 '${id}'입니다.<br><br>
			
			<span>최근 로그인 시각: ${log}</span>
		</div>
		
		<div class="btncontrol">
			<input type="button" class="btn1" value="로그인 선택화면으로 이동" onclick="location.href='/ssy/account/loginchoice.do'">
			<span></span>	
			<input type="button" class="btn1" value="비밀번호 찾기" onclick="location.href='/ssy/account/findpw.do'">
		</div>
			
	
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</main>
	<script>
		
	</script>
</body>
</html>


