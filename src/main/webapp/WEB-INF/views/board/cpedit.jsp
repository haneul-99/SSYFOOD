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
   
   #cbtitle {
   	display: block;
   	width: 900px;
   }
   
   #cbcontent {
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
      
      <h2 style="margin: 40px 55px;">1:1 문의</h2>
      	
      	<form method="POST" action="/ssy/complainboard/cpeditok.do">
      	<table id="tbl1" class="tbl1">
      		<tr>
      			<th>제목</th>
      			<td><input type="text" name="cbtitle" id="cbtitle" required value="${dto.cbtitle}"></td>
      		</tr>
      		<tr>
      			<th>내용</th>
      			<td><textarea name="cbcontent" id="cbcontent" required>${dto.cbcontent}</textarea></td>
      		</tr>
      	</table>
      	
      	<div id="btns">
      		<input type="button" value="돌아가기" onclick="location.href='/ssy/complainboard/cplist.do';"> 
      		<button type="submit">
      			<i class="fa-solid fa-pen-to-square"></i>
      			수정하기
      		</button>
      	</div>
      	
      	<input type="hidden" name="cbseq" value="${dto.cbseq}">
      	
      	</form>
   </main>
   <script>
      
   </script>
</body>
</html>


