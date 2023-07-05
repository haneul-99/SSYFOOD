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
</style>
</head>
<body>
	<%@include file="/WEB-INF/inc/header.jsp"%>
	<main>

		<div id="mpmenu">
			<ul>
				<li onclick="location.href='/ssy/member/memberpage.do';"><span>포인트<br>쿠폰함</span></li>
				<li onclick="location.href='/ssy/member/memberpage1.do';"><span>주문내역</span></li>
<!-- 				<li onclick="location.href='/ssy/member/memberpage2.do';" style="background-color:#ccc"><span>장바구니<br>페이지</span></li>
				<li onclick="location.href='/ssy/member/memberpage3.do';"><span>찜목록</span></li> -->
				<li onclick="location.href='/ssy/member/memberpage4.do';"><span>개인정보<br>수정</span></li>
				<li onclick="location.href='/ssy/member/memberpage5.do';"><span>댓글관리</span></li>
			</ul>
		</div>
		<!-- mpmenu -->
		<div id="my">
			<h1>장바구니</h1>
			<table>
				<tr>
					<th>번호</th>
					<th>상품명</th>
					<th>상품이미지</th>
					<th>금액</th>
					<th>수량</th>
					<th>결제</th>
				</tr>
				<% int n = 1; %>
				<c:forEach items="${buList}" var="list">
				<tr>
					<td><%= n %></td>
					<td>${list[0]}</td>
					<td><img src="/images/${list[1]}"></td>					
					<td>${list[2]}</td>
					<td><input type="number" value=1 name="quantity" style="width:40px;"></td>
					<td><input type="button" value="결제하기"></td>
				</tr>
				<% n=n+1; %>
				</c:forEach>
			</table>
			
		</div>
		<div style="clear: both;"></div>
		<br>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</main>
	<script>
		
	</script>
</body>
</html>