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
   #tbl1 { width: 920px; }
   #tbl2 { width: 200px; }
   #tbl2 td:nth-child(1) { border-bottom: 1px solid #999; }
   #tbl1 th:nth-child(1) { width: 60px; }
   #tbl1 th:nth-child(2) { width: auto; }
   #tbl1 th:nth-child(3) { width: 120px; }
   #tbl1 td { text-align: center; }
   #tbl1 td:nth-child(2) { text-align: left; }
   
   #search table{
   	width: 460px;
   	text-align: center;
    border: 0;   
   	margin-left: 220px;
   }
   
   #search table td {
   	border: 0;
   	padding: 5px;
   }
   
   #pagebar {
    width: 850px;
	margin: 30px auto;
	text-align: center;
   }
  
   #box1, #box2 {
   	width: 200px;
   	float: left;
   	margin: 15px;
   }
   
   #tbl2 {
   border: 1px solid #999;
   border-collapse: collapse;   
   }
   
   #tbl2 a {
   	padding: 10px;
   	font-size: 12px;
   }	
   
   a.active-color {
   	color: gold;
   }
   
   #tbl2 td {
   	border: 1px solid #999;
   	padding: 7px;
   	height: 27px;
   }
   
   #tbl2 td:hover {
   	background-color: #EEE;
   }
   
   #tbl2 i {
   	float: right;
   	color: #999;
   	font-size: 15px;
   }
 
   
   #btns {
   	margin-top: 20px;
   	width: 740px;
   }

   
   
   
</style>
</head>
<body>
   <main>
      <%@ include file="/WEB-INF/inc/header.jsp" %>
      <div id="main">
      
      	<div id="box1">
      		<h1 style="margin-bottom: 29px;">고객센터</h1>
      	
	      	<table id="tbl2">
	      		<tr>
	      			<td>
	      				<a href="/ssy/complainboard/cplist.do">문의게시판<i class="fa-solid fa-chevron-right"></i></a>
	      			</td>
	      		</tr>
	      		<tr>
	      			<td>
	      				<a href="/ssy/faqboard/faqlist.do">자주하는 질문<i class="fa-solid fa-chevron-right"></i></a>
	      			</td>
	      		</tr>
	      	</table>
	     </div>
	     <div id="box2">
	      	<h2 style="margin-top: 40px;">
	      		자주하는 질문
	      		<c:if test="${map.isSearch == 'y'}">
	      		<i class="fa-solid fa-magnifying-glass"></i>
	      		</c:if>
	      	</h2>
      	
	      	<c:if test="${map.isSearch == 'y'}">
	      	<div style="width: 920px; margin-bottom: 10px; text-align: center;">
	      		'${map.word}'(으)로 검색한 결과 총 ${list.size()}개의 게시물이 발견되었습니다.
	      	</div>
	      	</c:if> 
      	
	      	<table id="tbl1" class="tbl1">
	      		<tr>
	      			<th>번호</th>
	      			<th>제목</th>
	      			<th>날짜</th>
	      		</tr>
	      		<c:if test="${list.size() == 0}">
	      		<tr>
	      			<td colspan="5">게시물이 없습니다.</td>
	      		</tr>
	      		</c:if>
	      		<c:forEach items="${list}" var="dto">
	      		<tr>
	      			
	               <td>${dto.fqseq}</td>
	               <td>
	               	   <a href="/ssy/faqboard/faqview.do?fqseq=${dto.fqseq}&isSearch=${map.isSearch}&column=${map.column}&word=${map.word}">${dto.fqtitle}</a>
	               </td>	
	               <td>${dto.fqdate}</td>
	            </tr>
	      		</c:forEach>
	      	</table>
      		
      	
	      	<div id="btns">
	      		<c:if test="${not empty auth and lv == 3}">
	      		<button type="button" onclick="location.href='/ssy/faqboard/faqadd.do';">
	      			<i class="fa-solid fa-pen-nib"></i>
	      			등록
	      		</button>
	      		</c:if>
	      	</div>
	      	
		     <div id="pagebar">
		      		${pagebar}
		     </div>
		   <div id="search">
      		<form method="GET" action="/ssy/faqboard/faqlist.do">
      		<table>
      			<tr>
      				<td>
      					<select name="column" id="column">
      						<option value="fqtitle">제목</option>
      						<option value="fqcontent">내용</option>
      						<option value="all">모두</option>
      					</select>
      				</td>
      				<td>
      					<input type="text" name="word" id="word" required>
      				</td>
      				<td>	
      					<button type="submit">
      						<i class="fa-solid fa-magnifying-glass"></i>
      						검색하기
      					</button>
      					<button type="button"  onclick="location.href='/ssy/faqboard/faqlist.do';">
      						<i class="fa-solid fa-list"></i>
      						검색취소
      					</button>
      				</td>
      			</tr>
      		</table>
      		</form>
      		</div>
	      </div>
      	</div>
      	
   </main>
   <script>
	  
   </script>
</body>
</html>
      

