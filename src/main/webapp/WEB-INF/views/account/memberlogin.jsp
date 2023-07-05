<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSY Food</title>
<%@ include file="/WEB-INF/inc/asset.jsp"%>
<style>
.head {
   font-size: 2rem;
   margin-top: 100px;
   margin-bottom: 20px;
}



.txt {
   width: 300px;
}

table th {
   width: 150px;
}



.find {
   text-align:right;
   color: #444;
   margin-right:150px;
}

.btn {
   width: 500px;
   margin: 20px 0;
   height: 50px;
   border:none;
   background-color: tomato;
   padding:10px;
   font-size: 1.2rem;
   color:white;
   
   
}
body main{
   min-height: 500px;
   
}

.btns{
   margin: 30px auto;
   width: 800px;
   display:flex;
   flex-direction:column;
   align-items:center;
}

.tbl2{
margin:0 auto;
}

.head {
width: 800px;
margin:20px auto;

}
.head >span {
font-size: 3rem;
margin-bottom: 50px;
}


.form1 {
margin-top: 30px;
display:flex;
flex-direction:column;
align-items:center;


}

.form1  div:first-child {
   font-size: 1.3rem;
   text-align:left;
   margin-right: 450px;
}
#idForm div:first-child{
   margin-right: 465px;
}

.txt {
margin: 10px 0;
width: 500px;
padding: 10px;
height: 30px;
font-size: 1.5rem;

}

</style>
</head>
<body>
   <%@ include file="/WEB-INF/inc/header.jsp"%>

   <main>

      <!-- 메인화면 -->
      <form method="POST" action="/ssy/account/memberloginok.do">

         <div class="head">
         <span>회원 로그인</span>
            <div class="div_loginForm">
            
            <div id="idForm" class="form1">
            <div>아이디</div>
               <div> 
               <input type="text" class="txt" id="id" name="id"
                  placeholder="아이디 입력" required></input>
               </div>
            </div>
            <div id="pwForm" class="form1">
            <div>비밀번호</div>
            <div>   
            <input type="password" class="txt" id="pw" name="pw"
                  placeholder="비밀번호 입력" required></input>
            </div>
            </div>
         <div class="find">
            <a href="/ssy/account/findid.do" class="find-font">아이디 찾기</a> <span>|</span>
            <a href="/ssy/account/findpw.do" class="find-font">비밀번호 찾기</a>
         </div>
            </div>
      </div>
         <div class="btns">
            <div><button type="submit" class="btn">로그인</button></div> 
            <div> <input type="button" value="돌아가기" class="btn"
               onclick="location.href='/ssy/account/loginchoice.do';"></div> 
         </div>
      </form>

      <!-- 테스트용 회원 아이디 접속 -->
      <form method="POST" action="/ssy/account/memberloginok.do">
         <input type="hidden" name="id" value="QBUvsH23"> 
         <input type="hidden" name="pw" value="zujyytpw95"> 
         <input type="submit" value="테스트멤버로그인">
      </form>


   </main>
      <%@include file="/WEB-INF/inc/footer.jsp"%>
   <script>
      
   </script>
</body>
</html>

