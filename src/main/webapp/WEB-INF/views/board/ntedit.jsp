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
#tblview {
	margin-left: 20%;
	margin-right: 20%;
}

#name {
	border: 0;
	border-bottom: 1px solid #555;
	width: 700px;
	margin-bottom: 5px;
}

#content {
	width: 700px;
	height: 300px;
	margin-bottom: 5px;
}

#attach {
	border: 0;
	margin-bottom: 5px;
}

.btns {
	margin-left: 813px;
}
</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/inc/header.jsp"%>

		<h2>공지사항 수정</h2>

		<form method="POST" action="/ssy/noticeboard/nteditok.do">
			<table id="tblview">
				<tr>
					<td><input type="text" name="name" id="name" class="name"
						value="${dto.nname}" required></td>
				</tr>
				<tr>
					<td><textarea name="content" id="content" class="content"
							required>${dto.ncontent}</textarea></td>
				</tr>
				<tr>
					<td>첨부파일 <input type="file" name="attach" id="attach"
						class="attach"></td>
				</tr>
			</table>

			<div class="btns">
				<input type="button" value="돌아가기"
					onclick="location.href='/ssy/noticeboard/ntlist.do';">
				<button type="submit">수정하기</button>
			</div>

			<input type="hidden" name="seq" value="${dto.nseq}">
		</form>


		<%@ include file="/WEB-INF/inc/footer.jsp"%>
	</main>
	<script>
		
	</script>
</body>
</html>


