<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
#logo {
	background-image: url("/ssy/asset/images/로고.png");
	background-size: 100px auto;
	background-repeat: no-repeat;
	width: 100px;
	height: 100px;
	cursor: pointer;
}

#head {
	margin: 30px auto;
	height: 70px;
	width: 700px;
	display: flex;
	justify-content: space-between;
	margin-bottom: 0px;
	position: relative;
}

#search {
	width: 400px;
	height: 30px;
}

#i-glass {
	margin-right: 10px;
	font-size: 1.5rem;
}

#div-search {
	position: relative;
	margin-right: 100px;
}

#i-glass {
	position: absolute;
	top: 3px;
	right: 5px;
	background-color: transparent;
	border: none;
}

#div-login {
	position: absolute;
	right: -255px;
	top: -30px;
}

#div-login>a {
	margin: 0 10px;
}

#header-ul {
	display: flex;
	justify-content: center;
}

#header-ul li {
	list-style-type: none;
	border-right: 1px solid #777;
	padding: 0 30px;
	cursor: pointer;
}

#header-ul>li:nth-child(5) {
	border: none;
}

.btn-info {
	border-radius: 50%;
	margin-right: 10px;
	background-color: white;
}

#btn-bucket{
		background-color:transparent;
		border:none;
		float:right;
		position:absolute;
		right:-100px;
		top:20px;
		font-size: 1.4rem;
		
	
	}
	#btn-jjim{
		background-color:transparent;
		border:none;
		float:right;
		position:absolute;
		right:-180px;
		top:18px;
		font-size: 1.4rem;
	}
	
	.i-rndbox:hover {
      color:gold;
   }
   
   .i-jjim{
   transition: all .5s;
   }
   
   .i-jjim:hover{
      color:tomato;
   transform: translateY(-3px);
         
   }
   .i-bucket:hover {
/*       color:cornflowerblue; */
/*       transform: translateX(3px);    */
   animation: .1s  btn ease-in infinite;
   }
   .i-bucket{
/*    transition: all .5s; */
   
   }
   
   @keyframes btn {
   from{
      transform: translateX(-1px);
      color:cornflowerblue;
   }
   to{
      transform: translateX(1px);
      color:cornflowerblue;
   }
   
   }
</style>

<header>
	<div id="head">
		<div id="logo" onclick="location.href='/ssy/index.do'"></div>

		<form action="/ssy/product/product.do" method="GET">
			<div id="div-search">
				<input type="text" placeholder="원하시는 상품/판매자를 입력해주세요." id="search"
					name="word">
				<button id="i-glass">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</div>
		</form>

		<div id="div-login">
			<c:if test="${empty auth and empty lv}">
				<a href="/ssy/account/loginchoice.do">로그인</a>
				<a href="/ssy/account/registerchoice.do">회원가입</a>
			</c:if>
			<c:if test="${not empty auth and lv == 1}">
				<button type="button" class="btn-info"
					onclick="location.href='/ssy/member/memberpage.do?';">
					<i class="fa-sharp fa-solid fa-user i-log"></i>
				</button>${mname} 회원님<a href="/ssy/account/logoutok.do">로그아웃</a>
			</c:if>
			<c:if test="${not empty auth and lv == 2}">
				<button type="button" class="btn-info"
					onclick="location.href='/ssy/seller/sellerpage.do?sSeq=${sseq}';"
					style="border-color: cornflowerblue;">
					<i class="fa-sharp fa-solid fa-user i-log"
						style="color: cornflowerblue;"></i>
				</button>${sname} 판매자님 <a href="/ssy/account/logoutok.do">로그아웃</a>
			</c:if>

			<c:if test="${not empty auth and lv == 3}">
				<button type="button" class="btn-info"
					onclick="location.href='/ssy/admin/adminpage.do';"
					style="border-color: tomato;">
					<i class="fa-sharp fa-solid fa-user i-log" style="color: tomato;"></i>
				</button>${aname} 관리자님 <a href="/ssy/account/logoutok.do">로그아웃</a>
			</c:if>
			
			</div>
			<c:if test="${not empty auth and lv == 1}">
			<button type="button" id="btn-bucket" onclick="location.href='/ssy/member/bucket.do?mseq=${mseq}'"><i class="fa-solid fa-cart-shopping i-bucket"></i><div>장바구니</div></button>
			<button type="button" id="btn-jjim" onclick="location.href='/ssy/member/jjim.do?mseq=${mseq}'"><i class="fa-solid fa-heart i-jjim"></i><div>찜목록</div></button>
			</c:if>

		</div>
	</div>
	<!-- head -->
	<hr>
	<nav>
		<ul id="header-ul">
			<li><a href="/ssy/product/recommendlist.do">추천상품</a></li>
			<li><a href="/ssy/product/newlist.do">신상품</a></li>
			<li><a href="/ssy/noticeboard/ntlist.do"
				class="header-link">공지사항</a></li>
			<li><a href="/ssy/eventboard/evtlist.do">이벤트</a></li>
			<li><a href="/ssy/complainboard/cplist.do">고객센터</a></li>

		</ul>
	</nav>
	<hr>
</header>
