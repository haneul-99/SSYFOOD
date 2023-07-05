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

#view-title {
	font-size: 30px;
	margin-bottom: 5px;
}

#view-subtitle {
	margin-bottom: 5px;
	color: #555;
}

#tblbtns {
	width: 100%;
}

.left {
	text-align: left;
}

.right {
	text-align: right;
}

form {
	display: inline;
}
</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/inc/header.jsp"%>

		<h2>공지사항</h2>


		<div id="view-head">
			<div id="view-title">${dto.nname}</div>
			<div id="view-subtitle">작성자 : 관리자 | 작성일 : ${dto.ndate} | 조회수 :
				${dto.nreadcount}</div>
		</div>
		<hr style="margin-bottom: 20px;">

		<div id="view-body">
			${dto.ncontent}
		</div>

		<hr style="margin-top: 20px;">
		
		
	
		<table id="tblbtns">
			<tr>
				<td class="left"><input type="button" id="back" class="back"
					onclick="location.href='/ssy/noticeboard/ntlist.do';" value="돌아가기">
				</td>
				<c:if test="${not empty auth and lv == 3}">
					<td class="right">
						<form method="GET" action="/ssy/noticeboard/ntedit.do">
								<input type="hidden" name="nseq" value="${dto.nseq}">
								<button type="submit" id="edit" class="btn" name="edit">수정하기</button>
						</form>
						<form method="POST" action="/ssy/noticeboard/ntdelok.do">
								<input type="hidden" name="nseq" value="${dto.nseq}">
								<button type="submit" id="del" class="btn" name="del">삭제하기</button>
						</form>
					</td>
				</c:if>
			</tr>
		</table>


		<%@ include file="/WEB-INF/inc/footer.jsp"%>
	</main>
	<script>
	 $("#del").click(function(){
	       if(confirm("정말 삭제 하시겠습니까 ?") == true){
	           alert("삭제 되었습니다");
	       }
	       else{
	    	   alert("삭제가 취소되었습니다");
	    	   history.back();
	           return false;
	       }
	   });
	</script>
</body>
</html>


