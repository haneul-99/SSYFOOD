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
	width: 600px;
}

#tbl1 {
	text-align: center;
}

#ul-seller > li:nth-child(1) {
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
		<div id="my">
			<h1>회원 마이 페이지</h1>
			<table id="tbl1"  class="tbl1">
				<tr>
					<th>보유 포인트</th>
					<td>${dto.mpoint}P</td>
				</tr>
				<tr>
				
					<th>보유 쿠폰 개수
					<td> ${listlen}장</td>
				</tr>
				
			</table>
			<h1>회원 팔로우 목록</h1>
			<table id="tbl1" class="tbl1">
				<tr>
					<th>번호</th>
					<th>판매자 이름</th>
					<th>판매자 상품 보러가기</th>
				</tr>
				
					<% int n = 1; %>
					<c:forEach items="${list}" var="sname">
					<tr>
						<th><%= n %></th>
						<td>${sname}</td>
						<td><input type="button" value="이동하기" onClick="location.href='page.html';"></td>
					</tr>
					<% n=n+1; %>
					</c:forEach>
			</table>
			<h1>보유 쿠폰 현황</h1>
			<table id="tbl1" class="tbl1">
				<tr>
					<th>번호</th>
					<th>쿠폰명</th>
					<th>할인율</th>
					<th>쿠폰내용</th>
					<th>유효기간</th>
				</tr>
				
				<c:forEach items="${coList}" var="list">
				<% int k = 1; %>
				<tr>
					<td><%= k %></td>
					<c:forEach items="${list}" var="content">
					<td>${content}</td>
					</c:forEach>
				</tr>
				<% k=k+1; %>
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


