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
   #pagebar {
	margin: 20px;
	text-align: center;
}

#edit{
	margin:0 auto;
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
     
     <%@ include file="/WEB-INF/inc/adminnav.jsp"%>
     
		
		<button onclick = "location.href ='/ssy/admin/pointselect.do'">회원 보유 포인트 조회</button>
				<button onclick = "location.href ='/ssy/admin/couponinsert.do'">쿠폰 등록</button>
		
		     <h3>쿠폰 등록</h3>
      		<table id = "coupon">
      			<tr>
      				<th>쿠폰이름</th>
      				<th>사용기간</th>
      				<th>사용조건</th>
      				<th>할인율</th>
      				<th>쿠폰번호</th>
      				<th>모든 회원 일괄 지급</th>
      				
      			</tr>
      		<c:forEach items="${colist}" var="codto">
      				<tr>
							<td>${codto.coname}</td>
							<td>${codto.codate}까지</td>
							<td>${codto.cocontent}</td>
							<td>${codto.codiscount}</td>
							<td>${codto.coseq}</td>
							<td>
								<form method = "POST" action ="/ssy/admin/couponinsertallok.do">
								<button type = "submit">지급</button>
								<input type = "hidden" name = "coseq" value = "${codto.coseq}">
								<input type = "hidden" name = "aiseq" value = "${dto.aiseq }">
								
								</form>
							</td>
							
					</tr>
      		</c:forEach>
      		
      		</table>
      	<div id="pagebar">${pagebar}</div>
      	
      	<form method = "POST" action = "/ssy/admin/couponinsertok.do" id = "edit">
      	<div>쿠폰이름:<input type = "text" name = "coname" id = "coname"></div>
      	<div>할인율:<input type = "text" name = "codiscount" id = "codiscount"></div>
      	<div>사용조건:<input type = "text" name = "cocontent" id = "cocontent"></div>
      	<div>날짜:<input type = "text" name = "codate" id = "codate"></div>
      	<button type = "submit" onclick = "$('#insertCoupon').click()">쿠폰 등록 하기</button>
      	</form>
					
					    

		
		
      <%@include file="/WEB-INF/inc/footer.jsp"%>
     </main>
   
   
   <script>
      
   </script>
</body>
</html>


