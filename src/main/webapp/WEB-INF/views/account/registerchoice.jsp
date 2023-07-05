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
   .btn1{ 
   	margin: 50px; 
   	width: 300px;
   	height: 300px;
   	background-color:tomato;
   	color: white;
   	opacity: .7;
   }
   button:hover {
   	opacity: 1;
   }
   
   .btns {
   text-align:center;
   	margin-top: 150x; 
   }
   .btns > button > i {
   font-size: 5rem;
   }
   .info{
   	margin-top:15px;
   }
   .info>span{
   	font-size: 2rem;
   	display:block;
   	font-weight:bold
   }
</style>
</head>
<body>
   <main>
      <%@ include file="/WEB-INF/inc/header.jsp" %>
      
      <div class="btns">
	      <button type="button" class="btn1"onclick="location.href='/ssy/account/memberregister.do';">
	      <i class="fa-solid fa-user"></i>
	      	<div class="info"><span>회원</span> <span>회원가입</span></div>
	      </button>
	      <button type="button" class="btn1" onclick="location.href='/ssy/account/sellerregister.do';">
	      	<i class="fa-solid fa-warehouse"></i>
	        	<div class="info"><span>판매자</span> <span>회원가입</span></div> 
	      </button>
      </div>
      
   </main>
   <script>
      
   </script>
</body>
</html>