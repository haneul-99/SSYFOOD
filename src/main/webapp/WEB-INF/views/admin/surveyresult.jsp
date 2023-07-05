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
#survey-chart {
	width: 800px;
}

#tbl {
	text-align: center;
	border: none;
	padding : 5px;
}

#tbl th:nth-child(1) {
	width: 150px;
}

#tbl th:nth-child(2) {
	width: 620px;
}

#ul-seller > li:nth-child(5) {
	 	background-color: tomato;
	 	color: white;
	 	font-size: 1.3rem;
	 	font-weight: bold;
}
</style>
</head>
<body>

	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<main>
		<%@ include file="/WEB-INF/inc/header.jsp"%>

<%@ include file="/WEB-INF/inc/adminnav.jsp"%>

		<h2>설문조사 결과</h2>

		<table id="tbl"  class="tbl1">
			<tr>
				<td>
					<h3>객관식 문항</h3>
					<div id="survey-chart">
						<canvas id="myChart" style="width: 700px; display: inline-block;"></canvas>
					</div>
				</td>
				<td>
					<div>
						<h3>서술형 문항</h3>
						<table id="tbl1" class="tbl1">
							<tr>
								<th>회원이름</th>
								<th>내용</th>
							</tr>
							<c:forEach items="${list6}" var="dto">
								<tr>
									<td>${dto.mname}</td>
									<td>${dto.resultq6}</td>
								</tr>
							</c:forEach>
							
						</table>
							<div id="pagebar">${pagebar}</div>
					</div>

				</td>
			</tr>
		</table>



		<%@ include file="/WEB-INF/inc/footer.jsp"%>
	</main>
	<script>
			const colors = [ 'tomato', 'gold', 'cornflowerblue', 'greenyellow',
				'violet'];
		var chBar = document.getElementById("myChart");
		var chartData = {
			labels : [ "Q1", "Q2", "Q3", "Q4", "Q5" ],
			datasets : [ {
				data : [
					<c:forEach items="${list1}" var="n">
					${n}, 
					</c:forEach>
				],
				backgroundColor : colors[0],
				label : "1번 투표수(명)"
			}, {
				data : [ 
					<c:forEach items="${list2}" var="n">
					${n}, 
					</c:forEach>					
				],
				backgroundColor : colors[1],
				label : "2번 투표수(명)"
			}, {
				data : [ 
					<c:forEach items="${list3}" var="n">
					${n}, 
					</c:forEach>					
				],
				backgroundColor : colors[2],
				label : "3번 투표수(명)"
			}, {
				data : [ 
					<c:forEach items="${list4}" var="n">
					${n}, 
					</c:forEach>					
				],
				backgroundColor : colors[3],
				label : "4번 투표수(명)"
			}, {
				data : [ 
					<c:forEach items="${list5}" var="n">
					${n}, 
					</c:forEach>					
				],
				backgroundColor : colors[4],
				label : "5번 투표수(명)"
			} ]
		};

		var myChart = new Chart(chBar, {
			// 챠트 종류를 선택
			type : 'bar',

			// 챠트를 그릴 데이타
			data : chartData,

			// 옵션
			options : {
				legend : {
					display : false
				}
			}
		});
	</script>
</body>
</html>


