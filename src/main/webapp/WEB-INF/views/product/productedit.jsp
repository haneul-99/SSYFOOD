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
   
   .sub1 {
   	 font-size: 1.5rem;
   	 background-color:tomato;
   	 border-radius: 10px;
   	 color:white;
   	 height: 50px;
   	 border: 2px dashed white;
   	 box-shadow: 0 0 0 5px tomato;
   	 margin-right:30px; }
   	 
   	 #preview{
   	 width: 200px;
   	 height: 200px;
   	 border:none;
   	 }
</style>
</head>
<body>
   <main>
      	<h1>상품수정하기</h1>
		<hr>
		<form action="/ssy/product/producteditok.do" enctype="multipart/form-data" method="POST">
		
		<h2>상품 정보 작성</h2>
			<table class="tbl1">
				<tr>
					<th>상품이름</th>
					<td> <input type="text" name="pName" value="${dto.pname}" required> </td>
				</tr>
				<tr>
					<th>상품 이미지 <br>(수정을 원치않을경우 파일선택x)</th>
					<td id="td-pFile"> <input type="file" name="pFile" id="pFile" accept="image/*" >
					<span>기존이미지:${dto.pimage}</span> 
					<input type="hidden" value="${dto.pimage}" name="beforeImage" >
					<img  id="preview"/>
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td> <select name="pCategory" id="sel1">
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
					<td><input type="text" name="pPrice" required value="${dto.pprice}">원</td>
				</tr>
				<tr>
					<th>원산지</th>
					<td><input type="text" name="pOrigin" required value="${dto.porigin}"></td>
				</tr>
				<tr>
					<th>용량</th>
					<td><input type="text" name="pQuantity" placeholder="g단위로 입력하시오"  value="${dto.pquantity}" required>g</td>
				</tr>
				<tr>
					<th>할인율</th>
					<td> <input type="number" min="1" max="99" name="pDiscount" required value="${dto.pdiscount}">% </td>
				</tr>
				<tr>
					<th>환불 가능여부</th>
					<td>   <input type="radio" id="Y" name="pRefundOx" value="Y" class="rad1"
             checked> O <input type="radio" id="huey" name="pRefundOx" value="N" class="rad1"
            > X </td>
				</tr>
				<tr>
					<th>재고</th>
					<td> <input type="number" min="1" max="9999" name="pAmount" required value="${dto.pamount}">개</td>
				</tr>
				
				<tr>
				
					<th>해시태그</th>
					<td><input id="tag" name="tag" value="${list}">  </td>
				</tr>
			</table>
		
		<input type="hidden" value="${dto.pseq}" name="pSeq">
		<div class="btns"><button type="button" class="sub1" onclick="history.back()">돌아가기</button> 
		<button type="submit" class="sub1">수정하기</button> </div>
		</form>
   </main>
   <script>
   $('#tag').tagify();
   
      $('#sel1').val(${dto.cseq}).prop("selected",true);
      $('.rad1').val('${dto.prefundox}').prop('checked', true); 
      $('#tag').attr("value",'안녕');
    		  
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
   </script>
</body>
</html>


