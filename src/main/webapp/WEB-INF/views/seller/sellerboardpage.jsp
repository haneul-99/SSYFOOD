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
   	margin-bottom: 100px;
   }
   
   table {
   	width: 1200px;
   }
   
   #ul-seller > li:nth-child(3) {
    background-color: tomato;
    color: white;
    font-size: 1.3rem;
    font-weight: bold;
    box-sizing: border-box;
 }
</style>
</head>
<body>
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		<%@include file="/WEB-INF/inc/sellernav.jsp" %>
		
		<section>
		<h2>※ 판매자 문의게시판</h2>
		
			<table class= "board tbl1" id="sellerboard">
				<tr>
					<th>번호</th>
					<th>상품이름</th>
					<th>제목</th>
					<th>회원이름</th>
					<th>작성날짜</th>	
				</tr>
				<c:if test="${list.size() == 0}">
				<tr>
					<td colspan="5">게시물이 없습니다.</td>
				</tr>
				</c:if>
				
				<c:forEach items="${list}" var="dto" varStatus="status">
				<tr onclick="location.href = '/ssy/seller/sellerboardview.do?qseq=${dto.qseq}&qtitle=${dto.qtitle}&qcontent=${dto.qcontent}&pseq=${dto.pseq}&pname=${dto.pname}&mname=${dto.mname}';">
					<td style="cursor:pointer;" class="btn">${status.count}</td>
					<td style="cursor:pointer;" class="btn">${dto.pname}</td>
					<td style="cursor:pointer;" class="btn">${dto.qtitle}</td>
					<td style="cursor:pointer;" class="btn">${dto.mname}</td>
					<td style="cursor:pointer;" class="btn">${dto.qdate}</td>
				</tr>
				
				</c:forEach>
				
			</table>
			
		
		</section>
	   <%@include file="/WEB-INF/inc/footer.jsp"%>
     </main>
   
   <script>
   	
   
   
   </script>
</body>
</html>


