<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/inc/asset.jsp" %>
<style>
   table { 
     width: 800px;
   	margin: 50px auto;
  	font-size: 1.3rem;
   }
   
   table th,table td {
      border-bottom: 1px solid #BBB;
      padding: 20px;
   }
   
   input[type="text"], input[type="password"] {
   width: 500px;
   padding: 10px; 
   height: 20px;
   }
   
   input[type=button] {
   	width: 90px;
   	height: 43px;
   	margin-left: 5px;
   }
   
   .yesId{
      font-size:.8rem;
      color: cornflowerblue;
   }
   
   h1 {
      text-align:center;
   
   }
   
   #btns{
   text-align:right;
   }
   
   #store_info {
   width: 500px;
   height: 150px;
   
   }
   .msg{
    text-align: right;   
   }
   
</style>
</head>
<body>
   <main>
   <%@ include file="/WEB-INF/inc/header.jsp" %>
        <form method="POST" action="/ssy/account/sellerregisterok.do">
        
        <h1>판매자 회원가입</h1>
        <hr>
        <div class="msg">판매자의 개인정보를 입력해주세요</div>
       <table class="tbl1">
         <tr>
            <th>아이디</th>
            <td id="idtd">
            	<input type="text" name="id" id="id" placeholder="﻿아이디는 5-20자의 영문 소문자, 숫자만 사용" required> 
            	<span class="yesId"></span> 
            	<input type="button" value="중복 확인" id="id_check">
	      	<div><span id="result"></span></div>
	      </td>
         </tr>
         <tr>
            <th>비밀번호</th>
            <td><input type="password" name="pw" id="pw" placeholder="﻿비밀번호는 8-16자의 영문 대소문자, 숫자만 사용 가능" required> </td>
         </tr>
         <tr>
            <th>비밀번호 확인</th>
            <td><input type="password" name="pwc" id="pwc"> </td>
         </tr>
         <tr>
            <th>이름</th>
            <td><input type="text" name="name" id="name" placeholder="홍길동" required> </td>
         </tr>
         <tr>
            <th>주민등록번호</th>
            <td>
            	<input type="text" name="ssid" id="ssid" placeholder="'-'를 포함해서 압력해주세요." required> 
            	<div><span id="result1"></span></div>
            </td>
         </tr>
         <tr>
            <th>연락처</th>
            <td><input type="text" name="tel" id="tel" placeholder="숫자만 입력해주세요" required> </td>
         </tr>
      </table>
      <hr>    
      <div class="msg">판매장의 정보를 입력해주세요</div>   
         <table class="tbl1">
         <tr>
            <th>판매장명</th>
            <td><input type="text" name="store_name" id="store_name" placeholder="판매장명을 입력해주세요"> </td>
         </tr>
         <tr>
            <th>판매장 주소</th>
            <td><input type="text" name="store_address" id="store_address"> </td>
         </tr>
         <tr>
            <th>판매장 정보</th>
            <td> <textarea name="store_info" id="store_info" placeholder="판매장 정보를 입력해주세요."> </textarea> </td>
         </tr>
      
      </table>
         <div id="btns"> 
            <button type="submit" id="submit1">회원가입</button> 
            <button type="button" onclick="history.back()">돌아가기</button>
            </div>
      </form>
   </main>
   <script>
   
   $('#id_check').click(function() {
		
	   if ($('#id').val() != '') {
	   
		$.ajax({
			type: 'POST',
			url: '/ssy/account/registerid.do',
			data: 'id=' + $('#id').val(),
			dataType: 'json',
			success: function(result) {
				if (result.use == 1) {
					$('#result').text('이미 사용중인 아이디입니다.').css('color', 'tomato');
				} else {
					$('#result').text('사용 가능한 아이디입니다.').css('color', 'cornflowerblue');
				}
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});	
		
	   } 
	   
	});
   
   $('#ssid').keyup(()=>{
	     if($('#ssid').val().length == 14) {
	    	  
	    	  	$.ajax({
		    	 	type: 'POST',
				url: '/ssy/account/sellerjumin.do',
				data: 'ssid=' + $('#ssid').val(),
				dataType: 'json',
				success: function(result) {
					if (result.use > 1) {
					 	//location.href='/ssy/account/login.do'; //아이디 찾기로 이동
						//alert('이미 회원가입한 회원입니다.');
						$('#result1').text('이미 회원가입한 판매자입니다.').css('color', 'tomato');
						$('#submit1').prop('disabled', true);
						
					} else {
						$('#submit1').prop('disabled', false);
					}
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
		    	  
		      }); 
	    	  
	      } 
  	  }
   );
      

   	$('#submit1').click(() => {
      
      
//       	아이디
	     for (var i=0; i<$('#id').val().length; i++) {
   	        let c = $('#id').val().charAt(i);
   	        
   	        if ((c < 'a' || c > 'z') && (c < '0' || c > '9') ) {
   	              alert('아이디를 소문자 영어/숫자조합 으로만 입력하세요.');
   	              event.preventDefault();
   	              $('#id').focus();
   	              return;
   	           }
   	      }
   		
   	      
   	      if($('#id').val().length<5 || $('#id').val().length > 20) {
   	            alert('아이디를 5~20자 사이로 입력해주세요.');
   	             event.preventDefault();
   	            $('#id').focus();
   	            return;
   	      }
      
//		비밀번호
      	if($('#pw').val().length<8 || $('#pw').val().length > 16) {
	            alert('비밀번호를 8~16자 사이로 입력해주세요.');
	             event.preventDefault();
	            $('#pw').focus();
	            return;
         	}
      
      	if($('#pw').val() != $('#pwc').val()) {
	            alert('비밀번호가 일치하지않습니다.');
	             event.preventDefault();
	            $('#pwc').focus();
	            return;
         	}
      	
      	for (var i=0; i<$('#pw').val().length; i++) {
  	        let c = $('#pw').val().charAt(i);
  	        
  	        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9') ) {
  	              alert('비밀번호를 대/소문자 영어/숫자조합 으로만 입력하세요.');
  	              event.preventDefault();
  	              $('#pw').focus();
  	                return;
  	           }
  	      }

 
	 	for (var i=0; i<$('#tel').val().length; i++) {
		        let c = $('#tel').val().charAt(i);
		        
		        if (c < '0' || c > '9') {
		              alert('숫자만 입력하세요.');
		              event.preventDefault();
		              $('#tel').focus();
		                return;
		        }
		 } 

      
      
   });
   
 

   </script>
</body>
</html>

