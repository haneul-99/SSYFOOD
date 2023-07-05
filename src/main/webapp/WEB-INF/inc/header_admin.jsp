<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
   #logo {
      background-image: url("/ssy/asset/images/로고.png");
      background-size: 100px auto ;
      background-repeat: no-repeat;
      width: 100px;
      height:100px;
   }
   
   #head {
      margin: 30px auto;
      height: 70px;
      width: 700px;
      display:flex;
      justify-content: space-between;
      margin-bottom: 0px;
      position:relative;
   }
   
   #search {
      width: 400px;
      height: 30px;
   }
        
    i{
    margin-right: 10px;
    font-size: 1.5rem;
    }
    
    #div-search {
       position: relative;
       margin-right: 100px;
    }
    
    #i-glass {
       position: absolute;
       top:5px;
       right: 5px;
       background-color:transparent;
       border: none;
    }
    #div-login {
       position:absolute;
       right:-255px;
       top:-20px;
    }
    #div-login > a {
       margin: 0 10px;
    }
    
    ul{
    display:flex;
    justify-content:center;
    }
    
    li {
       list-style-type:none;
       border-right:1px solid #777;
       padding: 0 20px;
       font-size: 13px;
    }
    
    ul > li:nth-child(9) {
       border:none;
    }
    
    
</style>
    
<header>

   <div id="head">
         <div id="logo">
         </div>
         <div id="div-search">
         <input type="text" placeholder="원하시는 상품/판매자를 입력해주세요." id="search">
         <button id="i-glass"> <i class="fa-solid fa-magnifying-glass" ></i></button>
         </div>
         
         
   <div id="div-login">
         <a href="#!">로그인</a>
         <a href="#!">회원가입</a>
         </div>
   </div> <!-- head -->
         <hr>
         <nav>
            <ul>
               <li>공지사항 작성</li>
               <li>이벤트 작성</li>
               <li onclick="location.href='/ssy/admin/coupon.do';">쿠폰/포인트</li>
               <li onclick="location.href='/ssy/admin/adminpageseller.do';">판매자 관리</li>
               <li onclick="location.href='/ssy/admin/adminpage.do';">회원 관리</li>
               <li>고객센터</li>
               <li>전체 주문 관리 내역</li>
               <li>설문조사 통계</li>
               <li onclick="location.href='/ssy/admin/deliver.do';">택배사 정보</li>
              
            
            
            </ul>
         </nav>
         <hr>
      </header>   