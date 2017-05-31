<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<div class="content-wrapper" style="min-height: 1000px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			체중<small>체중 기록</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}"><i class="fa fa-dashboard"></i>DashBoard</a></li>
			<li class="active">체중</li>
		</ol>
	</section>

	<div class="col-md-6">
		<!-- BAR CHART -->
		<div class="box box-info">
			<div class="box-header with-border">
				<h3 class="box-title">체중</h3>

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

			<c:if test="${empty weights}">
				<h2 class="text-yellow">
					저장된 데이터가 없습니다. <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/weight" role="button">Back</a>
				</h2>
			</c:if>

			<c:if test="${not empty weights }">
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
						<th>체중</th>
						<sec:authorize access="!hasRole('ROLE_ADMIN')">
							<th></th>
						</sec:authorize>
					</tr>
				</thead>
				<tbody>
					<c:set var="id" value="1" />
					<c:forEach var="weight" items="${weights}">

						<tr>
							<td><c:out value="${id}" /></td>
							<td>${weight.measurement_time}</td>
							<td>${weight.weightValue}</td>
							<sec:authorize access="!hasRole('ROLE_ADMIN')">
								<td><a
									href="${pageContext.request.contextPath}/weight/delete/${weight.username}/${weight.measurement_time}">
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

	<c:forEach var="weight" items = "${weights}">
	result.push("${weight.weightValue}");
	//time.push("${weight.measurement_time}");
	time.push(number);
	number += 1;
	</c:forEach>

	var randomScalingFactor = function() {
		return Math.round(Math.random() * 100)
	};

	var months = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	var lineChart = null;
	var lineChartData = {
		labels : time,
		datasets : [ {
			label : "Weight",
			fillColor : "rgba(0, 0, 0, 1)",
			strokeColor : "rgba(0, 0, 0, 1)",
			pointColor : "rgba(220, 220, 220, 1)",
			
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(220,220,220,1)",
			data : result
		} ]

	};

	var lineChartOption = {
		//Boolean - If we should show the scale at all
		showScale : true,
		scaleBeginAtZero : true,

		//Boolean - Whether grid lines are shown across the chart
		scaleShowGridLines : false,
		//String - Colour of the grid lines
		scaleGridLineColor : "rgba(0,0,0,.05)",
		//Number - Width of the grid lines
		scaleGridLineWidth : 1,
		//Boolean - Whether to show horizontal lines (except X axis)
		scaleShowHorizontalLines : true,
		//Boolean - Whether to show vertical lines (except Y axis)
		scaleShowVerticalLines : true,
		//Boolean - Whether the line is curved between points
		bezierCurve : true,
		//Number - Tension of the bezier curve between points
		bezierCurveTension : 0.3,
		//Boolean - Whether to show a dot for each point
		pointDot : true,
		//Number - Radius of each point dot in pixels
		pointDotRadius : 2,
		//Number - Pixel width of point dot stroke
		pointDotStrokeWidth : 1,
		//Number - amount extra to add to the radius to cater for hit detection outside the drawn point
		pointHitDetectionRadius : 20,
		//Boolean - Whether to show a stroke for datasets
		datasetStroke : true,
		//Number - Pixel width of dataset stroke
		datasetStrokeWidth : 2,
		//Boolean - Whether to fill the dataset with a color
		datasetFill : false,

		//Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
		maintainAspectRatio : true,
		//Boolean - whether to make the chart responsive to window resizing
		responsive : true

	};

	$(function() {
		var ctx = document.getElementById("canvas").getContext("2d");
		lineChart = new Chart(ctx).Line(lineChartData, lineChartOption);
	});

	$("input#btnAdd").on(
			"click",
			function() {
				lineChart.addData([ randomScalingFactor(),
						randomScalingFactor() ],
						months[(lineChart.datasets[0].points.length) % 12]);
			});

	$("canvas").on("click", function(e) {
		var activePoints = lineChart.getPointsAtEvent(e);
		console.log(activePoints);

		for ( var i in activePoints) {
			console.log(activePoints[i].value);
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

	var s = "${pageContext.request.contextPath}/weight";

	//s = s + "/search?" + username + "/" + $('#fromDate').val() + "/" + $('#toDate').val();

	$("button#search").on(
			"click",
			function() {

				var username = "${weights[0].username}";
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