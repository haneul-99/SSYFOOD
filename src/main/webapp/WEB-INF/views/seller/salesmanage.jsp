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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<style>
   #ul-seller{
	list-style-type: none;
	position:fixed;
	left:30px;
}
.li-seller {
	width:100px;
	height:100px;
	border:1px solid black;
	display:flex;
	justify-content:center;
	align-items:center;

}

 .li-seller{
   	 	cursor:pointer;
   	 }
   	 
 #ul-seller > li:nth-child(2) {
 	background-color: tomato;
 	color: white;
 	font-size: 1.3rem;
 	font-weight: bold;
 }
   	 
#piechart1 { width: 760px; height: 550px; margin: 0 auto;}

.sales{
	border-bottom:1px solid #999;
	padding: 10px 0;
}

   #ul-seller > li:nth-child(2) {
 	background-color: tomato;
 	color: white;
 	font-size: 1.3rem;
 	font-weight: bold;
 	}
 	
 	#tbl2>tr:first-child {
 		position:relative;
 	}
 	#tbl2 {
 		border:10px solid #EEE;
 		width: 1200px;
 		min-height:100%;
 		border-collapse:collapse;
 	}
 	#tbl2 td{
 		border:10px solid #EEE;
 		width: 400px;
 	}
 	#piechart1 {
 	}
 	
 	g{
 	width:400px;
 	height:400px;}
 	
 	.ctd1{
 	padding-bottom: 200px;}
 	.ctd2{
 	padding-bottom: 350px;}
 	#a1{
 	width: 300px;
 	}
 	.sales, .sales span {
 	font-size: 1.3rem;
 	}
</style>
</head>
<body>
     <main>
		<%@include file="/WEB-INF/inc/header.jsp" %>
			<%@include file="/WEB-INF/inc/sellernav.jsp" %>
		
		 <section>
      <h1>매출관리</h1>
      	<table id="tbl2"  class="tbl1">
      		<tr>
      		<td id="a1">
      			<h3>상품별 판매량(개)</h3>
		     	<div id="piechart1"></div>
		     </td>
      		<td>
      			<h3>매출현황</h3>
      			<c:if test="${todaySales == null}">
		     	<div class="sales"><span>오늘 매출:0 원</span> </div>
		     	</c:if>
		     	
		     	<c:if test="${todaySales != null}">
		     	<div class="sales"><span>오늘 매출:<fmt:formatNumber value="${todaySales}" pattern="#,###"/> 원</span> </div>
		     	</c:if>
		     	
		     	<c:if test="${yesterdaySales == null}">
		     	<div class="sales"><span>어제 매출: 0 원</span> </div>
		     	</c:if>
		     	<c:if test="${yesterdaySales != null}">
		     	<div class="sales"><span>어제 매출: <fmt:formatNumber value="${yesterdaySales}" pattern="#,###"/> 원</span> </div>
		     	</c:if>
		     	<div id="div-totalPrice" class="sales"  style="color:cornflowerblue; font-size:1.5rem"><span style="font-size:1.5rem">총 매출액:<fmt:formatNumber value="${totalSales}" pattern="#,###"/></span> </div>
		     	<h3 style="margin-top: 60px;">팔로우 현황</h3>
		     	<div class="sales"><span>팔로우:</span> </div>
		     </td>
      		</tr>
      		<tr>
      			<td colspan="2" class="ctd1">
	      			<h3>상품별 매출액(천 원) </h3>
				    <div id="priceChart" style="width: 900px; height: 200px;"></div>	
	      		</td>
			</tr>
      	
      		<tr>
      			<td colspan="2">
	      		</td>
			</tr>
      	</table>
      
      </section>
     </main>
   
   
   <script>
   
   //총매출액 구하기
   let a = 10;
   let total = 0;
   <c:forEach items="${cList}" var="dto">
   total += ${dto.price}
   </c:forEach>
   
   function number_format(num){
	    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',');
	}
   let tmp = number_format(total)
   $('#div-totalPrice').append(tmp+' 원');
   
   
   
   //판매량 차트
   google.charts.load("current", {packages:["corechart"]});
   google.charts.setOnLoadCallback(drawChart);
   function drawChart() {
     var data = google.visualization.arrayToDataTable([
       ['Product', 'cnt'],
       <c:forEach items="${cList}" var="dto">
       ['${dto.productname}',${dto.count}],
       </c:forEach>
     ]);

     var options = {
     };

     var chart = new google.visualization.PieChart(document.getElementById('piechart1'));
     chart.draw(data, options);
   }
   
   //매출액 차트
   google.charts.setOnLoadCallback(drawChart2);
    function drawChart2() {
    	let color = Math.floor(Math.random()*800)+100;
    	
      var data = google.visualization.arrayToDataTable([
        ["Element", "Density", { role: "style" } ],
        <c:forEach items="${cList}" var="dto">
        ['${dto.productname}',${dto.price}/1000, "#" + Math.floor(Math.random()*800)+100],
        </c:forEach>
      ]);
      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        width: 1000,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.BarChart(document.getElementById("priceChart"));
      chart.draw(view, options);
   }
    
  
    //오늘 매출 건수 
        google.charts.load('current', {'packages':['line']});
      google.charts.setOnLoadCallback(drawChart3);

    function drawChart3() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', 'Month');
      data.addColumn('number', '판매량(원)');

      data.addRows([
        [1,  0],
        [2,  8900],
        [3,  0],
        [4,  0],
        [5,  8900],
        [6,   8900],
        [7,   0],
        [8,   0],
        [9,   4583000],
        [10,   0],
        [11,   17800],
        [12,   0],
      ]);

      var options = {
        chart: {
        },
        width: 1000,
        height: 500
      };

      var chart = new google.charts.Line(document.getElementById('salesCount'));

      chart.draw(data, google.charts.Line.convertOptions(options));
    }
    
    
   </script>
</body>
</html>


