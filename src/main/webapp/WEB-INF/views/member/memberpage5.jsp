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

#ul-seller > li:nth-child(4) {
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
			<h1>작성 문의 목록</h1>
			<table class="tbl1">
				<tr>
					<th>번호</th>
					<th>상품</th>
					<th>문의내용</th>
					<th>작성 날짜</th>
					<th>자세히보기</th>
				</tr>
				
				<% int n = 1; %>
				<c:forEach items="${qList}" var="list">
				<tr>
					<td><%= n %></td>
					<td>${list[0]}</td>
					<td>${list[1]}</td>
					<td>${list[2]}</td>
					<td>${list[3]}</td>
					<td><input type="button" value="이동하기" onclick="location.href='/ssy/product/productdetail.do?pseq=${pseq}';"></td>
				</tr>
				<% n=n+1; %>
				</c:forEach>
			</table>
			<h1>작성 후기 목록</h1>
			<table class="tbl1">
				<tr>
					<th>번호</th>
					<th>상품</th>
					<th>문의내용</th>
					<th>작성 날짜</th>
					<th>자세히보기</th>
				</tr>
				
				<% int k = 1; %>
				<c:forEach items="${rvList}" var="list">
				<tr>
					<td><%= k %></td>
					<td>${list[0]}</td>
					<td>${list[1]}</td>
					<td>${list[2]}</td>
					<td><input type="button" value="이동하기" onClick="location.href='page.html';"></td>
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