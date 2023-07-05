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

#ul-seller > li:nth-child(2) {
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
			<h1>주문 내역</h1>
			<table id="tbl1" class="tbl1">
				<tr>
					<th>번호</th>
					<th>상품명</th>
					<th>상품이미지</th>
					<th>금액</th>
					<th>배송지</th>
					<th>수령인</th>
					<th>주문날짜</th>
				</tr>
				<% int n = 1; %>
				<c:forEach items="${olList}" var="list">
				<tr>
					<td><%= n %></td>					
					<td>${list.oname}</td>		
					<td><img src="/ssy/asset/images/${list.oimage}" style="width:50px;"></td>		
					<td>${list.oprice}</td>		
					<td>${list.oaddress}</td>		
					<td>${list.mname}</td>			
					<td>${list.odate}</td>		
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