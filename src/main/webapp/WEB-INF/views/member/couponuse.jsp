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
</style>
</head>
<body>
	<main>
		<% int n = 1; %>
		<table  class="tbl1">
			<c:forEach items="${coList}" var="list">
			<tr>
				<td><%= n %></td>
			<c:forEach items="${list}" var="content">
				<td>${content}</td>
			</c:forEach>
			</tr>
			<% n=n+1; %>
			</c:forEach>
		</table>
	</main>
	<script>
      
   </script>
</body>
</html>