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
	width: 800px;
	margin: 0 auto;
}

table, td, th {
	border-collapse: collapse;
	text-align: center;
	
}
#deliver tr{
	height: 80px;
}
tr>th:nth-child(1) {
	width: 100px;
}
tr>th:nth-child(2) {
	width: 200px;
}
tr>th:nth-child(3) {
	width: 100px;
}

tr>th:nth-child(4) {
	width: 100px;
}

tr>th:nth-child(5) {
	width: 100px;
}
tr>th:nth-child(6) {
	width: 100px;
	
}
tr>th:nth-child(7) {
	width: 150px;
}

#pagebar {
	margin: 20px;
	text-align: center;
}

#search {
	margin: 0 auto;
	
}

#search table {
	width: 600px;
	margin: 20px auto;
	border: 0;
	border: 1px solid #EEE;
}

#search table td {
	border: 0;
	padding: 5px;
}

#insert {

	margin-top: 25px;
	margin-left: 1000px;
	width: 100px;
	height: 40px;
}

#ul-seller > li:nth-child(3) {
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
			<h2>택배사 정보 페이지</h2>
			
				<table id = "deliver">
					<tr>
						<th>번호</th>
						<th>택배사명</th>
						<th>소형(5kg)</th>
						<th>중형(10kg)</th>
						<th>대형(20kg)</th>
						<th>초대형(25kg)</th>
						<th>삭제</th>
						
					</tr>
					
					<c:forEach items="${d_list}" var="d_dto">
						<tr>
							<td>${d_dto.dseq}</td>
							<td>${d_dto.dname}</td>
							<td>${d_dto.dsmall}원</td>
							<td>${d_dto.dmidium}원</td>
							<td>${d_dto.dlarge}원</td>
							<td>${d_dto.dxlarge}원</td>
							<td>
							<button class = "button" value = "${dto.mseq}" onclick="$('#${d_dto.dseq}').show()">가격 수정</button>
							
							</td>
						</tr>
						<tr class = "editbtn" id = "${d_dto.dseq}" style = "display: none">
							
							<td colspan='7'>
								수정내용 
							<div id = "editinfo">
							<form method = "POST" action = "/ssy/admin/delivereditok.do">
							
							<div>소형:&nbsp;&nbsp;&nbsp; <input type = "text" name = "dsmall" id = "dsmall" value="${d_dto.dsmall}"></div> 
							<div>중형:&nbsp;&nbsp;&nbsp; <input type = "text" name = "dmidium" id = "dmidium" value="${d_dto.dmidium}"></div> 
							<div>대형:&nbsp;&nbsp;&nbsp; <input type = "text" name = "dlarge" id = "dlarge" value="${d_dto.dlarge}"></div> 
							<div>초대형:                  <input type = "text" name = "dxlarge" id = "dxlarge" value="${d_dto.dxlarge}"></div>
									
									
									<input type = "hidden" name = "dseq" value = "${d_dto.dseq}">
									<div>
									<button type = "submit" style="display:inline">수정하기</button>
									<button type="button" onclick="location.href='/ssy/admin/deliver.do';" >
									수정취소
									</button>
									</div>
									</form>
									
							</div>
							 </td>
							
							</tr>
					</c:forEach>
						
				</table>	
				
				<button class = "insert" onclick="$('#insertdeliver').show()">택배사 등록</button>
				<div id = insertdeliver style="display: none">
						<form method = "POST" action = "/ssy/admin/deliverplusok.do">
							<div>택배사이름:<input type = "text" name = "dname" id = "dname"></div>
							<div>소형 가격:<input type = "text" name = "dsmall" id = "dsmall"></div>
							<div>중형 가격:<input type = "text" name = "dmidium" id = "dmidium"></div>
							<div>대형 가격:<input type = "text" name = "dlarge" id = "dlarge"></div>
							<div>초대형 가격:<input type = "text" name = "dxlarge" id = "dxlarge"></div>
						
									<!-- insert into tblDeliver(dSeq, dName, dSmall, dMidium, dLarge, dXlarge) values (seqdeliver.nextval, 'CJ대한통운', 1000, 2000, 3000, 5000); -->
								<div>
									<button type = "submit">택배사 추가하기</button>
									<button type="button" onclick="location.href='/ssy/admin/deliver.do';">
									추가취소
									</button>
								</div>
						</form>
				
				</div>
				
					<div id="pagebar">${pagebar}</div>

			<div id="search">
				<!-- 검색!!! method="GET" -->
				<form method="GET" action="/ssy/admin/deliver.do">
					<table>
						<tr>
							<td>
								<select name="d_column" id="d_column">
									<option value="dname">택배사 이름</option>
								</select>
							</td>
							<td><input type="text" name="d_word" id="d_word" required
								autocomplete="off"></td>
							<td>
								<button type="submit">
									<i class="fa-solid fa-magnifying-glass"></i> 검색하기
								</button>
								<button type="button"
									onclick="location.href='/ssy/admin/deliver.do';">
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
   <c:if test="${map.isSearch == 'y'}">
   $('#column').val('${map.column}');
   $('#word').val('${map.word}');
   </c:if>
   
   
   $('#selpage').val(${nowPage});
   
   </script>
</body>
</html>


