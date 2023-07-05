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
	
	h2 {
		
		margin-left: 100px;
		margin-right: 100px;
		border: 3px dashed #999;
		border-radius: 10px;
		padding: 10px;	
	}
	
	h3 {
		margin-left: 100px;
		margin-right: 100px;
		border: 3px dashed #999;
		border-radius: 10px;
		padding: 10px;	
	}
	
	
	#pinfo {
		display: flex;
		text-align: center;		
		margin-left: 100px;
		
	}

	img {
		width: 350px;
		height: 350px;
		margin: 100px;
		margin-right: 80px;
		margin-top: 50px;
	}
	
	table {
		height: 450px;
	}
	
	
		
	.btn {
		cursor: pointer;
		width: 220px;
		height: 70px;
		border: 2px solid cornflowerblue;
		background-color: white;
		margin: 10px;
		font-size: 20px;
	}
	
	#btns {
		text-align: center;
		margin-bottom: 100px;
		
	}
	
	.i-btn {
		margin-right:10px;
	}
	
	
	#sellerboard {
		text-align: center;
    	width: 1000px;
    	height: 30px;
    	margin-bottom: 30px;
	}
	
	
	#product-ul {
   
   	list-style-type: none;
   	font-size: 2rem;
   
   	position:fixed;
   	left:60px;
   	
   	
   }
   
   #product-ul > li {
	   text-align: center;
	   margin: 10px 0;
	   border: 2px solid #DDD;
	   cursor:pointer;
   }
   
   li> i {
   	transform: translate(0px, 2px);
   
   }
   
   #product-ul > li:nth-child(${cSeq}) {
    	color: tomato;
    
    }
    
    #pagebar {
    	text-align: center;
    	margin-bottom: 100px;
    	
    }
    
    #editdel {
    	width: 100px;
    }
    
    #editbtn {
    	margin-bottom: 5px;
    }

