<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
		
		html,body{
		margin:0;
		padding:0;
		}
		footer {
			min-height: 300px;
			display:flex;
			margin:30px 0;
		}		
		
		#foot_info{
			font-size: 4rem;	
					width:1200px;
			
			padding: 50px;		
			margin:0 auto;
		}
		
		#foot_info div {
				font-size: 3rem;
				margin: 20px;
		}
		#foot_info div:nth-child(3),#foot_info div:nth-child(3) a {
				font-size: 1.5rem;
		}
		#foot_info div:nth-child(2) >span{
			padding-left: 20px;
		}
		#foot_info div > span {
			font-size: 1.5rem;
		}
	</style>
    <hr>
   	<footer>
	   <div id="foot_info">
	   	<div >싸이푸드 고객센터</div>
	   	<div>1600-0000<span>화~목요일 오후 1시 - 오후 6시</span></div>
	   	<div>비회원 문의 : <a href="#1">sos@ssyfood.com</a>
	   	<br>비회원 대량주문 문의 : <a href="#!">sos@ssyfood.com</a></div>
	   	</div>
	</footer>
