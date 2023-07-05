<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSY Food</title>
<%@ include file="/WEB-INF/inc/asset.jsp" %>
<link rel="stylesheet" href="/toy/asset/css/tagify.css">
<script src="/toy/asset/js/jQuery.tagify.min.js"></script>
<script src="/toy/asset/js/tagify.js"></script>
<style>
  .tagify--focus {
   		--tags-border-color: #999;
   }
   table {
   
   width: 800px;
   margin: 30px auto;}
   main{
   
   padding-bottom: 150px;}
   
   input[type="text"] {
   width: 600px;
   
   }
   .btns {
   text-align:right;
   padding-right: 100px;
   
   }
   
   #sub1 {
   	 font-size: 1.5rem;
   	 background-color:tomato;
   	 border-radius: 10px;
   	 color:white;
   	 height: 50px;
   	 border: 2px dashed white;
   	 box-shadow: 0 0 0 5px tomato;}
   	 
   	 #preview{
   	 width: 200px;
   	 height: 200px;
   	 border:none;
   	 }
</style>
</head>
<body>
   
   
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		
		<h1>상품등록하기</h1>
		<hr>
		<form action="/ssy/product/registerproductok.do" enctype="multipart/form-data" method="POST">
		
		<h2>상품 정보 작성</h2>
			<table class="tbl1">
				<tr>
					<th>상품이름</th>
					<td> <input type="text" name="pName"> </td>
				</tr>
				<tr>
					<th>상품 이미지</th>
					<td id="td-pFile"> <input type="file" name="pFile" id="pFile" accept="image/*"> 
					<img  id="preview"/>
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td> <select name="pCategory">
						<option value="1">정육</option>
						<option value="2">해산물</option>
						<option value="3">과일</option>
						<option value="4">국/반찬</option>
						<option value="5">면/샐러드</option>
						<option value="6">주류</option>
					</select> </td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" name="pPrice" required>원</td>
				</tr>
				<tr>
					<th>원산지</th>
					<td><input type="text" name="pOrigin" required></td>
				</tr>
				<tr>
					<th>용량</th>
					<td><input type="text" name="pQuantity" placeholder="g단위로 입력하시오" required>g</td>
				</tr>
				<tr>
					<th>할인율</th>
					<td> <input type="number" min="0" max="99" name="pDiscount" required>% </td>
				</tr>
				<tr>
					<th>환불 가능여부</th>
					<td>   <input type="radio" id="Y" name="pRefundOx" value="Y"
             checked> O <input type="radio" id="huey" name="pRefundOx" value="N"
            > X </td>
				</tr>
				<tr>
					<th>재고</th>
					<td> <input type="number" min="0" max="9999" name="pAmount" required>개</td>
				</tr>
				<tr>
					<th>해시태그</th>
					<td> <input id="tag" name="tag"> </td>
				</tr>
			</table>
		
		<div class="btns"> <button type="submit" id="sub1">등록하기</button> </div>
		</form>
     </main>
   
   
   <script>
      
//The DOM element you wish to replace with Tagify

  // initialize Tagify on the above input node reference
  $('#tag').tagify();
	   
   $('#pFile').change(function(){
	   
	   setImageFromFile(this, '#preview');
   });
   
   function setImageFromFile(input, expression) {
	    if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    reader.onload = function (e) {
	    $(expression).attr('src', e.target.result);
	  }
	  reader.readAsDataURL(input.files[0]);
	  }
	}
// 	   alert(e.target.result);
   
//    });	   
// 	   $.ajax({  //ajax 객체 생성 
			
// // 			1. 페이지 요청 정보
// 			type: 'POST',
// 			url: '/ssy/product/productimage.do',
			
// 			//2. 데이터 전송
// 			data: 'data=' + image,
			
// 			//3. 결과 수신 이벤트
// 			success: function(result) {
// 				$('#td-pFile').append(result);
// 			},
			
// 			//ajax 에러 처리
// 			error: function(a, b, c) {
// 				console.log(a, b, c);
// 			}
			
// 		});
		
// 	});
 
   
   
   </script>
</body>
</html>


