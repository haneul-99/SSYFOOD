<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/inc/asset.jsp"%>
<style>
h2 {
	margin-left: 100px;
	margin-top: 50px;
	margin-bottom: 50px;
}

table {
	width: 1200px;
	margin: 0 auto;
}

table, td, th {
	border-collapse: collapse;
	text-align: center;
}

tr>th:nth-child(1) {
	width: 60px;
}

tr>th:nth-child(2) {
	width: 120px;
}

tr>th:nth-child(3) {
	width: 200px;
}

tr>th:nth-child(4) {
	width: 350px;
}

tr>th:nth-child(5) {
	width: 100px;
}

tr>th:nth-child(6) {
	width: 150px;
}

tr>td:nth-child(6) {
	font-size: 15px;
}

tr>th:nth-child(7) {
	width: 150px;
}

#search {
	margin: 0 auto;
}

#search table {
	width: 600px;
	margin: 20px auto;
	border: 0;
}

#search table td {
	border: 0;
	padding: 5px;
}

#pagebar {
	margin: 20px;
	text-align: center;
}
</style>
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/inc/header.jsp"%>

<%@ include file="/WEB-INF/inc/adminnav.jsp"%>

		<section>
			<h2>회원 관리 페이지</h2>
			
				<table class="tbl1">
					<tr>
						<th>회원번호</th>
						<th>회원이름</th>
						<th>회원아이디</th>
						<th>주소</th>
						<th>최근 구매일</th>
						<th>연락처</th>
						<th>계정</th>
					</tr>
					<c:forEach items="${list}" var="dto">
						<tr>
							<td>${dto.mseq}</td>
							<td>${dto.mname}</td>
							<td>${dto.aiid }</td>
							<td>${dto.maddress}</td>
							<td>2022-12-20</td>
							<!-- 없음 -->
							<td>${dto.mtel}</td>
								<td>
								<button  onclick="onDispaly()">수정 제발 수정</button>
							<form method="POST" action="/ssy/admin/adminpagedelok.do">	
							 	<input type="hidden" name="mseq" value="${dto.mseq}">	
								<button type="submit">삭제!제발</button> 
								</form>
								</td>
						</tr>
						
						<tr id = "noneDiv" style ="display:none;">
						<td>보여지기</td>
						</tr>
					</c:forEach>
				</table>
			

			<div id="pagebar">${pagebar}</div>

			<div id="search">
				<!-- 검색!!! method="GET" -->
				<form method="GET" action="/ssy/admin/adminpage.do">
					<table>
						<tr>
							<td><select name="column" id="column">
									<option value="mname">회원이름</option>
									<option value="aiid">회원아이디</option>
									<option value="maddress">주소</option>
									<option value="mtel">연락처</option>
									<option value="all">모두</option>
							</select></td>
							<td><input type="text" name="word" id="word" required
								autocomplete="off"></td>
							<td>
								<button type="submit">
									<i class="fa-solid fa-magnifying-glass"></i> 검색하기
								</button>
								<button type="button"
									onclick="location.href='/ssy/admin/adminpage.do';">
									<i class="fa-solid fa-list"></i> 검색취소
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>


		</section>


		<%@ include file="/WEB-INF/inc/footer.jsp"%>



	</main>
	<script>

   <c:if test="${map.isSearch == 'y'}">
   $('#column').val('${map.column}');
   $('#word').val('${map.word}');
   </c:if>
   
   
   $('#selpage').val(${nowPage});
   
   function onDisplay(){
	   $('#noneDiv').show();
   }
   </script>
</body>
</html>

