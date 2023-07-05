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
   #register_product {
      text-align: right;
      padding-right: 50px;
   
   
   }
       #register_product> a {
       font-size: 2rem;
       background-color:tomato;
       border-radius: 10px;
       color:white;
       height: 30px;
       border: 2px dashed white;
       box-shadow: 0 0 0 5px tomato;
       }
       
       
       
       #ul-seller > li:nth-child(1) {
    background-color: tomato;
    color: white;
    font-size: 1.3rem;
    font-weight: bold;
    box-sizing: border-box;
 }
 .items {
 position:relative;
 }
 
 .deleteItem {
    width: 250px;
    height: 100px;
    transform: rotate(70deg);
    position:absolute;
    background-color:red;
    color:gold;
    top: 100px;
    display:flex;
    font-size: 2rem;
    align-items:center;
    justify-content:center;
    right: -10px;
    
 
 }
 
 
</style>
</head>
<body>
     <main>
      <%@include file="/WEB-INF/inc/header.jsp" %>
      
   <%@include file="/WEB-INF/inc/sellernav.jsp" %>
         
         <h1>${sName}님의 상품 정보</h1>
      <section>
      <c:forEach items="${sList}" var="dto">
         <div class="items" onclick="location.href='#!'" style="cursor:pointer;">
            <c:if test="${dto.post == 'Y'}">
            <img alt="" src="/ssy/asset/images/${dto.pimage}" class="items-img">
            </c:if>
            <c:if test="${dto.post == 'N'}">
            <img alt="" src="/ssy/asset/images/${dto.pimage}" class="items-img"> <div class="deleteItem">삭제된 아이템</div>
            </c:if>
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
            
            <c:if test="${dto.post == 'Y'}">
            <button onclick="location.href='/ssy/product/productedit.do?pSeq=${dto.pseq}&pImage=${dto.pimage}&sSeq=${sseq}';"><i class="fa-solid fa-pen"></i></button>
            <button onclick="del(${dto.pseq},'${dto.pimage}',${dto.sseq})"><i class="fa-solid fa-trash"></i></button>
            </c:if>
         </div>
         </c:forEach>
      </section>
      
      <div id="register_product"> <a href="/ssy/product/registerproduct.do?sSeq=${sseq}">상품등록</a> </div>
     </main>
   
   
   <script>
   
   <c:if test="${lv == 1}">
   alert('판매자/관리자만 접근이 가능합니다.');
   history.back()
   </c:if>
   
      function del(seq,image,sseq){
//          alert(seq);

      if(confirm('상품을 삭제하시겠습니까?')){
         
         location.href='/ssy/product/productdelok.do?pSeq='+seq + '&pImage=' + image+ '&sSeq=' + sseq;
         
      };
         
      }   
   
   
   
   </script>
</body>
</html>

