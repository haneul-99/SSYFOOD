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
   
   section {
   	display:flex;
   	flex-wrap:wrap;
   	justify-content:center;
   
   }
   .div-category{
   width: 250px;
   	height: 250px;
   	border-bottom: 5px solid #EEE;
    display:flex;
    justify-content:center;
    cursor:pointer;
    margin: 50px;
    opacity:.5;
    border-radius:20px;
    
   }
   
   .div-category>div {
   
   color:black;
   	font-weight: bold;
   	font-size: 3.5rem;
   	vertical-align:center;
   	display:flex;
   	align-items:center;
   	justify-content:center;
   	text-align:center;
   }
   
   .div-category:hover{
   opacity:1;
   }
   div>img {
   width: 350px;
   height: 200px;
   
   }
   
   #div-meat{
   	background-image: url('/ssy/asset/images/고기.webp');
   	background-position: center;
   	background-size: contain;
   }
   #div-sea{
   	background-image: url('/ssy/asset/images/해산물1.jpg');
   	background-size: contain;
   	background-position: center;
   }
   #div-kook{
   	background-image: url('/ssy/asset/images/밥.webp');
   	background-size: contain;
   	background-position: center;
   	}
   #div-noodle{
   	background-image: url('/ssy/asset/images/샐러드.webp');
   	background-position: center;
   	background-repeat: no-repeat;
   	background-size: 90%;
   	
   		}
   #div-alcohol{
   	background-image: url('/ssy/asset/images/술1.jpg');
   	background-position: center;
   		background-size: contain;
   	}
   #div-fruit{
   	background-image: url('/ssy/asset/images/과일1.jpg');
   	background-size: 50%;
   	background-repeat: no-repeat;
   	background-position: center;
   	}
   
</style>
</head>
<body>
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
      <section>
      <div class="div-category" id="div-meat" onclick="location.href='/ssy/product/product.do?cSeq=1&word='">
		    <div>정육</div>
<!-- 		    <div><img src="/ssy/asset/images/고기그림.jpg"></div> -->
      </div>
      
      <div class="div-category" id="div-sea"  onclick="location.href='/ssy/product/product.do?cSeq=2&word='">
		      <div>해산물</div>
<!-- 		        <div><img src="/ssy/asset/images/해산물.jpg"></div> -->
      </div>
      
      <div class="div-category" id="div-fruit" onclick="location.href='/ssy/product/product.do?cSeq=3&word='">
		  <div>과일</div>  
<!-- 		    <div><img src="/ssy/asset/images/과일.jpg"></div>  -->
      </div>
      
      <div class="div-category" id="div-kook" onclick="location.href='/ssy/product/product.do?cSeq=4&word='">
      	<div>국과 반찬</div>
<!--       	  <div><img src="/ssy/asset/images/국.jpg"></div> -->
      </div>
      
      <div class="div-category" id="div-noodle" onclick="location.href='/ssy/product/product.do?cSeq=5&word='">
      		<div>면 /<br>샐러드</div>
<!--       		  <div><img src="/ssy/asset/images/라멘.jpg"></div> -->
      </div>
      
      <div class="div-category" id="div-alcohol" onclick="location.href='/ssy/product/product.do?cSeq=6&word='">
      	<div>주류</div>
<!--       	  <div><img src="/ssy/asset/images/술.jpg"></div> -->
      </div>
      
      <c:if test="${not empty auth and lv == 1}">
	      <%@include file="/WEB-INF/inc/randombox.jsp" %>
      </c:if>
      
      </section>
      	
     </main>
       <%@include file="/WEB-INF/inc/footer.jsp"%>
   
   
   <script>
      
   </script>
</body>
</html>