</style>
</head>
<body>
   
   
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		
		<section>
		
		<h2>※ 상품 상세정보</h2>
		
		<nav>
		<ul id="product-ul">
			<li onclick="location.href='/ssy/product/product.do?cSeq=1&word='"><i class="fa-solid fa-drumstick-bite"></i></li>
			<li onclick="location.href='/ssy/product/product.do?cSeq=2&word='"><i class="fa-solid fa-fish"></i></li>
			<li onclick="location.href='/ssy/product/product.do?cSeq=3&word='"><i class="fa-solid fa-apple-whole"></i></li>
			<li onclick="location.href='/ssy/product/product.do?cSeq=4&word='"><i class="fa-solid fa-bowl-food"></i></li>
			<li onclick="location.href='/ssy/product/product.do?cSeq=5&word='"><i class="fa-solid fa-seedling"></i></li>
			<li onclick="location.href='/ssy/product/product.do?cSeq=6&word='"><i class="fa-solid fa-champagne-glasses"></i></li>
		</ul>
		</nav>
		
		
		<div id="pinfo">
		
		<div id="pimage">
			<img src="/ssy/asset/images/${dto.pimage}" class="items-img">
		</div>
		
		
			<table class="tbl tbl1">
				
				<tr>
					<th>상품명</th>
					<td>
						<div id="pname">
							${dto.pname}
						</div>
					</td>
				</tr>
				<tr>
					<th>가격</th>
					<td>
						<div id="pprice">
							<div>
					
							<c:if test="${dto.pdiscount == 0}"> 
							<span class="origin-price"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/> <span>원</span> </span>
							</c:if>
					
							<c:if test="${dto.pdiscount != 0}"> 
							<span class="origin-price" style="text-decoration:line-through; color: #999"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/> <span>원</span> </span>
							→ <span><fmt:formatNumber value="${dto.pprice*(1-dto.pdiscount/100)}" pattern="#,###"/>원</span>
							</c:if> 
					
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
					<c:if test="${dto.cseq == 1}">
					정육
					</c:if>
					<c:if test="${dto.cseq == 2}">
					해산물
					</c:if>
					<c:if test="${dto.cseq == 3}">
					과일
					</c:if>
					<c:if test="${dto.cseq == 4}">
					국과 반찬
					</c:if>
					<c:if test="${dto.cseq == 5}">
					면과 샐러드
					</c:if>
					<c:if test="${dto.cseq == 6}">
					주류
					</c:if>
						
					</td>
				</tr>
				<tr>
					<th>원산지</th>
					<td>
						<div id="porigin">
							${dto.porigin}
						</div>
					</td>
				</tr>
				<tr>
					<th>용량</th>
					<td>
						<div id="pquantity">
							${dto.pquantity}g
						</div>
					</td>
				</tr>
				<tr>
					<th>할인율</th>
					<td>
						${dto.pdiscount}%
					</td>
				</tr>
				<tr>
					<th>판매장명</th>
					<td>${dto.storename}</td>
				</tr>
				<tr>
					<th>판매장 주소</th>
					<td>${dto.saddress}</td>
				</tr>
				
			</table>
		
		</div>
		
		<div id="btns">
			<button type="button" class="btn" id="btn1"><i class="fa-solid fa-coins i-btn" style="color:gold;"></i>바로구매</button>
			
			<button type="button" class="btn" id="btn2" onclick="location.href='/ssy/member/bucketok.do?pseq=${dto.pseq}&bprice=<fmt:formatNumber value="${dto.pprice*(1-dto.pdiscount/100)}" pattern="#"/>&cseq=${dto.cseq}'"><i class="fa-solid fa-cart-shopping i-btn" style="color:#999;"></i>장바구니</button>
       		<button type="button" class="btn" id="btn3" onclick="if(confirm('찜 하시겠습니까?'))location.href='/ssy/member/jjimok.do?pseq=${dto.pseq}';"><i class="fa-solid fa-heart i-btn" style="color:tomato;"></i>찜하기</button>
			
			<button type="submit" class="btn" id="btn4"><i class="fa-solid fa-user-pen i-btn" style="color:black"></i>문의하기</button>
		</div>
		
		</section>
		
		
		
		
		
		
		
		<hr>
		<h3>판매자 문의게시판</h3>
		
		
				
		<table class= "board tbl1" id="sellerboard">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>회원이름</th>
					<th>작성날짜</th>
					<th id="editdel"></th>		
				</tr>
				<c:if test="${list.size() == 0}">
				<tr>
					<td colspan="5">게시물이 없습니다.</td>
				</tr>
				</c:if>
				
				<c:forEach items="${qnaList}" var="dto" varStatus="status">
				<tr>
					<td onclick="location.href='/ssy/board/qnaview.do?qseq=${dto.qseq}&qtitle=${dto.qtitle}&qcontent=${dto.qcontent}&pseq=${dto.pseq}'" style="cursor:pointer;">${dto.rnum}</td>
					
					<td onclick="location.href='/ssy/board/qnaview.do?qseq=${dto.qseq}&qtitle=${dto.qtitle}&qcontent=${dto.qcontent}&pseq=${dto.pseq}'" style="cursor:pointer;">${dto.qtitle}</td>
					
					<td onclick="location.href='/ssy/board/qnaview.do?qseq=${dto.qseq}&qtitle=${dto.qtitle}&qcontent=${dto.qcontent}&pseq=${dto.pseq}'" style="cursor:pointer;">${dto.mname}</td>
					
					<td onclick="location.href='/ssy/board/qnaview.do?qseq=${dto.qseq}&qtitle=${dto.qtitle}&qcontent=${dto.qcontent}&pseq=${dto.pseq}'" style="cursor:pointer;">${dto.qdate}</td>
					
					<c:if test="${not empty auth and dto.mname == mname}">
					
					<td>
					<input type="hidden" value="${dto.qtitle}" name="qtitle">
					<input type="hidden" value="${dto.qcontent}" name="qcontent">
					<input type="hidden" value="${dto.qseq}" name="qseq">
					<input type="hidden" value="${dto.pseq}" name="pseq">
				
					<button type="button" id="editbtn${status.count}" class="editbtn" onclick="location.href='/ssy/board/qnaedit.do?qseq=${dto.qseq}&qtitle=${dto.qtitle}&qcontent=${dto.qcontent}&pseq=${dto.pseq}'">
						<i class="fa-solid fa-pen-to-square"></i>
						수정하기
					</button>
				
				<form method="POST" action="/ssy/board/qnadelok.do">
					<button type="submit" id="delbtn${status.count}" class="delbtn">
						<i class="fa-solid fa-trash-can"></i>
						삭제하기
					</button>
						<input type="hidden" value="${dto.qseq}" name="qseq">
						<input type="hidden" value="${dto.pseq}" name="pseq">
				</form> 
					</td>
					</c:if>
				</tr>
				
				</c:forEach>
				
			</table>
							
	
			<div id="pagebar">
				${pagebar}
			</div>
			
		
      <%@include file="/WEB-INF/inc/footer.jsp"%>
     </main>
   
   
   <script>
      
	   $("#btn1").click(function(){
		   
		   
		   <c:if test="${empty auth}">
	       if(confirm("로그인 후 이용가능한 서비스 입니다.\r\n 로그인 하시겠습니까?") == true) {
	    	   
	      
	    	  location.href = "http://localhost:8090/ssy/account/memberlogin.do?pseq=" + ${dto.pseq};
	    	  
	    	  

	       } else {
	    	   
	    	   return;
	       }
	       
	       </c:if>
	       <c:if test="${not empty auth}">
	       
	       		location.href = "http://localhost:8090/ssy/pay/pay.do?pseq=${dto.pseq}";
	       
	       </c:if>
		   
	   });
	   

	   
	   
	   $("#btn4").click(function(){
		  
		   <c:if test="${empty auth}">
	       if(confirm("로그인 후 이용가능한 서비스 입니다.\r\n 로그인 하시겠습니까?")) {
	    	  location.href = "http://localhost:8090/ssy/account/memberlogin.do?pseq=" + ${dto.pseq};
	       } else {
	    	   
	    	   return;
	       }
	       </c:if>
	       <c:if test="${not empty auth}">
	       
	       if(confirm("문의하시겠습니까?")) {
		    	  location.href = "http://localhost:8090/ssy/board/qnaadd.do?pseq=" + ${dto.pseq};
		       } else {
		    	   
		    	   return;
		       }
	       
	       </c:if>
		   
	   });
	   
// 	   $(".editbtn").click(function(){
			
// 		   <c:if test="${not empty auth}">
	       
// 	       if(confirm("수정하시겠습니까?")) {
// 		    	  location.href = "http://localhost:8090/ssy/board/qnaedit.do?pseq=" + ${dto.pseq};
// 		       } else {
		    	   
// 		    	   return;
// 		       }
	       
// 	       </c:if>
// 	   });
	   
	   
	   $(".delbtn").click(function(){
       
		   if(confirm("정말 삭제하시겠습니까 ?") == true){
           
			   alert("삭제되었습니다");
       
		   } else {
         
			   return false ;
       
		   }
   });
	   
	   
	   
	   
   
   </script>
</body>
</html>


