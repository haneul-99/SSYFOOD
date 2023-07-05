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
.head {
	font-weight: 1000;
	font-size: 20px;
	text-align: center;
	margin-top: 100px;
	margin-bottom: 20px;
}

.tbl1 {
	width: 40%;
	margin-left: 30%;
	margin-right: 30%;
	border: 0px;
	margin-bottom: 20px;
}

.txt {
	width: 300px;
}

.jumin {
	width: 136px;
}

table th {
	width: 150px;
}

.btn1 {
	width: 40%;
	height: 40px;
	margin-left: 30%;
	margin-right: 30%;
	margin-bottom: 10px;
}
</style>
</head>
<body>

   <main>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
      	
      	<form method="POST" action="/ssy/account/findpwok.do">

			<div class="head">비밀번호 찾기</div>

			<table class="tbl1" id="tbl1">
				<tr>
					<th>아이디</th>
					<td><input type="text" class="txt" id="id" name="id"
						placeholder="아이디 입력" required></input></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" class="txt" id="name" name="name"
						placeholder="이름 입력" required></input></td>
				</tr>
				<tr>
					<th>주민등록번호</th>
					<td>
						<input type="text" class="jumin" id="frontjumin" name="frontjumin" placeholder="주민번호 앞자리 입력" required></input>
						<span>-</span>
						<input type="password" class="jumin" id="backjumin" name="backjumin" placeholder="주민번호 뒷자리 입력" required></input>
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><input type="text" class="txt" id="tel" name="tel"
						placeholder="연락처 입력" required></input></td>
				</tr>
			</table>

			<button type="submit" class="btn1">다음</button>


		</form>
      	
      	<!-- 테스트용 비밀번호 찾기 -->
		<form method="POST" action="/ssy/account/findpwok.do">
			<input type="hidden" name="id" value="QBUvsH23">
			<input type="hidden" name="name" value="홍린영">
			<input type="hidden" name="frontjumin" value="620611">
			<input type="hidden" name="backjumin" value="2673940">
			<input type="hidden" name="tel" value="010-3821-5752">
			<input type="submit" value="테스트비밀번호찾기">
		</form>
      	
      	
      	
      	
		<%@include file="/WEB-INF/inc/footer.jsp"%>
   </main>
   <script>
      
   </script>
</body>
</html>


