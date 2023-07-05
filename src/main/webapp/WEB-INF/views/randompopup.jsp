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
	margin-bottom: 30px;
	text-align: center;
}

#rbox {
	width: 300px;
	margin-left: 90px;
	margin-bottom: 30px;
	display: inline-block;
}

#result_btn {
	text-align: center;
}

#rtbl {
	width: 200px:
	text-align: center;
	margin-left: 9%;
	margin-right: 4%;
	margin-top: 40px;
	display: inline-block;
}

#rtbl td {
	display: inline;
}

#rbutton {
	width: 250px;
}

input {
	width: 280px;
}
</style>
</head>
<body style="background-color: greenyellow;">
		
	<h2>R A N D O M B O X</h2>

	<img src="/ssy/asset/pic/randombox.png" id="rbox" class="rbutton">
	
		<div id="result_btn">
			<button type="button" id="rbutton">결과확인</button>
		</div>
	<!-- 	
		<div id="hidden_btn" style="visibility:hidden">
			<button type="submit">보관함으로 이동</button>
			<input type="button" value="나가기" onclick="window.close();">
		</div> -->
		
	
	<script>
		var enough = '${enough}';
		
		if (enough == "Y") {
			alert('10000P가 차감되었습니다.');			
		} else {
			alert('포인트가 부족합니다.');
			window.close();
		}
	
	
	$(function() {
  		
    	var target = document.querySelector('.rbutton');
  		var timer;
  		
  		$('#rbutton').click(function() {
  			
  			var player = target.animate([
      	      { transform: 'translate(0, 0);'},
      	      { transform: 'translate(10px, 0px)'},
      	      { transform: 'translate(-10px, 0px)'},
      	      { transform: 'translate(0, 0)'},
      	      { transform: 'translate(-7px, 0)'},
      	      { transform: 'translate(7px, 0)'},
      	      { transform: 'translate(0, 0)'},
      	      { transform: 'translate(0, 15px)'},
      	      { transform: 'translate(0, -15px)'},
      	      { transform: 'translate(0, 0)'}
      	  ],{
      	      duration : 2000
      	  });
  			
  			timer = setTimeout(function () {
      		  
        		alert("축하합니다. ${dto.rbcontent} 상품 당첨되셨습니다.");
        		
        		location.href="/ssy/randompopupok.do?mseq=${mseq}&rbseq=${dto.rbseq}&maddress=${mdto.maddress}";
     
        		
        	  }, 3500);
  			
  		});//알버튼클릭
  		
  		
  		
  		
  	})
  
   </script>
</body>
</html>


