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
	text-align: center;
}

#tbl1 td:nth-child(3) {
	text-align: left;
}


textarea {
	width: 90%;
	height: 200px;
}
</style>
</head>
<body>

   <main>
		<%@ include file="/WEB-INF/inc/header.jsp" %>
    	
    	
    	<c:if test="${not empty auth and lv == 1}">
	    	<div>작성자 : ${mname}</div>    	
    	</c:if>
    	
    	<form method="POST" action="/ssy/admin/surveyok.do">
    	
    	<table id="tbl1" class="tbl1">
    		<tr>
    			<th>번호</th>
    			<td>설문내용</td>
    		</tr>
    		<tr>
    			<th>1</th>
    			<td>귀하는 한 달에 몇 번이나 인터넷 쇼핑몰을 이용하십니까?</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="radio" name="q1" value="1"> 1) 1~2회
    				<input type="radio" name="q1" value="2"> 2) 3~4회
    				<input type="radio" name="q1" value="3"> 3) 5~6회
    				<input type="radio" name="q1" value="4"> 4) 7회 이상
    				<input type="radio" name="q1" value="5"> 5) 정기적으로 이용하지 않음
    			</td>
    		</tr>
    		<tr>
    			<th>2</th>
    			<td>SSY Food몰을 통해 주로 구매하신 물품은 무엇입니까?</td>
    		</tr>
    		<tr>
				<td colspan="2">
					<input type="radio" name="q2" value="1"> 1) 과일
    				<input type="radio" name="q2" value="2"> 2) 해산물
    				<input type="radio" name="q2" value="3"> 3) 정육
    				<input type="radio" name="q2" value="4"> 4) 국/반찬
    				<input type="radio" name="q2" value="5"> 5) 면/샐러드
				</td>
    		</tr>
    		<tr>
    			<th>3</th>
    			<td>결제수단은 주로 어떤 것을 사용하십니까?</td>
    		</tr>
    		<tr>
    			<td colspan="2">
 		   			<input type="radio" name="q3" value="1"> 1) 신용카드
    				<input type="radio" name="q3" value="2"> 2) 핸드폰결제
    				<input type="radio" name="q3" value="3"> 3) 무통장입금
    				<input type="radio" name="q3" value="4"> 4) 인터넷뱅킹
    				<input type="radio" name="q3" value="5"> 5) 기타
    			</td>
    		</tr>
    		<tr>
    			<th>4</th>
    			<td>SSY Food몰을 이용하는 이유가 있다면 무엇입니까?</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="radio" name="q4" value="1"> 1) SSY Food몰의 신뢰성 때문에
    				<input type="radio" name="q4" value="2"> 2) 고급스러운 이미지 때문에
    				<input type="radio" name="q4" value="3"> 3) 싼 가격 때문에
    				<input type="radio" name="q4" value="4"> 4) 보안이 잘 되어있기 때문에
    				<input type="radio" name="q4" value="5"> 5) 다양한 상품이 구비되어있기 때문에
    			</td>
    		</tr>
    		<tr>
    			<th>5</th>
    			<td>SSY Food몰의 화면구성이 충분히 세련되다고 생각하십니까?</td>
    		</tr>
    		<tr>
				<td colspan="2">					
    				<input type="radio" name="q5" value="1"> 1) 매우 그렇다
    				<input type="radio" name="q5" value="2"> 2) 그렇다
    				<input type="radio" name="q5" value="3"> 3) 보통이다
    				<input type="radio" name="q5" value="4"> 4) 그렇지않다
    				<input type="radio" name="q5" value="5"> 5) 매우 그렇지않다
				</td>
    		</tr>
    		<tr>
    			<th>6</th>
    			<td>SSY Food몰을 이용하실때 제품에 대한 설명이 충분하다고 생각하십니까?(서술형)</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<textarea name="q6" required></textarea>
    			</td>
    		</tr>
    	</table>
	    		<input type="hidden" name="mseq" value="${mseq}">
	    		<input type="hidden" name="newsurvey" value="${newsurvey}">
    			
    		<div id="btn1">
    			<button type="submit" id="sub_btn">설문 제출</button>
    		</div>
    	</form>
    
      	<%@ include file="/WEB-INF/inc/footer.jsp"%>  	
   </main>
   <script> 
   
   if(!${newsurvey}) {
	   
	   $("#sub_btn").click(function() {
		 	if(confirm("기존에 이미 작성한 설문조사가 있습니다. 다시 작성하시겠습니까?") == true) {
		 		return true;
		 	} else {
		 		return false;
		 	}
		   
	   });
   }
   
   		
   </script>
</body>
</html>


