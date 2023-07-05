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
   #ul-seller > li:nth-child(4) {
	 	background-color: tomato;
	 	color: white;
	 	font-size: 1.3rem;
	 	font-weight: bold;
 	}
 	
 	.tbl1{
 		width: 1200px;
 		margin: 50px auto;
 	}
 	.tbl1 th:nth-child(1){width:40px;}
 	.tbl1 td:nth-child(1){text-align:center;}
 	
 	.tbl1 th:nth-child(2){width:150px;}
 	.tbl1 th:nth-child(3){width:80px;}
 	.tbl1 th:nth-child(4){width:120px;}
 	.tbl1 th:nth-child(5){width:80px}
 	.tbl1 th:nth-child(6){width:150px;}
 	.tbl1 th:nth-child(7){width:auto}
 	.tbl1 th:nth-child(8){width:auto}
 	
 	.tbl1 td:nth-child(11){position:relative;}
 	
 	
 	.editState{
 	
 		position:absolute;
 		right:-60px;
 	}
 	
 	#pagebar{
 	 font-size: 1.8rem;
 	 text-align:center;
 	
 	}
 	#pagebar> a {
 		margin: 0 10px;
 	
 	}
</style>
</head>
<body>
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		 <%@include file="/WEB-INF/inc/sellernav.jsp" %>
		 
		 <section>
		 	
		 	<table class="tbl1" id="tbl1">
		 		<tr>
		 			<th>번호</th>
		 			<th>상품이름</th>
		 			<th>주문자 이름</th>
		 			<th>주문자 연락처</th>
		 			<th>수령자 이름</th>
		 			<th>수령자 주소</th>
		 			<th>상품가격(원)</th>
		 			<th>구매수량(개)</th>
		 			<th>총 구매가격(원)</th>
		 			<th>결제일자</th>
		 			<th>주문상태</th>
		 		</tr>
		 		<c:forEach items="${oList}" var="dto" varStatus="status">
					<tr>
						<td id="count">${dto.olseq}</td>
						<td>${dto.pname}</td>
						<td>${dto.mname}</td>
						<td>${dto.mtel}</td>
						<td>${dto.olname}</td>
						<td>${dto.oladdress}</td>
						<td><fmt:formatNumber value="${dto.odprice}" pattern="#,###"/></td>
						<td>${dto.odquantity}</td>
						<td><fmt:formatNumber value="${dto.odprice * dto.odquantity}" pattern="#,###"/></td>
						<td>${dto.oldate}</td>
						<td>
						<select name="stateSel" id="stateSel${status.count}" onchange="$('#editState${status.count}').show()">
						<c:if test="${dto.olstate == '결제완료'}">
							<option value="결제완료" selected>결제완료</option>
							<option value="배송준비중">배송준비중</option>
							<option value="배송중">배송중</option>
							<option value="배송완료">배송완료</option>
						</c:if>
						<c:if test="${dto.olstate == '배송준비중'}">
							<option value="결제완료">결제완료</option>
							<option value="배송준비중" selected>배송준비중</option>
							<option value="배송중">배송중</option>
							<option value="배송완료">배송완료</option>
						</c:if>
						<c:if test="${dto.olstate == '배송중'}">
							<option value="결제완료">결제완료</option>
							<option value="배송준비중">배송준비중</option>
							<option value="배송중" selected>배송중</option>
							<option value="배송완료">배송완료</option>
						</c:if>
						<c:if test="${dto.olstate == '배송완료'}">
							<option value="결제완료">결제완료</option>
							<option value="배송준비중">배송준비중</option>
							<option value="배송중">배송중</option>
							<option value="배송완료" selected>배송완료</option>
						</c:if>
						</select>
							<button name="editState"  id="editState${status.count}" class="editState" onclick="location.href='/ssy/seller/editstate.do?olseq=${dto.olseq}&olstate='+$('#stateSel${status.count}').val();">수정</button>
							</td>
						</tr>		 	
		 		</c:forEach>
		 	</table>
		 	<div id="pagebar">${pagebar}</div>
		 
		 
		 
		 
		 </section>
		 
     </main>
   
   
   <script>

   
   let n = 0;
   $('.editState').hide();
	 
			   	
	   
	   
   
      
   </script>
</body>
</html>


