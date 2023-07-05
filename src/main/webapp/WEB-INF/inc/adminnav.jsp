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
   	 
   	 
   </style>
    
	<ul id="ul-seller">
			<li class="li-seller" onclick="location.href='/ssy/admin/adminpage.do';">회원관리</li>
			<li class="li-seller" onclick="location.href='/ssy/admin/adminpageseller.do';">판매자관리</li>
			<li class="li-seller" onclick="location.href='/ssy/admin/deliver.do';">택배사 정보</li>
			<li class="li-seller" onclick="location.href='/ssy/admin/coupon.do';">쿠폰/포인트</li>
			<li class="li-seller" onclick="location.href='/ssy/admin/surveyresult.do';">설문조사<br>결과</li>
		</ul>























