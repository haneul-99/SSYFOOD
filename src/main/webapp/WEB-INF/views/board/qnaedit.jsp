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
		margin-right: 100px;
		border: 3px dashed #999;
		border-radius: 10px;
		padding: 10px;	
	}
   
   
  
   
   table.tbl1 {
   	width: 1000px;
   	margin: 30px;
   	margin-left: 90px;
   	margin-top: 100px;
   }
   
   input#qtitle {
   	width: 900px;
   	height: 30px;
   	margin: 30px;
   }
   
   textarea#qcontent {
   	width: 900px;
   	height: 400px;
   	margin: 30px;
   }
   
   
   
   .btns {
   	text-align: center;
   	margin-bottom: 100px;
   }
   
   .btn {
   	margin: 5px;
   }
   
   .i-btn {
   	margin-right: 10px;
   }
   
   
</style>
</head>
<body>
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		<section>
		
		<h2>※ 판매자 문의게시판</h2>
		
		<form method="POST" action="/ssy/board/qnaeditok.do">
			<table class="tbl1">
				<tr>
					<th>제목</th>
					<td><input type="text" name="qtitle" id="qtitle" required value="${qtitle}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="qcontent" id="qcontent" required>${qcontent}</textarea>
					<input type="hidden" value="${qseq}" name="qseq">
					</td>
					
				</tr>
			</table>
			
			<div class="btns">
				<!-- <input type="button" value="돌아가기" onclick="history.back();"> -->
				
				<button type="submit" id="btn1">
					<i class="fa-solid fa-pen-nib i-btn"></i>
				수정하기
				</button>
				
				<button type="button" onclick="history.back();" class="btn"><i class="fa-solid fa-rotate-left i-btn"></i>돌아가기</button>
			</div>
			<input type="hidden" value="${dto.mseq}" name="mseq">
			<input type="hidden" value="${pseq}" name="pseq">
		</form>
		</section>
		
		<%@include file="/WEB-INF/inc/footer.jsp"%>
     </main>
   
   
   <script>
   
 	
   
  		$("#btn1").click(function(){
		  if(confirm("수정하시겠습니까?")==true) {
	    	 
	    	 
	       } else {
	    	   
	    	   return;
	       }
  		});	
	
      
   </script>
</body>
</html>


