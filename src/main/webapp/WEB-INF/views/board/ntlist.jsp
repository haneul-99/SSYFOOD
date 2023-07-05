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

table {
	width: 1200px;
	margin: 0 auto;
	margin-bottom: 10px;
}

table, td, th {
	border-collapse: collapse;
	text-align: center;
}

tr>th:nth-child(1) {
	width: 60px;
}

tr>th:nth-child(2) {
	width: 400px;
}

tr>th:nth-child(3) {
	width: 100px;
}

tr>th:nth-child(4) {
	width: 60px;
}

tr>th:nth-child(5) {
	width: 100px;
}

#pagebar {
	margin: 20px;
	text-align: center;
}

.btns {
	text-align: right;
}
</style>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/inc/header.jsp"%>

		<h2>공지사항</h2>

		<table id="tbl1" class="tbl1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성날짜</th>
				<th>조회수</th>
			</tr>
			<tr id="survey-line">
				<td><img src="/ssy/asset/images/icon_notice.png"></td>
				<td><a href="/ssy/admin/survey.do?mseq=${mseq}">SSY Food 고객만족도에 관한 설문조사 참여 안내(회원 대상)</a></td>
				<td>관리자</td>
				<td>2023-01-02</td>
				<td></td>
			</tr>
			<c:if test="${list.size() == 0}">
				<tr>
					<td colspan="5">게시물이 없습니다.</td>
				</tr>
			</c:if>

			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.nseq}</td>
					<td>
						<a href="/ssy/noticeboard/ntview.do?seq=${dto.nseq}">${dto.nname}</a>
						<c:if test="${not empty dto.filename}">
							<i class="fa-solid fa-image" style="color:cornflowerblue; margin-left:3px;"></i>
						</c:if>
					</td>
					<td>관리자</td>
					<td>${dto.ndate}</td>
					<td>${dto.nreadcount}</td>
				</tr>
			</c:forEach>
		</table>

		<div class="btns">
			<c:if test="${not empty auth and lv == 3}">
				<button type="button"
					onclick="location.href='/ssy/noticeboard/ntadd.do?seq=${dto.nseq}';"
					class="write-btn">
					<i class="fa-solid fa-pen-nib"></i> 쓰기
				</button>
			</c:if>
		</div>
		
		<div id="pagebar">${pagebar}</div>

		<input type="hidden" name="mseq" value="${mseq}">
	
		<%@ include file="/WEB-INF/inc/footer.jsp"%>
	</main>
	<script>
		
	</script>
</body>
</html>


