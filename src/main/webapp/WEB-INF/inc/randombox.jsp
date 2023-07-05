<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
#floatMenu {
	position: absolute;
	width: 100px;
	height: 100px;
	left: 1390px;
	top: 250px;
	background-color: greenyellow;
	border: 0;
	border-radius: 10px;
}

table {
	margin: 0 auto;
	text-align: center;
	padding: 10px;
}

#rboxpopup, #boompopup {
	cursor: pointer;
}

#rbox {
	width: 50px;
	height: 50px;
}

#boom {
	margin: 0 auto;
	width: 80px;
	height: 50px;
}
</style>

<body>
	<main>
		<c:if test="${not empty auth and lv == 1}">
			<div id="floatMenu">
				<table onclick="openPopUp()" id="rboxpopup">
					<tr>
						<td>랜덤박스</td>
					</tr>
					<tr>
						<td><img src="/ssy/asset/pic/randombox.png" id="rbox">
						</td>
					</tr>
				</table>
			</div>
				
		</c:if>
		<input type="hidden" name="mseq" value="${mseq}">
	</main>
	<script>
		
		$(document).ready(function() {

			// 기존 css에서 플로팅 배너 위치(top)값을 가져와 저장한다.
			var floatPosition = parseInt($("#floatMenu").css('top'));
			// 250px 이런식으로 가져오므로 여기서 숫자만 가져온다. parseInt( 값 );

			$(window).scroll(function() {
				// 현재 스크롤 위치를 가져온다.
				var scrollTop = $(window).scrollTop();
				var newPosition = scrollTop + floatPosition + "px";

				/* 애니메이션 없이 바로 따라감
				 $("#floatMenu").css('top', newPosition);
				 */

				$("#floatMenu").stop().animate({
					"top" : newPosition
				}, 500);

			}).scroll();

		});

		function openPopUp() {
			// 함수 동작 테스트 
			//alert("팝업 테스트");

			//window.open("[팝업을 띄울 파일명 path]", "[별칭]", "[팝업 옵션]")
			window.open("randombefore.do?mseq=${mseq}", "randombefore",
					"width=500, height=600, top=150, left=200");
		}

	</script>
</body>
















