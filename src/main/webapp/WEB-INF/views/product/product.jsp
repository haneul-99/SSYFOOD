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
   
   section {
   
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
   .items-info >div:nth-child(2){
   		
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
   
   #product-ul {
   
   	list-style-type: none;
   	font-size: 2rem;
   
   	position:fixed;
   	
   }
   
   #product-ul > li {
	   text-align: center;
	   margin: 10px 0;
	   border: 2px solid #DDD;
	   cursor:pointer;
   }
   
   
   li> i {
   	transform: translate(0px, 2px);
   
   }
   
  section{
   position:relative;
   }
   
    #product-ul > li:nth-child(${cSeq}) {
    	color: tomato;
    
    }
    .iconbutton{
    	position:fixed;
    	right:50px;
    	bottom:50px;
    	background-color:transparent;
    	font-size: 1.5rem;
    	border-radius: 50%;
    	width: 50px;
    	height:50px;
    	border: 5px solid #FFF;
    }
</style>
</head>
<body>
   
     <main>
     	
		<%@include file="/WEB-INF/inc/header.jsp" %>
		<%@include file="/WEB-INF/inc/categorynav.jsp" %>
		<section>
		<button class= "iconbutton"><i class="fa-solid fa-up-long"></i></button>
		<c:forEach items="${pList}" var="dto">
		
			<div class="items" onclick="location.href='/ssy/product/productdetail.do?pseq=${dto.pseq}';" style="cursor:pointer;">
				<img alt="" src="/ssy/asset/images/${dto.pimage}" class="items-img">
				<div class="items-info">
				<div class="storename">${dto.storename}</div>
				<div>${dto.pname}</div>
				<div>
				
				<c:if test="${dto.pdiscount == 0}"> 
				<span class="origin-price"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/> <span>원</span> </span>
				</c:if>
				
				<c:if test="${dto.pdiscount != 0}"> 
				<span class="origin-price" style="text-decoration:line-through; color: #999"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/> <span>원</span> </span>
				→ <span><fmt:formatNumber value="${dto.pprice*(1-dto.pdiscount/100)}" pattern="#,###"/>원</span>  <span class="discount">${dto.pdiscount}%</span> 
				</c:if> 
				
				</div>
				</div>
			</div>
			
		</c:forEach>
		
		</section>
		
		
     </main>
   <%@include file="/WEB-INF/inc/footer.jsp"%>
   
   <script>

   $(document).ready(function() {
       console.log($(this).scrollTop());
       if ($(this).scrollTop() == 0) {
           $(".iconbutton").hide();
       }

       $(document).scroll(function() {
           if ($(this).scrollTop() > 0) {
               $(".iconbutton").show();
           } else {
               $(".iconbutton").hide();
           }
       })

       $(".iconbutton").click(function() {
           //                            바디 스크롤 제일 상위로 이동
           $("html,body").animate({
               scrollTop: 0
           }, 150)
       })

   })

   </script>
</body>
</html>


