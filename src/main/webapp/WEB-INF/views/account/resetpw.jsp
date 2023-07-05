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
	margin-bottom: 10px;
}

.txt {
	width: 300px;
}

table th {
	width: 150px;
}

.find {
	width: 1500px;
	text-align: center;
	margin-bottom: 10px;
}

span {
	margin: 5px;
}

.find-font {
	color: #444;
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
	<%@ include file="/WEB-INF/inc/header.jsp"%>

		<form method="POST" action="/ssy/account/resetpwok.do">

			<div class="head">비밀번호 재설정</div>

			<table class="tbl1" id="tbl1">
				<tr>
					<th>비밀번호</th>
					<td><input type="password" class="txt" id="pw" name="pw"
						placeholder="비밀번호 입력" required></input></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" class="txt" id="pw_check"
						name="pw_check" placeholder="비밀번호 확인 입력" required></input></td>
				</tr>
			</table>

			<input type="hidden" name="id" value="${id}">


			<button type="submit" class="btn1" id="submit1">비밀번호 변경</button>

			<input type="button" value="돌아가기" class="btn1"
				onclick="location.href='/ssy/account/findpw.do';">


		</form>

		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</main>
	<script>
   
   	$('#submit1').click(() => {
   			
   		for (var i=0; i<$('#pw').val().length; i++) {
  	        let c = $('#pw').val().charAt(i);
  	        
  	        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9') ) {
  	              alert('비밀번호를 영문 대/소문자, 숫자 조합으로만 입력하세요.');
  	              event.preventDefault();
  	              $('#pw').focus();
  	                return;
  	           }
  	      }
    		
           if($('#pw').val() != $('#pw_check').val()) {
	           alert('비밀번호 확인이 일치하지 않습니다. 다시 입력해주십시오.');
	           event.preventDefault();
	           $('#pw').focus();
	           return;
           }
			
           if($('#pw').val().length < 8 || $('#pw').val().length > 16) {
	           alert('비밀번호를 8~16자 사이로 입력해주세요.');
	            event.preventDefault();
	           $('#pw').focus();
	           return;
	      }

           
           
   	});
   	
   	
   	
   </script>

</body>
</html>


