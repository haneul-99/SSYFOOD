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
   table.tbl1 {
   
   margin-top: 50px ; 
   width: 1050px;
   }
   
   table.tbl1 td {font-size:1.4rem;}
   table.tbl1 td span {font-size:1.4rem;}
   .tbl1 th:nth-child(1){width:70px;}
   .tbl1 td:nth-child(1){ text-align:center;}
   .tbl1 th:nth-child(2){width:200px}
   .tbl1 th:nth-child(3){width:200px;}
   .tbl1 td:nth-child(3){text-align:center;}
   .tbl1 th:nth-child(4){width:130px;}
   .tbl1 td:nth-child(4){text-align:right;}
   .tbl1 th:nth-child(5){width:auto;}
   .tbl1 th:nth-child(6){width:130px;}
   .tbl1 td:nth-child(6){text-align:center;}
   .tbl1 td:nth-child(5){text-align:center;}
   .tbl1 td:nth-child(7){text-align:center;}
   .btns{
   text-align: right;
   padding-right: 50px;
   margin: 50px;
   }
   
   .btn {
   width: 150px;
   height: 70px;
   border:none;
   background-color: tomato;
   color:white;
   font-size: 1.5rem;
   
   }
   
   /*  */
input[type="checkbox"] {
width: 20px;
height: 20px;
}

input[type="number"] {
	width: 130px;
	height: 20px;
	font-size: 1.4rem;
	text-align: right;
	

}

td{
position:relative;

}

   .btnDel{
   position:absolute;
   right: -50px;
   background-color:tomato;
   color:white;
   font-size: 1.3rem;
   border:none;
   }
   
</style>
</head>
<body>
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		
		<h1> ${mname}님의 찜목록</h1>
		
		<table class="tbl1">
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>상품이미지</th>
				<th>가격</th>
				<th>장바구니에 담기</th>
			</tr>
				<c:if test="${jList.size() == 0}">
			<tr>
				<td colspan="7">찜한 상품이 없습니다.</td>
			</tr>
				</c:if>
			
			<c:forEach items="${jList}" var="dto" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${dto.pname}</td>
				<td> <img src="/ssy/asset/images/${dto.pimage}" width="150" height="150"> </td>
				<td>
					 <span id="span-price${status.count}" class="cl-price"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/><span>원</span></span>
				</td>
				<td>  
					<button onclick="if(confirm('장바구니에 담으시겠습니까?'))location.href='/ssy/member/bucketok.do?pseq=${dto.pseq}&bprice=${dto.pprice * (100-dto.pdiscount)/100}&jseq=${dto.jseq}';"> 장바구니에 담기  </button>
					<button type="button" class="btnDel" onclick="if(confirm('찜목록에서 삭제하시겠습니까?'))location.href='/ssy/member/jjimdelok.do?jseq=${dto.jseq}';"><i class="fa-solid fa-trash"></i></button>
				</td>
			</tr>
		</c:forEach>
				
		</table>
		<div class="btns">
			<button type="button" class="btn" onclick="history.back()">뒤로가기</button>
		</div> 
		
		
		
		
     </main>
   
   
   <script>
   
   
   
   
   
   
   
   
   
   
   	
    function number_format(num){
	       return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',');
	   }
    
//     	$('#lastPrice').ready(function(){
    	
    		
    		
//     	$('#lastPrice').append(number_format(sum) + '원');
// 			$('#lastPrice').append(sum); 
//     	let totalprice = 0;
    	
    	
    
  
   </script>
</body>
</html>


