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
 text-align: center;
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
 
 span{
 	
 }
   	 
   	 
   </style>
    
	<ul id="ul-seller">
			<li class="li-seller" onclick="location.href='/ssy/member/memberpage.do';">포인트<br>쿠폰함</li>
			<li class="li-seller" onclick="location.href='/ssy/member/memberpage1.do';">주문내역</li>
			<li class="li-seller" onclick="location.href='/ssy/member/memberpage4.do';">개인정보<br>수정</li>
			<li class="li-seller" onclick="location.href='/ssy/member/memberpage5.do';">댓글관리</li>
		</ul>




















