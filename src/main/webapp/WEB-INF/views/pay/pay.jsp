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
   
   table {
   		width: 750px;
   		margin: 0 auto;
   		margin-top: 20px;
   		margin-bottom: 10px;
   		
   }
   
   table th {
   		width: 120px;
   }
   
   table td {
   		font-size: 14px;
   		padding-left: 20px;
   }
   
   .btns {
   		margin-bottom: 10px;
   		position: right;
   		text-align: right;
   		margin-right: 230px;
   		margin-bottom: 100px;
   		margin-top: 20px;
	}
	
	img {
		width: 300px;
		height: 300px;
		margin: 10px;
		
	}
	
	.btn {
		cursor: pointer;
		border: 2px solid cornflowerblue;
		width: 100px;
		background-color: white;
	}
	
	h2 {
		
		margin-left: 100px;
		margin-right: 100px;
		border: 3px dashed #999;
		border-radius: 10px;
		padding: 10px;	
	}
	
	.i-btn {
		margin-right: 10px;
	}
	.address {
	
	width: 500px;
	}
	
   
</style>
</head>
<body>
   
   
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
		
		<section>
			
			<h2>※ 결제 페이지</h2>
		<form action="/ssy/pay/payok.do" method="POST">
			<table class="payinfo tbl1">
				
				
				<tr>
					<th>
						상품
					</th>
					<td>
						<div id="pimage">
							<img src="/ssy/asset/images/${dto.pimage}" class="items-img">
						</div>
						<hr>
						<div id="pname">
							<p>${dto.pname}</p>
						</div>
					</td>
				</tr>
				
				<tr>
					<th>
						가격
					</th>
					<td>
						<div>
							<c:if test="${dto.pdiscount == 0}"> 
							<span class="origin-price"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/> <span>원</span> </span>
							</c:if>
					
							<c:if test="${dto.pdiscount != 0}"> 
							<span class="origin-price" style="text-decoration:line-through; color: #999"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/> <span>원</span> </span>
							→ <span><fmt:formatNumber value="${dto.pprice*(1-dto.pdiscount/100)}" pattern="#,###"/>원</span>
							</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						수량
					</th>
					<td>
						<input type="number" name="pcount" size="1" min="1" max="1000" value="1" id="num1" onchange="$('#allpprice').text((number_format(parseInt($('#num1').val()) * ${dto.pprice*(1-dto.pdiscount/100)})) + '원');" required>
					</td>
				</tr>
				<tr>
					<th>
						총 구매가격
					</th>
					<td>
						<div>
							<c:if test="${dto.pdiscount == 0}"> 
							<span id="allpprice"><fmt:formatNumber value="${dto.pprice}" pattern="#,###"/><span>원</span> </span>
							
							<input type="hidden" name="pdprice" value="<fmt:formatNumber value="${dto.pprice}" pattern="#"/>">
							</c:if>
							<c:if test="${dto.pdiscount != 0}"> 
								<span id="allpprice"><fmt:formatNumber value="${dto.pprice*(1-dto.pdiscount/100)}" pattern="#,###"/>원 </span>
								
								<input type="hidden" name="pdprice" value="<fmt:formatNumber value="${dto.pprice*(1-dto.pdiscount/100)}" pattern="#"/>">
							</c:if>
						</div>
					</td>
				</tr>
			</table>
			
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
						<input type="text" id="sample6_address" placeholder="주소" name="oladdress" >
						<input type="text" id="sample6_detailAddress"  placeholder="상세주소" name="oladdress">
						<input type="text" id="sample6_extraAddress" placeholder="부주소" style="display:none">
					
<%-- 						<input type="text" class="address" value="${mdto.maddress}" name="oladdress"> --%>
					</td>
				</tr>
				
			</table>
			<div class="btns">
				<button type="submit" class="btn" id="btn1"><i class="fa-solid fa-coins i-btn" style="color:gold;"></i>결제하기</button>
				<button type="button" class="btn" id="btn2"><i class="fa-regular fa-circle-xmark i-btn" style="color:tomato;"></i>취소하기</button>
				<input type="hidden" value="${dto.pseq}" name="pseq">
			</div>
			</form>
			
			
			

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		</section>
		
      <%@include file="/WEB-INF/inc/footer.jsp"%>
     </main>
   
   
   <script>
   <c:if test="${lv == 2 || lv == 3}">
   alert('회원만 구매가 가능합니다.');
   history.back()
   </c:if>
	   
	   function number_format(num){
	       return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',');
	   }
	   let tmp = number_format(total)
	   $('#div-totalPrice').append(tmp+' 원');
   
	   
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


