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
   	#btns{
   	margin:auto 0;
   	}
   	
   	#ul-seller > li:nth-child(4) {
	 	background-color: tomato;
	 	color: white;
	 	font-size: 1.3rem;
	 	font-weight: bold;
}
</style>
</head>
<body>
   
   
     <main>
		<%@ include file="/WEB-INF/inc/header.jsp" %>
		
		
		<%@ include file="/WEB-INF/inc/adminnav.jsp" %>
				<div id = "btns">
				<button  onclick = "location.href ='/ssy/admin/pointselect.do'">회원 보유 포인트 조회</button>
				<button onclick = "location.href ='/ssy/admin/couponinsert.do'">쿠폰 등록</button>
     				</div>
      <%@include file="/WEB-INF/inc/footer.jsp"%>
     </main>
   
   
   <script>
      
   </script>
</body>
</html>


