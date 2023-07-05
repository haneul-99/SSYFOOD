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
   #sellerinfo{
   	margin: 0 auto;
   	width : 900px;
   	height: 700px;
   	text-align: center;
   }
   
  th{
   	height: 30px;
   }
   
   tr>td:nth-child(1){
   	width:150px;
    background-color: #ddd;
   }
   
   #id {
   	width:100px;
   }
  
   #seq{
   	width:100px;
   }
   
   #edit_btn{
   	margin-top:15px;
   }
 
</style>
</head>
<body>
   
   
     <main>
				<%@ include file="/WEB-INF/inc/header.jsp"%>

<%@ include file="/WEB-INF/inc/adminnav.jsp"%>

		<section>
			
			 <table class="tbl1" id = "sellerinfo">
			 		<tr>
			 			<th colspan="6">판매자 상세정보</th>
			 			
			 		</tr>
			 		<tr>
			 			<td>판매자이름</td>
			 			<td>${dto.sname}</td>
			 			<td id = "id">판매자아이디</td>
			 			<td>${dto.aiid}</td>
			 			<td id = "seq">판매자번호</td>
			 			<td>${dto.sseq}</td>
			 		</tr>
			 		
			 		<tr>
			 			<td>판매장명</td>
			 			<td colspan="5">${dto.sstore}</td>
			 		</tr>
			 		
			 		<tr>
			 			<td>주소</td>
			 			<td colspan="5">${dto.saddress}</td>
			 		</tr>
			 		
			 		<tr>
			 			<td>연락처</td>
			 			<td colspan="5">${dto.stel}</td>
			 		</tr>
			 		
			 		<tr>
			 			<td>판매장정보</td>
			 			<td colspan="5">${dto.sinfo}</td>
			 		</tr>
			 		
			 		<tr>
			 		
			 		
			 			<td colspan="6">
			 				<button value = "${dto.sseq}" onclick="$('#${dto.sseq}').show()" style="display: inline-block">계정수정</button>
			 				
			 				
			 				
			 				<form method = "POST" action = "/ssy/admin/adminpagesellerdelok.do" style="display: inline-block" >
			 				<input type = "hidden" name = "sseq" value = "${dto.sseq}">
			 				<input type = "hidden" name = "aiseq" value = "${dto.aiseq}">
			 				<button type = "submit" id = "del_btn" >계정삭제</button>
			 				</form>
			 			
			 			</td>
			 			
			 			<tr class = "editbtn" id = "${dto.sseq}" style = "display: none">
							
							<td colspan='7'>
								수정내용 
							<div id = "editinfo">
							<form method = "POST" action = "/ssy/admin/adminpagesellereditok.do">
							<div>판매자 이름:<input type = "text" name ="sname" id ="sname" value = "${dto.sname}" ></div>
							<div>판매자 아이디:<input type = "text" name ="aiid" id ="aiid" value = "${dto.aiid}"  ></div> 
							<div>판매자 비밀번호:<input type = "text" name ="aipw" id ="aipw" value = "${dto.aipw}"  ></div> 
							<div>판매장 명:<input type = "text" name ="sstore" id ="sstore" value = "${dto.sstore}"  ></div> 
							<div>주소:<input type = "text" name ="saddress" id ="saddress" value = "${dto.saddress}"  ></div> 
							<div>연락처:<input type = "text" name ="stel" id ="stel" value = "${dto.stel}"  ></div>
							<div>판매장 정보:<input type = "text" name ="sinfo" id ="sinfo" value = "${dto.sinfo}"  ></div>
									
									<input type = "hidden" name = "sseq" value = "${dto.sseq}">
									<input type = "hidden" name = "aiseq" value = "${dto.aiseq}">
									
								
									<div id = "edit_btn">
									<button type = "submit">수정하기</button>
								
									<button type="button" onclick="location.href='/ssy/admin/adminpagesellerdetail.do?sname=${dto.sname}&stel=${dto.stel}&sstore=${dto.sstore}&saddress=${dto.saddress}&sinfo=${dto.sinfo}&sseq=${dto.sseq}&aiid=${dto.aiid}&aiseq=${dto.aiseq}&aipw=${dto.aipw }';">
									수정취소
									</button>
									</div>
									</form>
									
								
							</div>
							 </td>
						
							</tr>
						
			 		
			 </table>
		
		
		
		
		
		
		
		</section>
		
      <%@include file="/WEB-INF/inc/footer.jsp"%>
     </main>
   
   
   <script>
   $("#del_btn").click(function(){
	    if(confirm("정말 삭제 하시겠습니까 ?") == true){
	        alert("삭제 되었습니다");
	    }
	    else {
	      return false ;
	    }
	});
   </script>
</body>
</html>


