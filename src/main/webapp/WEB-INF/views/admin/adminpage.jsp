<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSY Food</title>
<%@ include file="/WEB-INF/inc/asset.jsp"%>
<style>
h2 {
	margin-left: 100px;
	margin-top: 50px;
	margin-bottom: 50px;
}

table.tbl1 {
   border: 1px solid #999;
   border-collapse: collapse;   
}

table.tbl1 th, table.tbl1 td {
   border: 1px solid #999;
   padding: 7px;
}

table.tbl1 th {
   background-color: #DDD;   
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
	width: 80px;
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
	width: 200px;
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

#editinfo{
	text-align: 
}

#ul-seller > li:nth-child(1) {
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


		<%@ include file="/WEB-INF/inc/adminnav.jsp" %>

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
								
									<button class = "button" value = "${dto.mseq}" onclick="$('#${dto.mseq}').show()" style="display: inline-block" >수정</button>
									<form method="POST" action="/ssy/admin/adminpagedelok.do" style="display: inline-block">	
							 	
										<input type="hidden" name="mseq" value="${dto.mseq}">	
									<button type="submit" id = "insert_btn">삭제</button> 
								
								</form>
								</td>
							</tr>
						
							<tr class = "editbtn" id = "${dto.mseq}" style = "display: none">
							
							<td colspan='7'>
								수정내용 
							<div id = "editinfo">
							<form method = "POST" action = "/ssy/admin/adminpageeditok.do">
							<div><input type = "text" name = "mname" id = "mname" value="${dto.mname}"></div>
							<div><input type = "text" name = "aiid" id = "aiid" value="${dto.aiid}"></div> 
							<div><input type = "text" name = "aipw" id = "aipw" value="${dto.aipw}"></div> 
							<div><input type = "text" name = "maddress" id = "maddress" value="${dto.maddress}"></div> 
							<div><input type = "text" name = "mtel" id = "mtel" value="${dto.mtel}"></div>
									
									
									<input type = "hidden" name = "mseq" value = "${dto.mseq}">
									<input type = "hidden" name = "aiseq" value = "${dto.aiseq}">
									<div>
									<button type = "submit" id = "edit_btn">수정하기</button>
									<button type="button" onclick="location.href='/ssy/admin/adminpage.do';">
									수정취소
									</button>
									</div>
									</form>
									
								
							</div>
							 </td>
						
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
   
   
   $("#insert_btn").click(function(){
	    if(confirm("정말 삭제 하시겠습니까 ?") == true){
	        alert("삭제 되었습니다");
	    }
	    else {
	      return false ;
	    }
	});
   
   $("#edit_btn").click(function(){
	    if(confirm("정말 수정 하시겠습니까 ?") == true){
	        alert("수정 되었습니다");
	    }
	    else {
	      return false ;
	    }
	});
   </script>
</body>
</html>

