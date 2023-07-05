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
   
</style>
</head>
<body>
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		
  
   
   	<h1> ${mname}님의 결제</h1>
		<form method="POST" action="/ssy/pay/bucketpayok.do" onsubmit="return confirm('결제 하시겠습니까?');">
		<table class="tbl1">
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>상품이미지</th>
				<th>가격</th>
				<th>개수</th>
				
			</tr>
				<c:if test="${bList.size() == 0}">
			<tr>
				<td colspan="7">담긴 상품이 없습니다.</td>
			</tr>
				</c:if>
			
			<c:forEach items="${bpList}" var="dto" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${dto.pname}</td>
				<td> <img src="/ssy/asset/images/${dto.pimage}" width="150" height="150"> </td>
				<td>
					 <span id="span-price${status.count}" class="cl-price"><fmt:formatNumber value="${dto.bprice * dto.pcount}" pattern="#,###"/><span>원</span></span>
				</td>
				<td> 
						${dto.pcount}	
						<input type="hidden" name="pcount" value="${dto.pcount}">
						<input type="hidden" name="pseq" value="${dto.pseq}">
						<input type="hidden" name="bprice" value="${dto.bprice}">
						
								
				</td>
			</tr>
			<tr>
			</tr>
		</c:forEach>
		
		<tr>
		
				<td colspan="5">총 구매가격: <fmt:formatNumber value="${totalprice}" pattern="#,###"/> 원</td>
		</tr>
	</table>
				<input type="hidden" name="totalprice" value="${totalprice}">
		<table class="tbl1">
				<tr>
					<th>
						적용 쿠폰
					</th>
					<td>
						<select name="coupon">
							<option value="">::선택</option>
							<option value="1">전체 구매 품목 10% 할인쿠폰</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						결제 방법
					</th>
					<td>
						<select name="pay" required>
							<option value="">::선택</option>
							<option value="카카오페이">카카오페이</option>
							<option value="토스">토스</option>
							<option value="무통장 입금">무통장 입금</option>
							<option value="휴대폰 결제">휴대폰 결제</option>
							<option value="신용카드">신용카드</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<th>
						수령자
					</th>
					<td>
						<input type="text" class="name" value="${mdto.mname}" name="olname">
					</td>
				</tr>
				<tr>
					<th>
						배송지
					</th>
					<td>
						
						<input type="text" id="sample6_postcode" placeholder="우편번호" style="display:none">
						<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
						<input type="text" id="sample6_address" placeholder="주소" name="oladdress" required>
						<input type="text" id="sample6_detailAddress"  placeholder="상세주소" name="oladdress" required>
						<input type="text" id="sample6_extraAddress" placeholder="부주소" style="display:none">
					
<%-- 						<input type="text" class="address" value="${mdto.maddress}" name="oladdress"> --%>
					</td>
				</tr>
				
			</table>
			<div class="btns">
				<button type="submit" class="btn" id="btn1"><i class="fa-solid fa-coins i-btn" style="color:gold;"></i>결제하기</button>
				<button type="button" class="btn" id="btn2" onclick="location.href='/ssy/member/bucket.do';"><i class="fa-regular fa-circle-xmark i-btn" style="color:tomato;" ></i>취소하기</button>
			
			</div>
			</form>
      </main>
      <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   <script>
   function sample6_execDaumPostcode() {
       new daum.Postcode({
           oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 각 주소의 노출 규칙에 따라 주소를 조합한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               var addr = ''; // 주소 변수
               var extraAddr = ''; // 참고항목 변수

               //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
               if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                   addr = data.roadAddress;
               } else { // 사용자가 지번 주소를 선택했을 경우(J)
                   addr = data.jibunAddress;
               }

               // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
               if(data.userSelectedType === 'R'){
                   // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                   // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                   if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                       extraAddr += data.bname;
                   }
                   // 건물명이 있고, 공동주택일 경우 추가한다.
                   if(data.buildingName !== '' && data.apartment === 'Y'){
                       extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                   }
                   // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                   if(extraAddr !== ''){
                       extraAddr = ' (' + extraAddr + ')';
                   }
                   // 조합된 참고항목을 해당 필드에 넣는다.
                   document.getElementById("sample6_extraAddress").value = extraAddr;
               
               } else {
                   document.getElementById("sample6_extraAddress").value = '';
               }

               // 우편번호와 주소 정보를 해당 필드에 넣는다.
               document.getElementById('sample6_postcode').value = data.zonecode;
               document.getElementById("sample6_address").value = addr;
               // 커서를 상세주소 필드로 이동한다.
               document.getElementById("sample6_detailAddress").focus();
           }
       }).open();
   }
   </script>
</body>
</html>


