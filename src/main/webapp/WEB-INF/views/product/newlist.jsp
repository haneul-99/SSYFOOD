<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSY Food</title>
<%@ include file="/WEB-INF/inc/asset.jsp" %>
<style>
   .items{
   	width: 200px;
   	padding:5px;
   	margin: 0 40px 70px 50px;
   }
   
   .items-img {
   	width:200px;
   	height:200px;
   	border-radius: 5px;
   }
   
   #main {
   	margin-top: 50px;
   	display:flex;
   	justify-content: start;
   	flex-wrap:wrap;
   }
   
   .items-info{
   	margin:3px 0;
   }
   
   .items-info > div{
   	margin:5px 0;
   }

   .discount {
   	font-size: 1rem;
   	color:tomato;
   	margin-left: 5px;
   	padding-left: 10px;
   	border-left:1px solid #DDD;
   
   }
   .storename{
   	font-weight:bold;
   }
   
   img {
	width:1200px;
	height: 300px;
   }
   

</style>
</head>
<body>
   <main>
      <%@include file="/WEB-INF/inc/header.jsp" %>
      
      <img src="/ssy/asset/images/banner01.png">
      
      <h1 style="text-align: center; margin: 35px;">신상품</h1>
      
      <div id="main">
      
		<c:forEach items="${newlist}" var="dto">
		
			<div class="items">
				<div class="items" onclick="location.href='/ssy/product/productdetail.do?pseq=${dto.pseq}';" style="cursor:pointer;">
				<img alt="" src="/ssy/asset/images/${dto.pimage}" class="items-img">
				<div class="items-info">
				<div class="storename">${dto.storename}</div>
				<div>${dto.pname}</div>
				<div>
				
				<c:if test="${dto.pdiscount == 0}"> 
				<span class="origin-price"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/><span>원</span> </span>
				</c:if>
				
				<c:if test="${dto.pdiscount != 0}"> 
				<span class="origin-price" style="text-decoration:line-through; color: #999"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/> <span>원</span></span>
				→ <span><fmt:formatNumber value="${dto.pprice*(1-dto.pdiscount/100)}" pattern="#,###"/>원</span>  <span class="discount">${dto.pdiscount}%</span> 
				</c:if>
				 
				</div>
				</div>
				</div>
			</div>
			
			</c:forEach>
		</div>
   </main>
   <script>
      
   </script>
</body>
</html>


