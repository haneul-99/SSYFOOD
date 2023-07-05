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
	margin-left : 795px;
}

</style>
</head>
<body>

   <main>
		<%@ include file="/WEB-INF/inc/header.jsp" %>
    	
    	<h2>공지사항 작성</h2>
		<form method="POST" action="/ssy/noticeboard/ntaddok.do" enctype="multipart/form-data">
			<table id="tblview">
				<tr>
					<td><input type="text" name="name" id="name" class="name" placeholder="제목" required></td>
				</tr>
				<tr>
					<td><textarea type="text" name="content" id="content" class="content" placeholder="내용" required></textarea></td>
				</tr>
				<tr>
					<td>첨부파일 <input type="file" name="attach" id="attach" class="attach" value="첨부파일"></td>
				</tr>
			</table>
			
			
			<div class="btns">
				<input type="button" value="돌아가기" onclick="location.href='/ssy/noticeboard/ntlist.do';">
				<button type="submit">
					<i class="fa-solid fa-pen-nib"></i>
					작성완료
				</button>
			</div>
			
		</form>
		<%@ include file="/WEB-INF/inc/footer.jsp"%>  	
   </main>
   <script>
      
   </script>
</body>
</html>


