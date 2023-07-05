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
h2 {
	margin-bottom: 30px;
	text-align: center;
}

#rbox {
	width: 300px;
	margin-left: 90px;
	margin-bottom: 30px;
	display: inline-block;
}

#input_btn {
	text-align: center;
}

#rtbl {
	width: 200px:
	text-align: center;
	margin-left: 9%;
	margin-right: 4%;
	margin-top: 40px;
	display: inline-block;
}

#rtbl td {
	display: inline;
}

#rbutton {
	width: 250px;
	margin-bottom: 70px;
}

input {
	width: 280px;
}
</style>
</head>
<body style="background-color: greenyellow;">
		
	<h2>R A N D O M B O X</h2>

	<img src="/ssy/asset/pic/randombox.png" id="rbox" class="rbutton">
	
	<form method="GET" action="/ssy/randompopup.do?mseq=${mseq}">
		<div id="input_btn">
				<button type="submit" id="rbutton">10000P 구매하기</button>
		</div>
		<input type="hidden" name="mseq" value="${mseq}">
		<input type="hidden" name="mpoint" value="${mpoint}">
	</form>
	
	<span>${mname} 님 현재 잔여 포인트: ${mpoint}P</span>
	
	<script>
	
   </script>
</body>
</html>


