<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSY Food</title>
<%@ include file="/WEB-INF/inc/asset.jsp" %>
<style>
   #tbl1 {
   	width: 1100px;
   	margin: 0 auto;
   }
   
   #tbl1 th {
   	width: 160px;
   }
   
   #tbl1 td {
   	width: 680px;
   }
   
   #fqtitle {
   	display: block;
   	width: 900px;
   }
   
   #fqcontent {
   	display: block;
   	width: 900px;
   	height: 350px;
   }
   
   #btns {
   	margin-top: 30px;
   	width: 1200px;
   	text-align: center;
   }
   
   input[type=button], button[type=submit] {
   	width: 100px;
   	margin-right: 12px;
   	margin-bottom: 80px;
   }
</style>
</head>
<body>
   <main>
      <%@ include file="/WEB-INF/inc/header.jsp" %>
      
      <h2 style="margin: 40px 55px;">자주하는 질문</h2>
      	
      	<form method="POST" action="/ssy/faqboard/faqaddok.do">
      	<table id="tbl1" class="tbl1">
      		<tr>
      			<th>제목</th>
      			<td><input type="text" name="fqtitle" id="fqtitle" required></td>
      		</tr>
      		<tr>
      			<th>내용</th>
      			<td><textarea name="fqcontent" id="fqcontent" required></textarea></td>
      		</tr>
      	</table>
      	
      	<div id="btns">
      		<button type="submit">
      			<i class="fa-solid fa-pen-nib"></i>
      			등록
      		</button>
      		<input type="button" value="돌아가기" onclick="location.href='/ssy/faqboard/faqlist.do';"> 
      	</div>
      	</form>
      	
   </main>
   <script>
      
   </script>
</body>
</html>


