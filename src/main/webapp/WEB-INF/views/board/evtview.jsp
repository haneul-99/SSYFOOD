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
   	text-align: center;
   	margin: 40px;
   }

   #tbl1 {
      width: 1100px;
      margin: 0 auto;
   }
   #tbl1 th {
      width: 180px;
   }

   #tbl1 td {
      width: 640px;
   }   
   #tbl1 tr:nth-child(2) th {
      height: 200px;
   }
   
   .btns {
   width: 1100px;
   margin: 30px auto;
   }
   
   input[type=button] {
   	width: 100px;
   }
   
   button[type=button]{
   	margin-left: 8px;
   }
</style>
</head>
<body>
   <main>
      <%@ include file="/WEB-INF/inc/header.jsp" %>
      
      <h2>이벤트</h2>
         
         <table id="tbl1" class="tbl1">
            <tr>
               <th>제목</th>
               <td>${dto.ename}</td>
               <th>날짜</th>
               <td>${dto.edate}</td>
            </tr>
            <tr>
               <th>내용</th>
               <td colspan="3">${dto.econtent}</td>
            </tr>
            <tr>
               <th>번호</th>
               <td>${dto.eseq}</td>
               <th>조회수</th>
               <td>${dto.ereadcount}</td>
            </tr>
         </table>
         
         <div class="btns">
            <input type="button" value="목록" onclick="location.href='/ssy/eventboard/evtlist.do?column=${column}&word=${word}';">
            
            <c:if test="${not empty auth and lv == 3}">
            <button type="button" onclick="location.href='/ssy/eventboard/evtedit.do?seq=${dto.eseq}';" style="width: 100px; float: right;">
               <i class="fa-solid fa-pen-to-square"></i>
               수정하기
            </button>
           
      	    <input type="button" value="삭제하기" id="btn_delete" onclick="location.href='/ssy/eventboard/evtdelok.do?seq=${dto.eseq}';" style="width: 100px; float: right;">
      		<input type="hidden" name="fqseq" value="${eseq}">

          </c:if>  
         </div>
   </main>
   <script>
   $("#btn_delete").click(function(){
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


