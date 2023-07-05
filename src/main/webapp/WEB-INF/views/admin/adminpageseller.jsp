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


#pagebar {
	margin: 20px;
	text-align: center;
}
	

#search{
	
	}
   
   
   #ul-seller > li:nth-child(2) {
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
		
			<h2>판매자 관리 페이지</h2>
		
			<table>
				<tr>
					<th>판매자 이름</th>
					<th>판매장 명</th>
					<th>주소</th>
					<th>상세보기</th>
					
				</tr>
				<c:forEach items="${slist}" var = "sdto">
				<tr>
					
					
					<td onclick="location.href='/ssy/admin/adminpage.do'">${sdto.sname}</td>
					<td onclick="/ssy/admin/adminpage.do">${sdto.sstore}</td>
					<td onclick="/ssy/admin/adminpage.do">${sdto.saddress}</td>
					
					
					
					
					
					<td>
						<!-- <input type="button" value="상세보기"> -->
						<form method = "GET" action = "/ssy/admin/adminpagesellerdetail.do">
						<button>상세보기</button>
						<input type = "hidden" name = "sname" value = "${sdto.sname }">
						<input type = "hidden" name = "stel" value = "${sdto.stel }">
						<input type = "hidden" name = "sstore" value = "${sdto.sstore }">
						<input type = "hidden" name = "saddress" value = "${sdto.saddress }">
						<input type = "hidden" name = "sinfo" value = "${sdto.sinfo }">
						<input type = "hidden" name = "sseq" value = "${sdto.sseq }">
						<input type = "hidden" name = "aiid" value = "${sdto.aiid }">
						<input type = "hidden" name = "aiseq" value = "${sdto.aiseq }">
						<input type = "hidden" name = "aipw" value = "${sdto.aipw }">
						
						</form>
					</td>
					
				</tr>
				</c:forEach>
			</table>
			
			<div id="pagebar">${pagebar}</div>
			
			<div id="search">
				<!-- 검색!!! method="GET" -->
				<form method="GET" action="/ssy/admin/adminpageseller.do">
					<table>
						<tr>
							<td><select name="column" id="column">
									<option value="sname">판매자이름</option>
									<option value="sstore">판매장명</option>
									<option value="saddress">주소</option>
									<option value="all">모두</option>
							</select></td>
							<td><input type="text" name="word" id="word" required
								autocomplete="off"></td>
							<td>
								<button type="submit">
									<i class="fa-solid fa-magnifying-glass"></i> 검색하기
								</button>
								<button type="button"
									onclick="location.href='/ssy/admin/adminpageseller.do';">
									<i class="fa-solid fa-list"></i> 검색취소
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</section>
		
		
     
     </main>
   
   
   <script>
      
   </script>
</body>
</html>
