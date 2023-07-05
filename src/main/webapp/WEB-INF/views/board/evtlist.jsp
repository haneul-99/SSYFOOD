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
   #tbl1 { width: 1100px; margin: 0 auto;}
   #tbl1 th:nth-child(1) { width: 60px; }
   #tbl1 th:nth-child(2) { width: auto; }
   #tbl1 th:nth-child(3) { width: 150px; }
   #tbl1 th:nth-child(4) { width: 150px; }
   #tbl1 td { text-align: center; }
   #tbl1 td:nth-child(2) { text-align: left; }
   
  
   
   img {
	width:1200px;
	height: 200px;
   }
   
   #search table{
   	width: 460px;
   	text-align: center;
    border: 0;   
   	margin-left: 400px;
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
   
    #btns {
   	margin-top: 20px;
   	margin-left: 50px;
   	width: 740px;
   }
  
</style>
</head>
<body>
   <main>
   <%@ include file="/WEB-INF/inc/header.jsp" %>
      <div id="box1">
      		
      		<img src="/ssy/asset/images/banner02.png">
      	
	      	<h1 style="text-align: center; margin: 35px;">
	      		이벤트
	      		<c:if test="${map.isSearch == 'y'}">
	      		<i class="fa-solid fa-magnifying-glass"></i>
	      		</c:if>
	      	</h1>
      		
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
	      			<th>조회수</th>
	      		</tr>
	      		<c:if test="${list.size() == 0}">
	      		<tr>
	      			<td colspan="4">게시물이 없습니다.</td>
	      		</tr>
	      		</c:if>
	      		<c:forEach items="${list}" var="dto">
	      		<tr>
	      			
	               <td>${dto.eseq}</td>
	               <td>
	               	   <a href="/ssy/eventboard/evtview.do?eseq=${dto.eseq}&isSearch=${map.isSearch}&column=${map.column}&word=${map.word}">${dto.ename}</a>
	               </td>	
	               <td>${dto.edate}</td>
	               <td>${dto.ereadcount}</td>
	            </tr>
	      		</c:forEach>
	      	</table>
      		
      	
	      	<div id="btns">
	      		<c:if test="${not empty auth and lv == 3}">
	      		<button type="button" onclick="location.href='/ssy/eventboard/evtadd.do';">
	      			<i class="fa-solid fa-pen-nib"></i>
	      			등록
	      		</button>
	      		</c:if>
	      	</div>
	      	
		     <div id="pagebar">
		      		${pagebar}
		     </div>
		   <div id="search">
      		
      		<form method="GET" action="/ssy/eventboard/evtlist.do">
      		<table>
      			<tr>
      				<td>
      					<select name="column" id="column">
      						<option value="ename">제목</option>
      						<option value="econtent">내용</option>
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
      					<button type="button"  onclick="location.href='/ssy/eventboard/evtlist.do';">
      						<i class="fa-solid fa-list"></i>
      						검색취소
      					</button>
      				</td>
      			</tr>
      		</table>
      		</form>
      		</div>
	      </div>
   </main>
   <script>
      
   </script>
</body>
</html>


