<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <style>
    #ul-seller{
		list-style-type: none;
		position:fixed;
		left:10px;
	}
	.li-seller {
		width:100px;
		height:100px;
		border:1px solid black;
		display:flex;
		justify-content:center;
		align-items:center;
	}

 .li-seller{
   	 	cursor:pointer;
   	 }
   	 /*
   	  #ul-seller > li:nth-child() {
	 	background-color: tomato;
	 	color: white;
	 	font-size: 1.3rem;
	 	font-weight: bold;
 	}
 */
   	 
   	 
   </style>
    
	<ul id="ul-seller">
			<li class="li-seller" onclick="location.href='/ssy/seller/sellerpage.do?sSeq=${sseq}';">상품관리</li>
			<li class="li-seller" onclick="location.href='/ssy/seller/salesmanage.do?sSeq=${sseq}';">매출관리</li>
			<li class="li-seller" onclick="location.href='/ssy/seller/sellerboardpage.do?sSeq=${sseq}';">판매자문의</li>
			<li class="li-seller" onclick="location.href='/ssy/seller/orderprocess.do?sSeq=${sseq}';">주문처리</li>
		</ul>
























