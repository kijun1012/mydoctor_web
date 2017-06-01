<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<div class="content-wrapper" style="min-height: 1000px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			칼로리<small>칼로리 섭취 기록</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}"><i class="fa fa-dashboard"></i>DashBoard</a></li>
			<li class="active">칼로리</li>
		</ol>
	</section>

	<div class="col-md-6">
		<!-- BAR CHART -->
		<div class="box box-info">
			<div class="box-header with-border">
				<h3 class="box-title">칼로리</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
					
				</div>
			</div>
			<div class="box-body">
				<div class="chart">
					<canvas id="canvas"></canvas>
				</div>
			</div>
			<!-- /.box-body -->
		</div>
	</div>
	<div class="col-md-6">

		<div class="input-group">

			<c:if test="${empty calories}">
				<h2 class="text-yellow">
					저장된 데이터가 없습니다. <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/calorie" role="button">Back</a>
				</h2>
			</c:if>

			<c:if test="${not empty calories }">
				<div class="input-group-addon">
					<div class="col-md-4">
						시작 <input type="text" id="fromDate">
					</div>
					<div class="col-md-4">
						종료 <input type="text" id="toDate">

					</div>
					<div class="col-md-4">
						<button type="button" class="btn btn-default" id="search">조회하기</button>
					</div>

				</div>
			</c:if>

		</div>

		<div style="width: 100%; height: 250px; overflow: auto">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>번호</th>
						<th>날짜</th>
						<th>칼로리</th>
						<sec:authorize access="!hasRole('ROLE_ADMIN')">
							<th></th>
						</sec:authorize>
					</tr>
				</thead>
				<tbody>
					<c:set var="id" value="1" />
					<c:forEach var="calorie" items="${calories}">

						<tr>
							<td><c:out value="${id}" /></td>
							<td>${calorie.measurement_time}</td>
							<td>${calorie.calorie}</td>
							<sec:authorize access="!hasRole('ROLE_ADMIN')">
								<td><a
									href="${pageContext.request.contextPath}/calorie/delete/${calorie.username}/${calorie.measurement_time}">
										<i class="glyphicon glyphicon-remove"></i>
								</a></td>
							</sec:authorize>
						</tr>
						<c:set var="id" value="${id+1}" />
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript">
	var result = new Array();
	var time = new Array();
	var number = 1;

	<c:forEach var="calorie" items = "${calories}">
	result.push("${calorie.calorie}");
	time.push(number);
	number += 1;
	</c:forEach>

	var randomScalingFactor = function() {
		return Math.round(Math.random() * 100)
	};
	var months = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	var barChart = null;
	var barChartData = {
		labels : time,
		datasets : [ {
			label : "칼로리",
			fillColor : "#757575",
			strokeColor : "#757575",
			highlightFill : "rgba(220,220,220,0.75)",
			highlightStroke : "rgba(220,220,220,1)",
			data : result
		} ]

	};

	var barChartOption = {
		//Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
		scaleBeginAtZero : true,

		max : 10000,
		//Boolean - Whether grid lines are shown across the chart
		scaleShowGridLines : true,
		//String - Colour of the grid lines
		scaleGridLineColor : "rgba(0,0,0,0.05)",
		//Number - Width of the grid lines
		scaleGridLineWidth : 1,
		//Boolean - If there is a stroke on each bar
		barShowStroke : false,
		//Number - Pixel width of the bar stroke
		barStrokeWidth : 2,
		//Number - Spacing between each of the X value sets
		barValueSpacing : 5,
		//Number - Spacing between data sets within X values
		barDatasetSpacing : 1,
		onAnimationProgress : function() {
			console.log("onAnimationProgress");
		},
		onAnimationComplete : function() {
			console.log("onAnimationComplete");
		},
		//Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
		maintainAspectRatio : true,
		//Boolean - whether to make the chart responsive to window resizing
		responsive : true

	}

	$(function() {
		var ctx = document.getElementById("canvas").getContext("2d");
		barChart = new Chart(ctx).Bar(barChartData, barChartOption);
	});

	$("input#btnAdd").on(
			"click",
			function() {
				barChart.addData([ randomScalingFactor() ],
						months[(barChart.datasets[0].bars.length) % 12]);
			});

	$("canvas").on("click", function(e) {
		var activeBars = barChart.getBarsAtEvent(e);
		console.log(activeBars);

		for ( var i in activeBars) {
			console.log(activeBars[i].value);
		}
	});

	//기간 입력 JS--------------------------------------------------------------------------

	$('#fromDate').datepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		language : 'kr',
		todayHighlight : true

	});
	$('#toDate').datepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		language : 'kr',
		todayHighlight : true

	});

	var s = "${pageContext.request.contextPath}/calorie";

	//s = s + "/search?" + username + "/" + $('#fromDate').val() + "/" + $('#toDate').val();

	$("button#search").on(
			"click",
			function() {

				var username = "${calories[0].username}";
				var toDate = $('#toDate').val();
				var fromDate = $('#fromDate').val();

				if (toDate == "" || fromDate == "") {
					alert("날짜를 입력해주세요!");
				} else {
					var url = s + "/search?" + username + "/" + fromDate + "/"
							+ toDate;

					window.location.href = url;
				}
			});
</script>