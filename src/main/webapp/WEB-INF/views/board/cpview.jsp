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
   #tbl1 {
      width: 1000px;
      margin: 0 auto;
   }
   #tbl1 th {
      width: 160px;
   }
   #tbl1 td {
      width: 640px;
   }   
   
   #tbl1 tr:nth-child(1) th {
      height: 25px;
   }
   
   #tbl1 tr:nth-child(2) th {
      height: 230px;
   }
   
   #tbl1 tr:nth-child(3) th {
      height: 25px;
   }
   
   #btns {
   width: 1000px;
   margin: 30px auto;
   }
   
   input[type=button] {
   	width: 100px;
   	margin-right: 8px;
   }
   
   button[type=button]{
   	margin-left: 8px;
   }
   
   #tblAddComment {
      width: 1000px;
      border: 1px solid #999;
   	  border-collapse: collapse;
   	  margin: 35px auto;   
   }
      
   #tblAddComment #cacontent {
      display: block;
      width: 870px;
      height: 100px;
      margin: 8px;
   }
   
   #tblAddComment button {
      margin: 5px;
      height: 110px;
   }
   
   
   table td {
	   border: 1px solid #999;
	   padding: 7px;
   }
   
   #tblListComment {
      width: 1000px;
      margin: 15px auto;
      border-collapse: collapse;
      border: 1px solid black;
   }
   
   #tblListComment td:nth-child(1) {
      width: 460px;
      height: 80px;
      vertical-align: top;
      padding: 12px;
   }
   
   #tblListComment td:nth-child(2) {
      width: 200px;
      vertical-align: top;
   }
   
   #tblListComment table {
      width: 800px;
      margin: 10px auto;
   }
   
   #tblListComment table textarea {
      display: block;
      width: 590px;
      height: 100px;
   }
   
   #tblListComment table button {
      margin: 5px;
      height: 50px;
      width: 90%;
   }
   
</style>
</head>
<body>
   <main>
      <%@ include file="/WEB-INF/inc/header.jsp" %>
      
      <h2 style="text-align: center; margin: 40px;">1:1 문의</h2>
         
         <table id="tbl1" class="tbl1">
            <tr>
               <th>제목</th>
               <td>${dto.cbtitle}</td>
               <th>작성자</th>
               <td>${dto.mname}</td>
            </tr>
            <tr>
               <th>내용</th>
               <td colspan="3">${dto.cbcontent}</td>
            </tr>
            <tr>
               <th>등록일</th>
               <td colspan="3">${dto.cbdate}</td>
            </tr>
         </table>
         
         <div id="btns">
            <input type="button" value="1:1문의 목록" onclick="location.href='/ssy/complainboard/cplist.do?column=${column}&word=${word}';">
            
            <c:if test="${not empty auth and lv == 1 or lv == 3}">
            <button type="button" onclick="location.href='/ssy/complainboard/cpedit.do?seq=${dto.cbseq}';" style="width: 100px; float: right;">
               <i class="fa-solid fa-pen-to-square"></i>
               수정하기
            </button>
            <input type="button" value="삭제하기" id="btn_delete" onclick="location.href='/ssy/complainboard/cpdelok.do?seq=${dto.cbseq}';" style="width: 100px; float: right;">
      		<input type="hidden" name="fqseq" value="${cbseq}">
         
            </c:if>
            
         </div>
         
         <form method="POST" action="/ssy/complainboard/cpcommentaddok.do">
          <c:if test="${not empty auth and lv == 3}">
       	<h2 style="margin-left: 100px;">문의답변</h2>
         <table id="tblAddComment">
            <tr>
               <td><textarea name="cacontent" id="cacontent" required placeholder="답변"></textarea></td>
               <td>
                  <button type="submit">
                     <i class="fa-regular fa-pen-to-square"></i>
                     등록
                  </button>
               </td>
            </tr>
         </table>
         </c:if>
         <input type="hidden" name="cbseq" value="${dto.cbseq}">
         <input type="hidden" name="aseq" value="${aseq}">
         
         </form>
         
        
         <h3 style="margin-left: 100px;">작성된 답변</h3>
         <table id="tblListComment">
            <c:forEach items="${clist}" var="cdto">
            <tr>
               <td>${cdto.cacontent}</td>
               <td>
                  <div>${cdto.cadate}</div>
                  <div>관리자: ${cdto.aname}</div>
                  
                  <c:if test="${not empty auth and lv == 3}">
                  <div>
                  	 
                    <i class="fa-solid fa-pen-to-square" 
                           onclick="edit(${cdto.caseq});"></i> 
                     <i class="fa-solid fa-trash-can" onclick="location.href='/ssy/complainboard/cpcommentdelok.do?cbseq=${dto.cbseq}&caseq=${cdto.caseq}';"></i> 
                  </div>
                  </c:if>
                  
               </td>
            </tr>
            </c:forEach>
            <c:if test="${clist.size() == 0}">
	      		<tr>
	      			<td colspan="2" style="text-align: center; padding-top: 60px;">답변이 없습니다.</td>
	      		</tr>
	      	</c:if>
         </table>
           
      
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
   	
   	function edit(caseq) {
        
        //alert(caseq);
         
        $('.editRow').remove();
        
        let cacontent = $(event.target).parents('tr').children().eq(0).text();
        
        
        let temp = `
        
           <tr class="editRow">
              <td colspan="2">
                 <form method="POST" action="/ssy/complainboard/cpcommenteditok.do">
                 <table style="width: 950px;">
                    <tr>
                       <td><textarea name="cacontent" style="width: 700px;" required>\${cacontent}</textarea></td>
                       <td>
                          <button type="submit">
                             <i class="fa-solid fa-comments"></i>
                             수정
                          </button>
                          <button type="button" onclick="$('editRow').remove();">
                             <i class="fa-solid fa-xmark"></i>
                             취소
                          </button>
                       </td>
                    </tr>
                 </table>
                 <input type="hidden" name="caseq" value="\${caseq}">
                 <input type="hidden" name="cbseq" value="\${${dto.cbseq}}">
                 </form>
              </td>
           </tr>
           
        `;
        
        
        $(event.target).parents('tr').after(temp);
        
     }
   </script>
</body>
</html>


