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

#search table td {
	border: 0;
	padding: 5px;
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
			
			<table>
				<tr>
					<th>회원이름</th>
					<th>회원아이디</th>
					<th>현재 보유 포인트</th>
				</tr>
			<c:forEach items ="${polist }" var = "podto" >
				<tr>
					<td>${podto.mname }</td>
					<td>${podto.aiid }</td>
					<td>${podto.mpoint }</td>
				</tr>
			</c:forEach>
			</table>
		
		<div id="pagebar">${pagebar}</div>

			<div id="search">
				<!-- 검색!!! method="GET" -->
				<form method="GET" action="/ssy/admin/pointselect.do">
					<table>
						<tr>
							<td><select name="column" id="column">
									<option value="mname">회원이름</option>
									<option value="aiid">회원아이디</option>
									<option value = "all">모두</option>
									
							</select></td>
							<td><input type="text" name="word" id="word" required
								autocomplete="off"></td>
							<td>
								<button type="submit">
									<i class="fa-solid fa-magnifying-glass"></i> 검색하기
								</button>
								<button type="button"
									onclick="location.href='/ssy/admin/pointselect.do';">
									<i class="fa-solid fa-list"></i> 검색취소
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		
      <%@include file="/WEB-INF/inc/footer.jsp"%>
     </main>
   
   
   <script>
   <c:if test="${map.isSearch == 'y'}">
   $('#column').val('${map.column}');
   $('#word').val('${map.word}');
   </c:if>
   
   
   $('#selpage').val(${nowPage});
   
   </script>
</body>
</html>


