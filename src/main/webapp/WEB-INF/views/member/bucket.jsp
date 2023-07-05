<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSY Food</title>
<%@ include file="/WEB-INF/inc/asset.jsp" %>
<style>
   table.tbl1 {
   
   margin-top: 50px ; 
   width: 1050px;
   }
   
   table.tbl1 td {font-size:1.4rem;}
   table.tbl1 td span {font-size:1.4rem;}
   .tbl1 th:nth-child(1){width:70px;}
   .tbl1 td:nth-child(1){ text-align:center;}
   .tbl1 th:nth-child(2){width:200px}
   .tbl1 th:nth-child(3){width:200px;}
   .tbl1 td:nth-child(3){text-align:center;}
   .tbl1 th:nth-child(4){width:130px;}
   .tbl1 td:nth-child(4){text-align:right;}
   .tbl1 th:nth-child(5){width:auto;}
   .tbl1 th:nth-child(6){width:130px;}
   .tbl1 td:nth-child(6){text-align:center;}
   .tbl1 td:nth-child(5){text-align:center;}
   .tbl1 td:nth-child(7){text-align:center;}
   .btns{
   text-align: right;
   padding-right: 50px;
   margin: 50px;
   }
   
   .btn {
   width: 150px;
   height: 70px;
   border:none;
   background-color: tomato;
   color:white;
   font-size: 1.5rem;
   
   }
   
   /*  */
input[type="checkbox"] {
width: 20px;
height: 20px;
}

input[type="number"] {
	width: 130px;
	height: 20px;
	font-size: 1.4rem;
	text-align: right;
	

}

td{
position:relative;

}

   .btnDel{
   position:absolute;
   right: -50px;
   background-color:tomato;
   color:white;
   font-size: 1.3rem;
   border:none;
   }
   
</style>
</head>
<body>
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		
		<h1> ${mname}님의 장바구니</h1>
		<form method="POST" action="/ssy/pay/bucketpay.do">
		<table class="tbl1">
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>상품이미지</th>
				<th>가격</th>
				<th>개수</th>
				<th>개별구매</th>
				<th> <input type="checkbox" checked id="allcb"> </th>
			</tr>
				<c:if test="${bList.size() == 0}">
			<tr>
				<td colspan="7">담긴 상품이 없습니다.</td>
			</tr>
				</c:if>
			
			<c:forEach items="${bList}" var="dto" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${dto.pname}</td>
				<td> <img src="/ssy/asset/images/${dto.pimage}" width="150" height="150"> </td>
				<td>
					 <span id="span-price${status.count}" class="cl-price"><fmt:formatNumber value="${dto.bprice}" pattern="#,###"/><span>원</span></span>
				</td>
				<td> 
				<input type="number" min="1" max="9999" name="pcount" id="pcount${status.count}"  class="cl-count" value="1" height="50" onchange="$('#span-price${status.count}').text(number_format(parseInt($('#pcount${status.count}').val()) * ${dto.bprice}) + '원'); 
				 "> 
				 <%-- $('#realPrice').text(number_format(parseInt($('#lastPrice').text()) + ${dto.bprice} * parseInt($('#pcount${status.count}').val()-1))); --%>
			
				</td>
				<td>  <a href="#!"> 이 상품만 구매하기 </a></td>
				<td> 
				<input type="checkbox" class="cb1" checked>
				<button type="button" class="btnDel" onclick="if(confirm('장바구니에서 삭제하시겠습니까?'))location.href='/ssy/member/bucketdel.do?bseq=${dto.bseq}';"><i class="fa-solid fa-trash"></i></button>
				<input type="hidden" value="${dto.pseq}" name="pseq">
				 </td>
			</tr>
		</c:forEach>
		<c:if test="${bList.size() != 0}">
				
		<tr>
		<td colspan="7"><span>총 구매가격: </span><span id="lastPrice" style="display:none;"></span> 
		 <span id="realPrice"> </span>
		 <input type="hidden" value="" name="totalprice" id="totalprice">
		 <span>원</span> </td>
		</tr>
		</c:if>
		</table>
		<div class="btns">
		<button type="button" class="btn" onclick="history.back()">뒤로가기</button>
		<button  type="submit" class="btn" onclick="if(${bList.size() == 0}){alert('장바구니 내역이 없습니다.');  event.preventDefault()}">일괄구매</button>
		
		</div> 
		
		</form>
		
		
		
     </main>
   
   
   <script>
   
   
   
   
   
   
   
   
   	$('#allcb').click(()=>{
   	
   		if($('#allcb').prop("checked")){
   			$('.cb1').prop("checked",true);
   		} else {
   			$('.cb1').prop("checked",false);	
   		}
   		
   	})
   	
    $(".cb1").click(function(){
  $("#allcb").prop("checked", false);
 });
   
   	
    function number_format(num){
	       return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',');
	   }
    
//     	$('#lastPrice').ready(function(){
    		
			let sum = 0;    		
    		realsum = 0;
    		$('.cl-price').each((index,item)=>{
				
    			let tmp = item.innerText.substring(0,item.innerText.indexOf('원'));
    			tmp = parseInt(tmp.replace(",",""));
    			sum += tmp;
    		});
			$('#realPrice').text(number_format(sum)); 
			$('#totalprice').val(sum);
    		
			
    		$('.cl-count').change(function () {
    			
    			let tmp = 0;
    			let realsum = 0;
    			
    			$('.cl-price').each((index,item)=>{
					
        			let tmp = item.innerText.substring(0,item.innerText.indexOf('원'));
        			tmp = parseInt(tmp.replace(",","").replace(",",""));
        			realsum += tmp;
        		});
    			$('#realPrice').text(number_format(realsum)); 
    			$('#totalprice').val(realsum);
    		})
    		
    		
//     	$('#lastPrice').append(number_format(sum) + '원');
// 			$('#lastPrice').append(sum); 
//     	let totalprice = 0;
    	
    	
    
  
   </script>
</body>
</html>


