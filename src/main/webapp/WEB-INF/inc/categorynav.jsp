<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   <style>
   
    #product-ul > li:nth-child(${cSeq}) {
	   	color: tomato;
    }
	
	#product-ul {
	   	list-style-type: none;
	   	font-size: 2rem;
	   	position:fixed;
	   	left:60px;
	   	top: 180px;
	}
   
   #product-ul > li {
	   text-align: center;
	   margin: 10px 0;
	   border: 2px solid #DDD;
	   cursor:pointer;
	   width: 60px;
	   height: 60px;
   }
   
   
   li> i {
   	transform: translate(0px, 10px);
   	
   }
      
   </style>
   
    
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