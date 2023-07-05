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
   	.btn {
   		width : 250px;
   		height: 250px;
   		margin: 60px;
   		font-size: 30px;
   		color: white;
   	}
   
   	.tbl {
		/* border: 1px solid #999;  */
		border-collapse: collapse;
		width: 1200px;
		height: 365px;
		text-align: center;
	}
	
	form {
		display:inline;
	}
	
	#btn1, #btn2, #btn3 { background-color: tomato; }
	
</style>
</head>
<body>
	<%@ include file="/WEB-INF/inc/header.jsp" %>

   <main>
      	
		
		<div class="tbl">
		
			<form method="GET" action="/ssy/account/memberlogin.do">
			      	<button type="submit" class="btn" id="btn1">
			      	<i class="fa-solid fa-user"></i>
			      		회원 로그인
			      	</button>			
			</form>
			<form method="GET" action="/ssy/account/sellerlogin.do">
			      	<button type="submit" class="btn" id="btn2">
			      	<i class="fa-solid fa-warehouse"></i>
			      		판매자 로그인
			      	</button>			
			</form>
			<form method="GET" action="/ssy/account/adminlogin.do">
			      	<button type="submit" class="btn" id="btn3">
			      	<i class="fa-solid fa-user-lock"></i>
			      		관리자 로그인
			      	</button>			
			</form>
			
			
			
			      	
			      	      			

		</div>	
      	
      	
      	<hr>
      	<%@ include file="/WEB-INF/inc/footer.jsp" %>
   </main>
   <script>
      
   </script>
</body>
</html>


