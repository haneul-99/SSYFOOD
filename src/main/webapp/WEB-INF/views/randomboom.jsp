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
	margin-bottom: 50px;
	text-align: center;
}

#rboom {
	width: 100px;
	margin: 0px auto;
	display: inline-block;
}

#reward {
	width: 250px;
	margin-top: 30px;
	display: inline-block;
	text-align: center;
	font-size: 25px;
}

#rtbl {
	width: 300px:
	text-align: center;
	margin-left: 9%;
	margin-right: 4%;
	margin-top: 40px;
	display: inline-block;
}

#rtbl td {
	display: inline;
}

input {
	width: 280px;
}

span {
	margin-left: 100px;
}
</style>
</head>
<body style="background-color: greenyellow;">
		
	<h2>당첨상품 보관함</h2>
	
	<table>
		<tr>
			<td rowspan="2">
				<img src="/ssy/asset/pic/rightboom.png" id="rboom">
			</td>
			<td id="reward">당첨을 축하드립니다 !!</td>
			<td rowspan="2">
				<img src="/ssy/asset/pic/leftboom.png" id="rboom">
			</td>
		</tr>
	</table>
	
	<form method="post" action="/ssy/randomboomok.do">
		<table id="rtbl" class="tbl1">
			<tr>
				<td>당첨상품</td>
				<td>&nbsp;:&nbsp;${content}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" value="${mdto.maddress}" name="address">
					<button type="submit" name="rwd" id="submitrwd">주소입력</button>
									
				</td>
			</tr>
		</table>
		<input type="hidden" name="mseq" value="${mseq}">
	</form>
	
	
	<script>
	
    	  
   </script>
</body>
</html>


