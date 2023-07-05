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

#result_btn {
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
}

input {
	width: 280px;
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
	margin-bottom: 5px;
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

#warn {
	margin-left: 45px;
	color: red;
	font-size: 16px;
}
</style>
</head>
<body style="background-color: greenyellow;">
		
	<h2>R A N D O M B O X</h2>

	<%-- <img src="/ssy/asset/pic/randombox.png" id="rbox" class="rbutton">
	
		<div id="result_btn">
			<button type="button" id="rbutton">결과확인</button>
		</div>
		
		<form method="get" action="/ssy/randomboom.do?mseq=${mseq}">
			<div id="hidden_btn">
				<button type="submit">보관함으로 이동</button>
				<input type="button" value="나가기" onclick="window.close();">
				<input type="hidden" name="mseq" value="${mseq}">
				<input type="hidden" name="content" value="${content}">
			</div>
		</form> --%>

	<table class="tbl1">
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
	
		<table id="rtbl" class="tbl1">
			<tr>
				<td>당첨상품</td>
				<td>&nbsp;:&nbsp;${content}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" value="${maddress}" name="address">
					<button type="button" name="rwd" id="submitrwd" onclick="alert('주소입력이 완료되었습니다. 2~3영업일 이내에 배송이 시작됩니다.'); window.close();">주소입력</button>
				</td>
			</tr>
		</table>
		<span id="warn">※주의: 주소입력을 하지않으면 당첨 상품이 배송되지 않습니다.</span>
		<input type="hidden" name="mseq" value="${mseq}">


	<script>
	
    
   </script>
</body>
</html>


